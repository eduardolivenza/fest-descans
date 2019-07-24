package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Supplier", description = "Element for describe a supplier")
public class SupplierResource {


    @ApiModelProperty(required = true, value = "Company name", example = "Poligon", readOnly = true)
    public String companyId;

    @ApiModelProperty(required = true, value = "Company name", example = "Poligon SA")
    public String companyName;

    @ApiModelProperty(required = true, value = "Country", example = "Spain")
    public String country;


    public SupplierResource(){}

    public SupplierResource(String companyName, String country) {
        this.companyName = companyName;
        this.country = country;
    }
}
