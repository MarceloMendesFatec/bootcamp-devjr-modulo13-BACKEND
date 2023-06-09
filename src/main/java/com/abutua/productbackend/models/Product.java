package com.abutua.productbackend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.abutua.productbackend.DAO.ProductDAO;

@Entity
@Table(name = "TBL_PRODUCT")
public class Product implements Serializable {  

    // Atributos
    // Utilizar objetos nas entidades para poder utilizar o valor null se necessario
    //somes os constraints do banco de dados ficam na Entidade , as validacoes sao no DAO
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 255)	
    private String name;

    @Column(nullable = false , length = 1024)
    private String description;


    @ManyToOne 
    private Category category;
    
    private boolean promotion;
    private boolean newProduct;
    
    @Min(value=0, message = "price deve ser maior que zero")
    private Double price; // Se nao for passado o valor, vai como null , se for "double" vai como 0.0

    // Métodos Construtores
    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, String description, Category category, boolean promotion, boolean newProduct,
            double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.promotion = promotion;
        this.newProduct = newProduct;
        this.price = price;
    }

    public Product() { // NECESSARIO UM CONSTRUTOR VAZIO PARA O JPA

    }

    // Métodos
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", promotion=" + promotion
                + ", newProduct=" + newProduct + ", price=" + price + "]";
    }

    public ProductDAO toDAO() {
        ProductDAO productDAO = new ProductDAO();
        productDAO.setName(name);
        productDAO.setDescription(description);
        productDAO.setPrice(price);
        productDAO.setCategory(category.toDAO());
        productDAO.setNewProduct(newProduct);
        productDAO.setPromotion(promotion);
        productDAO.setId(id);

        return productDAO;
    }

    
}
