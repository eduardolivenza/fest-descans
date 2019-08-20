package com.eolivenza.modules.baseProject.resources;

public class ProductResourceDataBuilder {

    public static ProductResourceBuilder defaultBuilder() {
        return ProductResourceBuilder.aProductResource().withDescription("Comfortable latex bed").withIdentifier("Latex01").withCategory("BED").withComfortLevel(3).withSupplier(null);
    }
}
