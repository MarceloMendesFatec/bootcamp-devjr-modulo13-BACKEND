package com.abutua.productbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.DAO.ProductDAO;
import com.abutua.productbackend.DAO.ProductSaveDAO;
import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;
import com.abutua.productbackend.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    
    public ProductDAO getDAOByID(long id){
        return getById(id).toDAO();
    }

    public Product getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return product;
    }

    public List<ProductDAO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(p -> p.toDAO())
                .collect(Collectors.toList());
    }

    public ProductDAO save(ProductSaveDAO productSaveDAO) {
        Product product = productRepository.save(productSaveDAO.toEntity());
        return product.toDAO();
    }

    public void deleteById(long id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    public void update(long id, ProductSaveDAO productSaveDAO) {
        Product productEntity = getById(id);
        Product productUpdate = productSaveDAO.toEntity();

        if (productUpdate.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category can not be empty");
        }
        Category category = categoryService.getById(productUpdate.getCategory().getId());

        productEntity.setDescription(productUpdate.getDescription());
        productEntity.setName(productUpdate.getName());
        productEntity.setPrice(productUpdate.getPrice());
        productEntity.setNewProduct(productUpdate.isNewProduct());
        productEntity.setPromotion(productUpdate.isPromotion());
        productEntity.setCategory(category);

        productRepository.save(productEntity);
    }

    //create a function to update the product with the new product name
    
}
