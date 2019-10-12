package com.eolivenza.modules.baseProject.resources;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.CategoryResource;

public class CategoryResourceDataBuilder {

    public static CategoryResourceBuilder defaultBuilder() {
        return CategoryResourceBuilder.aCategoryResource().withValue("BED").withDescription(" Beds and canapes");
    }
}
