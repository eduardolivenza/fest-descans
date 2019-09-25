package com.eolivenza.modules.baseProject.application.images.commands;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;

import com.eolivenza.modules.baseProject.application.files.ImageStorage;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.products.ProductImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;


@Named
public class StoreImageCommandHandler implements CommandHandler<StoreImageCommand> {

    private final ImageStorage imageStorage;
    private final ProductsRepository productsRepository;

    @Inject
    public StoreImageCommandHandler(ImageStorage imageStorage, ProductsRepository productsRepository) {
        this.imageStorage = imageStorage;
        this.productsRepository = productsRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(StoreImageCommand storeImageCommand) {
        if (storeImageCommand.getFilename().contains("..")) {
            throw new RuntimeException("Cannot store file with relative path outside current directory " + storeImageCommand.getFilename());
        }
        Optional<Product> optionalProduct = productsRepository.retrieveByProductIdentifier(storeImageCommand.getProductIdentifier());
        if (optionalProduct.isPresent() && imageStorage.saveFile(storeImageCommand.getFileContent(), storeImageCommand.getFilename())){
            Product product = optionalProduct.get();
            product.addProductImage(new ProductImage(storeImageCommand.getFilename()));
            productsRepository.update(product);
        }
    }

    @Override
    public String getName() { return "Store an image in the system"; }

}


