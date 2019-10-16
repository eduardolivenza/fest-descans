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
public class GetSupplierQueryHandler implements QueryHandler<String, Supplier> {

    private final SuppliersRepository suppliersRepository;

    @Inject
    public GetSupplierQueryHandler(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    /**
     * Retrieve the {@link Product}
     *
     * @param identity string class
     * @return a {@link Supplier}
     **/
    @DomainStrictTransactional
    @Override
    public Supplier apply(String identity) {
        return (suppliersRepository.retrieve(identity));
    }

    @Override
    public String getName() { return "GetOneSupplier"; }



}
