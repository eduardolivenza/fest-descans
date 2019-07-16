package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Category", description = "Categories of the products")
public class CategoryResource {


    @ApiModelProperty(required = true, value = "Relates to the highest level customer identification. The CustomerID value can come from a global SAP, customer master record, local identifier", example = "Bayley")
    public String identifier;

    @ApiModelProperty(required = true, value = "The path on the filesystem where to generate the report file. It must exists.", example = "d:")
    public String description;



    public CategoryResource(){}

    public CategoryResource(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }
}
