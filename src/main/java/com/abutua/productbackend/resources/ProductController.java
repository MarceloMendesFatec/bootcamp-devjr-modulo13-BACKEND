package com.abutua.productbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.productbackend.DAO.ProductDAO;
import com.abutua.productbackend.DAO.ProductSaveDAO;
import com.abutua.productbackend.services.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService; 

    @PostMapping
    public ResponseEntity<ProductDAO> save(@Validated @RequestBody ProductSaveDAO productSaveDAO) {
        ProductDAO productDAO = productService.save(productSaveDAO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productDAO.getId())
                .toUri();
 
        return ResponseEntity.created(location).body(productDAO);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDAO> getProduct(@PathVariable long id) {
        ProductDAO productDAO = productService.getDAOByID(id);
        return ResponseEntity.ok(productDAO);
    }

    @GetMapping  
    public ResponseEntity<List<ProductDAO>> getProducts() { 
        return ResponseEntity.ok(productService.getAll());
    }

    @DeleteMapping("{id}")    
    public ResponseEntity<Void> removeProduct(@PathVariable long id) {
        productService.deleteById(id);                
        return ResponseEntity.noContent().build(); 
    }
   
    @PutMapping("{id}")    
    public ResponseEntity<Void> updateProduct(@PathVariable long id,  @Validated @RequestBody ProductSaveDAO productSaveDAO) {
        productService.update(id, productSaveDAO);
        return ResponseEntity.ok().build(); 
    }
    

}
