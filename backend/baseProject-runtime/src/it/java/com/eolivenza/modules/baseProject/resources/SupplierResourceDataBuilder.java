package com.eolivenza.modules.baseProject.resources;

public class SupplierResourceDataBuilder {

    public static SupplierResourceBuilder defaultBuilder() {
        return SupplierResourceBuilder.aSupplierResource().withId("Fest").withName("Fest Descans").withCountry("Spain");
    }
}
