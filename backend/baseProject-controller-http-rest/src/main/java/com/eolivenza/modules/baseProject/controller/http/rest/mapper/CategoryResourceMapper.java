package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.CategoryResource;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.SupplierResource;
import com.eolivenza.modules.baseProject.domain.model.products.Category;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import org.springframework.stereotype.Component;

@Component
public class CategoryResourceMapper implements ResourceMapper<Category, CategoryResource> {

    @Override
    public Category toFirstType(CategoryResource object) {
        return Category.valueOf(object.value);
    }

    @Override
    public CategoryResource toSecondType(Category object) {
        return new CategoryResource(
            object.name(),
            object.getDescription());
    }
}
