package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

@ApiModel(value = "Product", description = "Text for describing the user")
public class ProductResource {

    @ApiModelProperty(required = false, value = "Identifier", example = "123e4567-e89b-12d3-a456-426655440000", readOnly = true)
    public String identifier;

    @ApiModelProperty(required = true, value = "External identifier", example = "800||1")
    public String productIdentifier;

    @ApiModelProperty(required = false)
    public Set<AvailableSizeResource> sizes;

    public ProductResource(){}

    public ProductResource(String identifier, String productIdentifier,  Set<AvailableSizeResource> sizes) {
        this.identifier = identifier;
        this.productIdentifier = productIdentifier;
        this.sizes = sizes;
    }
}
