package com.eolivenza.modules.baseProject;

public class CategoryResourceDataBuilder {

    public static CategoryResourceBuilder defaultBuilder() {
        return CategoryResourceBuilder.aCategoryResource().withDescription("beds and sofas").withIdentifier("BED");
    }
}
