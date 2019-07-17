package com.eolivenza.modules.baseProject.application.products.commands;


import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.products.commands.availableSizes.AddAvailableSizeToProductCommand;
import com.eolivenza.modules.baseProject.application.repositories.CategoriesRepository;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.domain.model.categories.Category;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;

@Named
public class AddProductCommandHandler implements CommandHandler<AddProductCommand> {

    private final ProductsRepository productsRepository;

    private final CategoriesRepository categoriesRepository;

    private final CommandHandler<AddAvailableSizeToProductCommand> addAvailableSizeCommandHandler;

    private Logger logger = LoggerFactory.getLogger(AddProductCommandHandler.class);

    @Inject
    public AddProductCommandHandler(ProductsRepository productsRepository,
                                    CategoriesRepository categoriesRepository,
                                    CommandHandler<AddAvailableSizeToProductCommand> addAvailableSizeCommandHandler) {
        this.productsRepository = productsRepository;
        this.categoriesRepository = categoriesRepository;
        this.addAvailableSizeCommandHandler = addAvailableSizeCommandHandler;
    }

    @DomainStrictTransactional
    @Override
    public void accept(AddProductCommand addProductCommand) {

        Category category = null;
        if (productsRepository.existsByProductIdentifier(addProductCommand.productIdentifier)) {
            //throw new ReagentConsumerAlreadyExistsException(addProductCommand.externalIdentifier);
        }
        if (categoriesRepository.existsByIdentifier(addProductCommand.category)){
            category = categoriesRepository.retrieve(addProductCommand.category);
        }
        else{
            // throw exception
        }
        Product product = new Product(
                category,
                addProductCommand.productIdentifier,
                addProductCommand.productDescription,
                new HashSet<>()
        );

        productsRepository.create(product);

        addProductCommand.sizes.forEach(size ->
        {
            AddAvailableSizeToProductCommand addAvailableSizeToProductCommand = new AddAvailableSizeToProductCommand(
                    addProductCommand.productIdentifier,
                    size.size,
                    size.price
            );
            addAvailableSizeCommandHandler.accept(addAvailableSizeToProductCommand);
        });
    }

    @Override
    public String getName() { return "Add product"; }

}


