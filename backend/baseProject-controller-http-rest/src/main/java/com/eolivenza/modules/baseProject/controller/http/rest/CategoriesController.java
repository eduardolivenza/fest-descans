package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.categories.commands.AddCategoryCommand;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.CategoryResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "Category")
@RestController
public class CategoriesController {

    private final CommandHandler<AddCategoryCommand> addCategoryCommandHandler;

    @Autowired
    public CategoriesController(
            CommandHandler<AddCategoryCommand> addCategoryCommandHandler)   {
           this.addCategoryCommandHandler = addCategoryCommandHandler;
    }



    @ApiOperation(value = "Adds a new category")
    @PostMapping(path = "/categories")
    public void addCategory(@RequestBody final CategoryResource categoryResource) {

        AddCategoryCommand addCategoryCommand = new AddCategoryCommand(
                categoryResource.identifier ,
                categoryResource.description );
        addCategoryCommandHandler.accept(addCategoryCommand);
    }




}
