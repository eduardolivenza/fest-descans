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
public class AddSupplierCommandHandler implements CommandHandler<AddSupplierCommand> {

    private final SuppliersRepository suppliersRepository;

    private Logger logger = LoggerFactory.getLogger(AddSupplierCommandHandler.class);

    @Inject
    public AddSupplierCommandHandler(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(AddSupplierCommand addProductCommand) {
        Supplier supplier = new Supplier(
                addProductCommand.externalIdentifier,
                addProductCommand.companyName,
                addProductCommand.country
        );
        if (suppliersRepository.existsBySupplierIdentifier(addProductCommand.externalIdentifier)){
            suppliersRepository.update(supplier);
        }
        else {
            suppliersRepository.create(supplier);
        }
    }



    @Override
    public String getName() { return "Add a new supplier to our environment"; }

}


