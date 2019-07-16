package com.eolivenza.modules.baseProject.application.repositories;


import com.eolivenza.modules.baseProject.application.repositories.generics.Repository;
import com.eolivenza.modules.baseProject.domain.model.products.Category;

public interface CategoriesRepository  extends Repository<Category, String> {
    boolean existsByIdentifier(String identifer);
}

