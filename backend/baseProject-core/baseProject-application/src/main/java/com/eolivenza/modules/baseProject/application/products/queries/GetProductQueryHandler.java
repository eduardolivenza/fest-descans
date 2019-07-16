package com.eolivenza.modules.baseProject.application.products.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.domain.model.products.AvailableProduct;
import com.eolivenza.modules.baseProject.domain.model.products.Category;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.user.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Named
public class GetProductQueryHandler implements QueryHandler<String, Product> {

    private final ProductsRepository pRepository;

    @Inject
    public GetProductQueryHandler(ProductsRepository pRepository) {
        this.pRepository = pRepository;
    }

    /**
     * Retrieve the {@link Configuration}
     *
     * @param productIdentifier string class
     * @return a {@link Configuration}
     **/
    @DomainStrictTransactional
    @Override
    public Product apply(String productIdentifier) {
        Optional<Product> optionalProduct = pRepository.retrieveByProductIdentifier(productIdentifier);
        return optionalProduct.orElseThrow(() -> new ProductNotFoundException(productIdentifier));
    }

    @Override
    public String getName() { return "GetProduct"; }


}
