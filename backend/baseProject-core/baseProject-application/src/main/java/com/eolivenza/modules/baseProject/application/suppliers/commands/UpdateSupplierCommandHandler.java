package com.eolivenza.modules.baseProject.application.suppliers.commands;


import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.SuppliersRepository;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UpdateSupplierCommandHandler implements CommandHandler<UpdateSupplierCommand> {

    private final SuppliersRepository suppliersRepository;

    private Logger logger = LoggerFactory.getLogger(UpdateSupplierCommandHandler.class);

    @Inject
    public UpdateSupplierCommandHandler(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(UpdateSupplierCommand addProductCommand) {
        Supplier supplier = new Supplier(
                addProductCommand.externalIdentifier,
                addProductCommand.companyName,
                addProductCommand.country
        );
        suppliersRepository.update(supplier);
    }



    @Override
    public String getName() { return "Update supplier"; }

}


