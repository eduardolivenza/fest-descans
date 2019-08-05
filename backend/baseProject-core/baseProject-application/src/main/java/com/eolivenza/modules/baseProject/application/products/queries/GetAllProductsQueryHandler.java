package com.eolivenza.modules.baseProject.application.products.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.domain.model.products.Product;


import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.List;

@Named
public class GetAllProductsQueryHandler implements QueryHandler<Class<Void>, List<Product>> {

    private final ProductsRepository pRepository;

    @Inject
    public GetAllProductsQueryHandler( ProductsRepository pRepository) {
        this.pRepository = pRepository;
    }

    /**
     * Retrieve the {@link Product}
     *
     * @param getProductsRequest void class
     * @return a {@link Product}
     **/
    @DomainStrictTransactional
    @Override
    public List<Product> apply(Class<Void> getProductsRequest) {
        return (pRepository.retrieveAll());
    }

    @Override
    public String getName() { return "GetAllProducts"; }



}
