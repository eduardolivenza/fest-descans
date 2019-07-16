package com.eolivenza.modules.baseProject.application.products.commands.availableSizes;

public class AddAvailableSizeToProductCommand {

    public String identifier;

    public String size;

    public Integer price;

    public AddAvailableSizeToProductCommand(String identifer, String size, Integer price){
        this.identifier = identifer;
        this.size = size;
        this.price = price;
    }
}
