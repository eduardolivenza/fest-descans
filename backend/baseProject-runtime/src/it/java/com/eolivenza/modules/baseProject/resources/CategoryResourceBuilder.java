package com.eolivenza.modules.baseProject.resources;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.CategoryResource;


public final class CategoryResourceBuilder {
    private String identifier;
    private String description;


    private CategoryResourceBuilder() {
    }

    public static CategoryResourceBuilder aCategoryResource() {
        return new CategoryResourceBuilder();
    }

    public CategoryResourceBuilder withIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public CategoryResourceBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryResource build() {
        return new CategoryResource(identifier, description);
    }
}
