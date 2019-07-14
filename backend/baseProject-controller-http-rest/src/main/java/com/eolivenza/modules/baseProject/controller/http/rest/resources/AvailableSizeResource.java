package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

public class AvailableSizeResource {

    @ApiModelProperty(required = false, value = "Size", example = "", readOnly = true)
    public String size;

    @ApiModelProperty(required = true, value = "Price", example = "")
    public Integer price;

    public AvailableSizeResource(){}

    public AvailableSizeResource(String size, Integer price) {
        this.size = size;
        this.price = price;
    }
}
