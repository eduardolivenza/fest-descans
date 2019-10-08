package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.CategoryResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.CategoryResource;
import com.eolivenza.modules.baseProject.domain.model.products.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@Api(value = "Categories")
@RestController
public class CategoriesController {

    @Autowired
    private QueryHandler<Class<Void>, List<Category>> getAllCategoriesQueryHandler;
    @Autowired
    private CategoryResourceMapper categoryResourceMapper;

    @Autowired
    public CategoriesController( ) {
    }

    @ApiOperation(value = "Retrieves all possible product categories")
    @GetMapping(path = "/categories")
    public List<CategoryResource> getCategories() {
        List<Category> categoryList = getAllCategoriesQueryHandler.apply(Void.TYPE);
        return categoryList.stream().map(categoryResourceMapper::toSecondType).collect(Collectors.toList());
    }

}
