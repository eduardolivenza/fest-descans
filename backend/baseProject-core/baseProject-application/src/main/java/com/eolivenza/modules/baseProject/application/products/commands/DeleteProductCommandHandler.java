package com.eolivenza.modules.baseProject.application.products.commands;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.products.queries.ProductNotFoundException;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.application.repositories.SuppliersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand> {

    private final ProductsRepository productsRepository;

    private Logger logger = LoggerFactory.getLogger(DeleteProductCommandHandler.class);

    @Inject
    public DeleteProductCommandHandler(ProductsRepository productsRepository, SuppliersRepository suppliersRepository) {
        this.productsRepository = productsRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(DeleteProductCommand deleteProductCommand) {
        if (productsRepository.existsByuuid(deleteProductCommand.identifier)) {
            productsRepository.delete(deleteProductCommand.identifier);
        }
        else {
            throw new ProductNotFoundException(deleteProductCommand.identifier);
        }
    }

    @Override
    public String getName() { return "Deletes an existing product"; }

}


