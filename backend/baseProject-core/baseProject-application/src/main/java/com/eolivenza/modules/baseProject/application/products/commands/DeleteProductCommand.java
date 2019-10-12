package com.eolivenza.modules.baseProject.application.products.commands;

import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;

import java.util.Set;

public class DeleteProductCommand {

    public String identifier;


    public DeleteProductCommand(String identifier) {
        this.identifier = identifier;
    }
}
