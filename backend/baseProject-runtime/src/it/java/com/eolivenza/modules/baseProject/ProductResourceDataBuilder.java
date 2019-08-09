package com.eolivenza.modules.baseProject;

public class ProductResourceDataBuilder {

    public static ProductResourceBuilder defaultBuilder() {
        return ProductResourceBuilder.aProductResource().withDescription("Comfortable latex bed").withIdentifier("Latex01").withCategory("BED");
    }
}
