package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

@ApiModel(value = "Product", description = " Text for describing the product")
public class ProductResource {

    @ApiModelProperty(required = false, value = "Identifier", example = "", readOnly = true)
    public String internalIdentifier;

    @ApiModelProperty(required = true)
    public CategoryResource category;

    @ApiModelProperty(required = true, value = "Product name", example = "Memory restore")
    public String productName;

    @ApiModelProperty(required = true, value = "Product description", example = " Comfortable latex bed ")
    public String productDescription;

    @ApiModelProperty(required = true, value = "Comfort level", example = "3")
    public Integer comfortLevel;

    @ApiModelProperty(required = false)
    public SupplierResource supplier;

    @ApiModelProperty(required = false)
    public Set<AvailableSizeResource> sizes;

    @ApiModelProperty(required = false)
    public Set<String> images;

    public ProductResource(){}

    public ProductResource(String identifier, CategoryResource category, String productName, String productDescription, Integer comfortLevel, SupplierResource supplierResource, Set<AvailableSizeResource> sizes, Set<String> images) {
        this.internalIdentifier = identifier;
        this.category = category;
        this.productName = productName;
        this.productDescription = productDescription;
        this.sizes = sizes;
        this.comfortLevel = comfortLevel;
        this.supplier = supplierResource;
        this.images = images;
    }

    public ProductResource( CategoryResource category,  String productName, String productDescription,  Integer comfortLevel,  SupplierResource supplierResource, Set<AvailableSizeResource> sizes, Set<String> images) {
        this.category = category;
        this.productName = productName;
        this.productDescription = productDescription;
        this.comfortLevel = comfortLevel;
        this.supplier = supplierResource;
        this.sizes = sizes;
        this.images = images;
    }
}
