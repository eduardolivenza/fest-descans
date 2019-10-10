package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.products.commands.AddProductCommand;
import com.eolivenza.modules.baseProject.application.products.commands.DeleteProductCommand;
import com.eolivenza.modules.baseProject.application.products.commands.ModProductCommand;
import com.eolivenza.modules.baseProject.application.products.commands.productImages.AddProductImageCommand;
import com.eolivenza.modules.baseProject.application.security.BaseProjectGrantPermission;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.AvailableProductResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.ProductsResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.SupplierResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ProductResource;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Api(value = "Product")
@RestController
public class ProductsController {

    @Autowired
    private ProductsResourceMapper productsResourceMapper;
    @Autowired
    private AvailableProductResourceMapper availableProductResourceMapper;
    @Autowired
    private SupplierResourceMapper supplierResourceMapper;
    @Autowired
    private QueryHandler<Class<Void>, List<Product>> getAllProductsQueryHandler;
    @Autowired
    private QueryHandler<String, Product> getProductQueryHandler;
    @Autowired
    private CommandHandler<AddProductCommand> addProductCommandHandler;
    @Autowired
    private CommandHandler<ModProductCommand> modProductCommandHandler;
    @Autowired
    private CommandHandler<DeleteProductCommand> deleteProductCommandhandler;
    @Autowired
    private CommandHandler<AddProductImageCommand> storeImageCommandHandler;

    @Autowired
    public ProductsController() {
    }

    @ApiOperation(value = " Adds a new product")
    @PostMapping(path = "/products")
    public void addProduct(@RequestBody final ProductResource productResource) {
        Set<AvailableProduct> sizesSet = new HashSet<>();
        if (productResource.sizes != null) {
            Stream<AvailableProduct> sizesStream = productResource.sizes.stream().map(availableProductResourceMapper::toFirstType);
            sizesSet = sizesStream.collect(Collectors.toSet());
        }
        Supplier supplier = supplierResourceMapper.toFirstType(productResource.supplier);
        AddProductCommand addProductCommand = new AddProductCommand(
                productResource.productIdentifier,
                productResource.productName,
                productResource.category.value,
                productResource.productDescription,
                productResource.comfortLevel,
                supplier,
                sizesSet);
        addProductCommandHandler.accept(addProductCommand);
    }

    @ApiOperation(value = " Modify values of an existing product")
    @PatchMapping(path = "/products")
    public void modifyExistingProduct(@RequestBody final ProductResource productResource) {
        Set<AvailableProduct> sizesSet = new HashSet<>();
        if (productResource.sizes != null) {
            Stream<AvailableProduct> sizesStream = productResource.sizes.stream().map(availableProductResourceMapper::toFirstType);
            sizesSet = sizesStream.collect(Collectors.toSet());
        }
        Supplier supplier = supplierResourceMapper.toFirstType(productResource.supplier);
        ModProductCommand modProductCommand = new ModProductCommand(
                productResource.internalIdentifier,
                productResource.productIdentifier,
                productResource.productName,
                productResource.category.value,
                productResource.productDescription,
                productResource.comfortLevel,
                supplier,
                sizesSet);

        modProductCommandHandler.accept(modProductCommand);
    }

    @ApiOperation(value = " Get all products of the system")
    @GetMapping(path = "/products", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public List<ProductResource> getAllProducts() {
        List<Product> productList = getAllProductsQueryHandler.apply(Void.TYPE);
        return productList.stream().map(productsResourceMapper::toSecondType).collect(Collectors.toList());
    }

    @ApiOperation(value = " Retrieve one product by its external identifier")
    @GetMapping(path = "/products/{productIdentifier}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   public ProductResource retrieveProduct(
            @ApiParam(required = true, value = "External identifier of the instrument", example = "800||1")
            @PathVariable final String productIdentifier) {
        Product product = getProductQueryHandler.apply(productIdentifier);
        return productsResourceMapper.toSecondType(product);
    }

    @ApiOperation(value = " Retrieve one product by its internal identifier")
    @DeleteMapping(path = "/products/{identifier}")
    public void deleteProduct(
            @ApiParam(required = true, value = "External identifier of the instrument", example = "uuid")
            @PathVariable("identifier") String identifier) {
        deleteProductCommandhandler.accept(new DeleteProductCommand(identifier));
    }


    @PostMapping(value = "/products/images/{productIdentifier}")
    public void handleFileUpload(@PathVariable("productIdentifier") String productIdentifier,
                                 @RequestParam("file") MultipartFile file) {
        try{
            AddProductImageCommand command = new AddProductImageCommand(productIdentifier, file.getOriginalFilename(), file.getInputStream());
            storeImageCommandHandler.accept(command);
        } catch ( IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

}
