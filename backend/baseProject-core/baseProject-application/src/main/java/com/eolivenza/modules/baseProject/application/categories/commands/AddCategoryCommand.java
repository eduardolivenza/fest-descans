package com.eolivenza.modules.baseProject.application.categories.commands;

public class AddCategoryCommand {

    public String identifier;

    public String description;

    public AddCategoryCommand(String identifer, String description){
        this.identifier = identifer;
        this.description = description;
    }
}
