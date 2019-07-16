package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.security.BaseProjectGrantPermission;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.ProductsResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ProductResource;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Api(value = "Product")
@RestController
public class ProductsController {

    private final ProductsResourceMapper productsResourceMapper;
    private final QueryHandler<Class<Void>, List<Product>> getAllProductsQueryHandler;
    private final QueryHandler<String, Product> getProductQueryHandler;

    @Autowired
    public ProductsController(
            ProductsResourceMapper productsResourceMapper,
            QueryHandler<Class<Void>, List<Product>> getAllProductsQueryHandler,
            QueryHandler<String, Product> getProductQueryHandler)
    {
        this.productsResourceMapper = productsResourceMapper;
        this.getAllProductsQueryHandler = getAllProductsQueryHandler;
        this.getProductQueryHandler = getProductQueryHandler;
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
