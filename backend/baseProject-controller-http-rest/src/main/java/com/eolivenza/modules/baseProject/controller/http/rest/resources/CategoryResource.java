package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Category", description = "Categories of the products")
public class CategoryResource {


    @ApiModelProperty(required = true, value = " Product type identifier", example = "BED")
    public String identifier;

    @ApiModelProperty(required = true, value = " Category description.", example = " sofas & canapes")
    public String description;

    public CategoryResource(){}

    public CategoryResource(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }
}
