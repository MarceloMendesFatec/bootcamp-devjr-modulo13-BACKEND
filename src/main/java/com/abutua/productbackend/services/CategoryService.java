package com.abutua.productbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.DAO.CategoryDao;
import com.abutua.productbackend.DAO.CategorySaveDAO;
import com.abutua.productbackend.models.Category;

import com.abutua.productbackend.repositories.CategoryRepository;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

   

    public CategoryDao getDAOById(int id) {
        return getById(id).toDAO();
    }

    /*
     * O código é uma simples função que recebe um id como argumento e, em seguida,
     * busca uma entidade Category correspondente no repositório de categorias. Se a
     * entidade existir, ela é retornado. Caso contrário, o método lança uma exceção
     * de ResponseStatusException com o status NOT_FOUND e uma mensagem informando
     * que a categoria não foi encontrada. ↓↓↓
     */

    public Category getById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return category;
    }
    
    /*
     * Este código retorna uma lista de objetos do tipo "CategoryDao" que representa
     * todas as categorias armazenadas no repositório. Ele faz isso usando o método
     * "findAll()" do "categoryRepository" para obter uma lista de objetos do tipo
     * "Category". Em seguida, ele utiliza a Stream API do Java 8 para transformar
     * cada objeto "Category" em seu equivalente "CategoryDao" usando o método
     * "toDAO()" e coleta os objetos resultantes em uma nova lista usando o método
     * "collect()". Finalmente, a lista resultante é retornada. ↓↓↓
     */

    public List<CategoryDao> getAll() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(c -> c.toDAO())
                                 .collect(Collectors.toList());
                                 
    }
    

    public CategoryDao save(CategorySaveDAO categorySaveDAO) {
        Category category = categoryRepository.save(categorySaveDAO.toEntity());
        return category.toDAO();
    }

    public void deleteById(int id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }

    public void update(int id, CategorySaveDAO categorySaveDAO) {
        Category category = getById(id);
        category.setName(categorySaveDAO.getName());
        categoryRepository.save(category);

    }
    

}
