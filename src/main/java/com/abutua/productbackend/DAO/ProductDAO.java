package com.abutua.productbackend.DAO;

public class ProductDAO {
    
    
    private Long id;
    private String name;
    private String description;
    private CategoryDao category;
    private boolean promotion;
    private boolean newProduct;
    private Double price;
    
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public CategoryDao getCategory() {
        return category;
    }
    public void setCategory(CategoryDao category) {
        this.category = category;
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    
}
