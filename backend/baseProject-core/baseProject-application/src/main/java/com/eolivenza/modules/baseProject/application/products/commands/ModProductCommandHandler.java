package com.eolivenza.modules.baseProject.application.products.commands;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.products.ProductExistsException;
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
import java.util.Optional;
import java.util.UUID;

@Named
public class ModProductCommandHandler implements CommandHandler<ModProductCommand> {

    private final ProductsRepository productsRepository;
    private final SuppliersRepository suppliersRepository;

    private Logger logger = LoggerFactory.getLogger(ModProductCommandHandler.class);

    @Inject
    public ModProductCommandHandler(ProductsRepository productsRepository, SuppliersRepository suppliersRepository) {
        this.productsRepository = productsRepository;
        this.suppliersRepository = suppliersRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(ModProductCommand modProductCommand) {


        if (productsRepository.existsByuuid(modProductCommand.identifier)) {
            Product originalProduct = productsRepository.retrieve(modProductCommand.identifier);

            Category category = Category.valueOf(modProductCommand.category);
            if (!suppliersRepository.existsBySupplierIdentifier(modProductCommand.supplier.getExternalIdentifier())){
                logger.info("Supplier not found. Creating a new one");
                suppliersRepository.create(modProductCommand.supplier);
            }
            Product product = new Product(
                    UUID.fromString(modProductCommand.identifier),
                    category,
                    modProductCommand.productIdentifier,
                    modProductCommand.productName,
                    modProductCommand.productDescription,
                    modProductCommand.comfortLevel,
                    modProductCommand.supplier,
                    new HashSet<>(),
                    originalProduct.getProductImages()
            );
            modProductCommand.sizes.forEach(size ->
            {
                product.addAvailableSize(new AvailableProduct(size.size, size.price));
            });
            productsRepository.update(product);
        }
        else{
            throw new ProductExistsException(modProductCommand.productIdentifier);
        }
    }

    @Override
    public String getName() { return "Modify an existing product"; }

}


