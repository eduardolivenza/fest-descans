package com.eolivenza.modules.baseProject.application.suppliers.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.SuppliersRepository;
import com.eolivenza.modules.baseProject.domain.model.products.Product;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class GetAllSuppliersQueryHandler implements QueryHandler<Class<Void>, List<Supplier>> {

    private final SuppliersRepository suppliersRepository;

    @Inject
    public GetAllSuppliersQueryHandler(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    /**
     * Retrieve the {@link Product}
     *
     * @param getProductsRequest void class
     * @return a {@link Supplier}
     **/
    @DomainStrictTransactional
    @Override
    public List<Supplier> apply(Class<Void> getProductsRequest) {
        return (suppliersRepository.retrieveAll());
    }

    @Override
    public String getName() { return "GetAllSuppliers"; }



}
