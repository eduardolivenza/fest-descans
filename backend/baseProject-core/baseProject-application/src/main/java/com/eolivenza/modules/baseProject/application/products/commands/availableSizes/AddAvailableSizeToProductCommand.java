package com.eolivenza.modules.baseProject.application.products.commands.availableSizes;

public class AddAvailableSizeToProductCommand {

    public String identifier;

    public String size;

    public Integer price;

    public AddAvailableSizeToProductCommand(String productIdentifier, String size, Integer price){
        this.identifier = productIdentifier;
        this.size = size;
        this.price = price;
    }
}
