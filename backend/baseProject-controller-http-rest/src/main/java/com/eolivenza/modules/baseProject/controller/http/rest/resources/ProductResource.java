package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

@ApiModel(value = "Product", description = " Text for describing the product")
public class ProductResource {

    @ApiModelProperty(required = false, value = "Identifier", example = "", readOnly = true)
    public String identifier;

    @ApiModelProperty(required = true, value = "Category", example = "BED")
    public String category;

    @ApiModelProperty(required = true, value = "Product identifier", example = "Latex01")
    public String productIdentifier;

    @ApiModelProperty(required = true, value = "Product description", example = " Comfortable latex bed ")
    public String productDescription;

    @ApiModelProperty(required = true, value = "Comfort level", example = "3")
    public Integer comfortLevel;

    @ApiModelProperty(required = false)
    public SupplierResource supplier;

    @ApiModelProperty(required = false)
    public Set<AvailableSizeResource> sizes;

    public ProductResource(){}

    public ProductResource(String identifier, String category, String productIdentifier,  String productDescription, Integer comfortLevel, SupplierResource supplierResource, Set<AvailableSizeResource> sizes) {
        this.identifier = identifier;
        this.category = category;
        this.productIdentifier = productIdentifier;
        this.productDescription = productDescription;
        this.sizes = sizes;
        this.comfortLevel = comfortLevel;
        this.supplier = supplierResource;
    }

    public ProductResource( String category, String productIdentifier,  String productDescription,  Integer comfortLevel,  SupplierResource supplierResource, Set<AvailableSizeResource> sizes) {
        this.category = category;
        this.productIdentifier = productIdentifier;
        this.productDescription = productDescription;
        this.comfortLevel = comfortLevel;
        this.supplier = supplierResource;
        this.sizes = sizes;
    }
}
