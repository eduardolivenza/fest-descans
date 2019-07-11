package com.eolivenza.modules.baseProject.application.configuration.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.ProductTypeRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class GetConfigurationQueryHandler implements QueryHandler<Class<Void>, Optional<ProductType>> {
    private final ProductTypeRepository productTypeRepository;

    @Inject
    public GetConfigurationQueryHandler(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    /**
     * Retrieve the {@link ProductType}
     *
     * @param getConfigurationRequest void class
     * @return a {@link ProductType}
     **/
    @DomainStrictTransactional
    @Override
    public Optional<ProductType> apply(Class<Void> getConfigurationRequest) {
        return Optional.ofNullable(productTypeRepository.retrieve(ProductType.CONFIGURATION_UUID));
    }

    @Override
    public String getName() { return "GetConfiguration"; }


}
