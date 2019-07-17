package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModelProperty;

public class AvailableSizeResource {

    @ApiModelProperty(required = true, value = "Size", example = "90x190x20")
    public String size;

    @ApiModelProperty(required = true, value = "Price", example = "200")
    public Integer price;

    public AvailableSizeResource(){}

    public AvailableSizeResource(String size, Integer price) {
        this.size = size;
        this.price = price;
    }
}
