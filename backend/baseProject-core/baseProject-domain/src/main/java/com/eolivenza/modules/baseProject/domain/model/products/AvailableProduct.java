package com.eolivenza.modules.baseProject.domain.model.products;

public class AvailableProduct {

    public String identifier;

    public String size;

    public Integer price;

    public AvailableProduct(String identifer, String size, Integer price){
        this.identifier = identifer;
        this.size = size;
        this.price = price;
    }


}
