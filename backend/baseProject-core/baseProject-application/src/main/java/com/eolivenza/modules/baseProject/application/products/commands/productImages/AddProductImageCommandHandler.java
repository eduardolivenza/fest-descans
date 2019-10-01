package com.eolivenza.modules.baseProject.application.products.commands.productImages;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;

import com.eolivenza.modules.baseProject.application.files.ImageStorage;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.products.ProductImage;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Named
public class AddProductImageCommandHandler implements CommandHandler<AddProductImageCommand> {

    private final ImageStorage imageStorage;
    private final ProductsRepository productsRepository;
    private Pattern pattern;
    private Matcher matcher;

    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";

    @Inject
    public AddProductImageCommandHandler(ImageStorage imageStorage, ProductsRepository productsRepository) {
        this.imageStorage = imageStorage;
        this.productsRepository = productsRepository;
        pattern = Pattern.compile(IMAGE_PATTERN);
    }

    @DomainStrictTransactional
    @Override
    public void accept(AddProductImageCommand addProductImageCommand) {
        if (addProductImageCommand.getFilename().contains("..")) {
            throw new RuntimeException("Cannot store file with relative path outside current directory " + addProductImageCommand.getFilename());
        }
        matcher = pattern.matcher(addProductImageCommand.getFilename());
        if (!matcher.matches()) {
            throw new RuntimeException("Extension is not supported: " + addProductImageCommand.getFilename());
        }
        Optional<Product> optionalProduct = productsRepository.retrieveByProductIdentifier(addProductImageCommand.getProductIdentifier());
        if (optionalProduct.isPresent() && imageStorage.saveFile(addProductImageCommand.getFileContent(), addProductImageCommand.getFilename())){
            Product product = optionalProduct.get();
            product.addProductImage(new ProductImage(addProductImageCommand.getFilename()));
            productsRepository.update(product);
        }
    }

    @Override
    public String getName() { return "Store an image in the system"; }

}


