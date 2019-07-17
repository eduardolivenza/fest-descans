package com.eolivenza.modules.baseProject.domain.model.products;

import com.eolivenza.modules.baseProject.domain.model.Entity;

public class AvailableProduct extends Entity<AvailableProduct> {

    public String identifier;

    public String size;

    public Integer price;

    public AvailableProduct(String identifer, String size, Integer price){
        this.identifier = identifer;
        this.size = size;
        this.price = price;
    }

    public AvailableProduct(String size, Integer price){
        this.identifier = "";
        this.size = size;
        this.price = price;
    }

    @Override
    public int hashCodeCalculation() {
        return 0;
    }

    @Override
    public boolean hasSameIdentity(AvailableProduct other) {
        if (this.size.equals(other.size)){
            return true;
        }
        return false;
    }

}
