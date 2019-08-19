package com.eolivenza.modules.baseProject.application.suppliers.commands.commands;

public class AddSupplierCommand {

    public String externalIdentifier;

    public String companyName;

    public String country;

    public AddSupplierCommand(String externalIdentifier,
                              String companyName, String country){
        this.externalIdentifier = externalIdentifier;
        this.companyName = companyName;
        this.country = country;
    }
}
