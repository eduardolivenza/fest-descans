package com.eolivenza.modules.baseProject.application.products.commands;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.products.ProductExistsException;
import com.eolivenza.modules.baseProject.application.products.ProductWithThisNameExistsException;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.application.repositories.SuppliersRepository;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Category;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;

@Named
public class AddProductCommandHandler implements CommandHandler<AddProductCommand> {

    private final ProductsRepository productsRepository;
    private final SuppliersRepository suppliersRepository;

    private Logger logger = LoggerFactory.getLogger(AddProductCommandHandler.class);

    @Inject
    public AddProductCommandHandler(ProductsRepository productsRepository, SuppliersRepository suppliersRepository) {
        this.productsRepository = productsRepository;
        this.suppliersRepository = suppliersRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(AddProductCommand addProductCommand) {
        Category category = Category.valueOf(addProductCommand.category);
        if (productsRepository.existsByProductName(addProductCommand.productName)) {
            throw new ProductWithThisNameExistsException(addProductCommand.productName);
        }
        if (!suppliersRepository.existsBySupplierIdentifier(addProductCommand.supplier.getExternalIdentifier())){
            logger.debug("Supplier not found. Creating a new one");
            suppliersRepository.create(addProductCommand.supplier);
        }
        Product product = new Product(
                category,
                addProductCommand.productName,
                addProductCommand.productDescription,
                addProductCommand.comfortLevel,
                addProductCommand.supplier,
                new HashSet<>(),
                new HashSet<>()
        );
        addProductCommand.sizes.forEach(size ->
        {
            product.addAvailableSize(new AvailableProduct(size.size, size.price));
        });
        productsRepository.create(product);
    }

    @Override
    public String getName() { return "Add product"; }

}


