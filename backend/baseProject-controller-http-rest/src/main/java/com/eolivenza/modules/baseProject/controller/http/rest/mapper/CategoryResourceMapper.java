package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.CategoryResource;
import com.eolivenza.modules.baseProject.domain.model.categories.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResourceMapper implements ResourceMapper<Category, CategoryResource> {

    @Override
    public Category toFirstType(CategoryResource object) {
        return new Category(object.identifier, object.description);

    }

    @Override
    public CategoryResource toSecondType(Category object) {
        return new CategoryResource(
            object.getIdentifier(),
            object.getDescription()
        );
    }
}
