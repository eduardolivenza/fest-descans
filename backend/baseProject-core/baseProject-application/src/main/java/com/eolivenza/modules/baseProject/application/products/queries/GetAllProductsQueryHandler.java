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

@Named
public class GetAllProductsQueryHandler implements QueryHandler<Class<Void>, List<Product>> {

    private final ProductsRepository pRepository;

    @Inject
    public GetAllProductsQueryHandler( ProductsRepository pRepository) {
        this.pRepository = pRepository;
    }

    /**
     * Retrieve the {@link Configuration}
     *
     * @param getProductsRequest void class
     * @return a {@link Configuration}
     **/
    @DomainStrictTransactional
    @Override
    public List<Product> apply(Class<Void> getProductsRequest) {
        test();

        return (pRepository.retrieveAll());
    }

    @Override
    public String getName() { return "GetAllProducts"; }


    public void test(){

        Category c = new Category("SOFA", "SOFA");
        HashSet<AvailableProduct> list = new HashSet<>();
        list.add(new AvailableProduct("","135x200", 400));
        list.add(new AvailableProduct("","150x200", 500));
        Product p = new Product(c, "My Identifier", "product description", list);
        this.pRepository.create(p);

        list.add(new AvailableProduct("","90x190", 250));
        Product p2 = new Product(c, "My Identifier2", "product 2 description", list);
        this.pRepository.create(p2);
    }

}
