package com.eolivenza.modules.baseProject.application.configuration.commands.overwrite;


import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.configuration.FileNameNotValidException;
import com.eolivenza.modules.baseProject.application.repositories.ProductTypeRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OverwriteConfigurationCommandHandler implements CommandHandler<OverwriteConfigurationCommand> {
    private final ProductTypeRepository productTypeRepository;

    private Logger logger = LoggerFactory.getLogger(OverwriteConfigurationCommandHandler.class);

    @Inject
    public OverwriteConfigurationCommandHandler(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(OverwriteConfigurationCommand overwriteConfigurationCommand) {
        String exportPath = overwriteConfigurationCommand.getExportPath();
        logger.debug(" Selected path for configuration element is: {}", exportPath);
        if (overwriteConfigurationCommand.getClientIdentifier().matches(".*[/\n\r\t\0\f`?*\\<>|\":].*")) {
            throw new FileNameNotValidException(overwriteConfigurationCommand.getClientIdentifier());
        }
        ProductType productTypeWithNewValues = toDomain(overwriteConfigurationCommand);
        logger.debug(" Element configuration validated");
        if (productTypeRepository.exists(ProductType.CONFIGURATION_UUID)) {
            ProductType actualProductType = productTypeRepository.retrieve(ProductType.CONFIGURATION_UUID);
            actualProductType.overwriteWith(productTypeWithNewValues);
            productTypeRepository.update(actualProductType);
        }
        else {
            productTypeRepository.create(productTypeWithNewValues);
        }
    }

    private ProductType toDomain(OverwriteConfigurationCommand overwriteConfigurationCommand) {
        return new ProductType(
                overwriteConfigurationCommand.getClientIdentifier());

    }

    @Override
    public String getName() { return "OverwriteConfiguration"; }

}


