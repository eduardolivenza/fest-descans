package com.eolivenza.modules.baseProject.domain.model.products;

import com.eolivenza.modules.baseProject.domain.model.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Product extends Entity<Product> {

    @NotBlank
    private String email;
    @NotNull
    private String password;
    private String name;


    public Product(){ }

    public String getEmail() {
        return email;
    }

    public Product setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Product setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }


    public void overwriteWith(Product otherProduct) {
        setName(otherProduct.getName());
        setPassword(otherProduct.getPassword());
    }

    @Override
    public int hashCodeCalculation() {
        return 0;
    }

    @Override
    public boolean hasSameIdentity(Product other) {
        return false;
    }
}
