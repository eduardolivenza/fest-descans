package com.eolivenza.modules.baseProject.resources;


import com.eolivenza.modules.baseProject.controller.http.rest.resources.CategoryResource;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.SupplierResource;

public final class CategoryResourceBuilder {

    private String value;
    private String description;


    private CategoryResourceBuilder() {
    }

    public static CategoryResourceBuilder aCategoryResource() {
        return new CategoryResourceBuilder();
    }

    public CategoryResourceBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public CategoryResourceBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryResource build() {
        return new CategoryResource(value,  description);
    }
}
