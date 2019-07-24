package com.eolivenza.modules.baseProject.application.suppliers.commands.commands;

public class AddSupplierCommand {

    public String companyName;

    public String country;

    public AddSupplierCommand(String companyName, String country){
        this.companyName = companyName;
        this.country = country;
    }
}
