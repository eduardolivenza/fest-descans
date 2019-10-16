package com.eolivenza.modules.baseProject.application.suppliers.commands;

public class UpdateSupplierCommand {

    public String externalIdentifier;

    public String companyName;

    public String country;

    public UpdateSupplierCommand(String externalIdentifier,
                                 String companyName, String country){
        this.externalIdentifier = externalIdentifier;
        this.companyName = companyName;
        this.country = country;
    }
}
