package com.eolivenza.modules.baseProject.application.products.commands.availableSizes;


import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.products.commands.AddProductCommand;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.domain.model.products.Category;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;

@Named
public class AddAvailableSizeCommandHandler implements CommandHandler<AddAvailableSizeToProductCommand> {

    private final ProductsRepository productsRepository;

    private Logger logger = LoggerFactory.getLogger(AddAvailableSizeCommandHandler.class);

    @Inject
    public AddAvailableSizeCommandHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(AddAvailableSizeToProductCommand addProductCommand) {





    }



    @Override
    public String getName() { return "Add available size to a product"; }

}


