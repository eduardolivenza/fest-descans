package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.categories.commands.AddCategoryCommand;
import com.eolivenza.modules.baseProject.application.products.commands.AddProductCommand;
import com.eolivenza.modules.baseProject.application.security.BaseProjectGrantPermission;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.AvailableProductResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.ProductsResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.CategoryResource;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ProductResource;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Api(value = "Product")
@RestController
public class ProductsController {

    private final ProductsResourceMapper productsResourceMapper;
    private final AvailableProductResourceMapper availableProductResourceMapper;

    private final QueryHandler<Class<Void>, List<Product>> getAllProductsQueryHandler;
    private final QueryHandler<String, Product> getProductQueryHandler;
    private final CommandHandler<AddProductCommand> addProductCommandHandler;
    private final CommandHandler<AddCategoryCommand> addCategoryCommandHandler;


    @Autowired
    public ProductsController(
            ProductsResourceMapper productsResourceMapper,
            AvailableProductResourceMapper availableProductResourceMapper,
            CommandHandler<AddProductCommand> addProductCommandHandler,
            CommandHandler<AddCategoryCommand> addCategoryCommandHandler,
            QueryHandler<Class<Void>, List<Product>> getAllProductsQueryHandler,
            QueryHandler<String, Product> getProductQueryHandler)
    {
        this.productsResourceMapper = productsResourceMapper;
        this.availableProductResourceMapper = availableProductResourceMapper;
        this.getAllProductsQueryHandler = getAllProductsQueryHandler;
        this.getProductQueryHandler = getProductQueryHandler;
        this.addProductCommandHandler = addProductCommandHandler;
        this.addCategoryCommandHandler = addCategoryCommandHandler;
    }

    @ApiOperation(value = "Adds a new product")
    @PostMapping(path = "/products")
    public void addProduct(@RequestBody final ProductResource productResource) {
        Set<AvailableProduct> sizesSet = new HashSet<>();
        if (productResource.sizes != null) {
            Stream<AvailableProduct> sizesStream = productResource.sizes.stream().map(availableProductResourceMapper::toFirstType);
            sizesSet = sizesStream.collect(Collectors.toSet());
        }

        AddProductCommand addProductCommand = new AddProductCommand(
                productResource.productIdentifier,
                productResource.category,
                productResource.productDescription,
                sizesSet);

        addProductCommandHandler.accept(addProductCommand);
    }

    @ApiOperation(value = "Adds a new category")
    @PostMapping(path = "/categories")
    public void addCategory(@RequestBody final CategoryResource categoryResource) {

        AddCategoryCommand addCategoryCommand = new AddCategoryCommand(
                categoryResource.identifier ,
                categoryResource.description );

        addCategoryCommandHandler.accept(addCategoryCommand);
    }

    @ApiOperation(value = "Get all products of the system")
    @GetMapping(path = "/products", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public List<ProductResource> getAllProducts() {
        List<Product> productList = getAllProductsQueryHandler.apply(Void.TYPE);
        return productList.stream().map(productsResourceMapper::toSecondType).collect(Collectors.toList());
    }

    @ApiOperation(value = "Retrieve one product by its external identifier")
    @GetMapping(path = "/products/{productIdentifier}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   public ProductResource retrieveProduct(
            @ApiParam(required = true, value = "External identifier of the instrument", example = "800||1")
            @PathVariable final String productIdentifier) {

        Product product = getProductQueryHandler.apply(productIdentifier);
        return productsResourceMapper.toSecondType(product);
    }


}
