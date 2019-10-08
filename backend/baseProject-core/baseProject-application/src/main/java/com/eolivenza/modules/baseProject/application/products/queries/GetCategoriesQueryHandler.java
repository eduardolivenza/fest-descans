package com.eolivenza.modules.baseProject.application.products.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.ProductsRepository;
import com.eolivenza.modules.baseProject.domain.model.products.Category;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
public class GetCategoriesQueryHandler implements QueryHandler<Class<Void>, List<Category>> {

    @Inject
    public GetCategoriesQueryHandler(ProductsRepository pRepository) {
    }

    @DomainStrictTransactional
    @Override
    public List<Category> apply(Class<Void> getProductsRequest) {
        return Arrays.asList(Category.values());
    }

    @Override
    public String getName() { return "GetAllCategories"; }

}
