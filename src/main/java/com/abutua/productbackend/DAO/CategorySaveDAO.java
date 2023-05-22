package com.abutua.productbackend.DAO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.abutua.productbackend.models.Category;

public class CategorySaveDAO {
    @Size(min = 3, max = 255, message = "name deve ter entre 3 e 255 caracteres")
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toEntity() {
        return new Category(name);
    }
}
