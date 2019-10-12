package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModelProperty;

public class CategoryResource {

    @ApiModelProperty(required = true, value = "Category value", example = "BED")
    public String value;
    @ApiModelProperty(required = false, value = "Category description", example = "Beds and canapes", readOnly = true)
    public String description;

    public CategoryResource(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public CategoryResource(String value) {
        this.value = value;
    }

    public CategoryResource(){}
}
