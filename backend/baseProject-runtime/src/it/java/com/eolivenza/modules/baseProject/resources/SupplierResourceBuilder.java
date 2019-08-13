package com.eolivenza.modules.baseProject.resources;


import com.eolivenza.modules.baseProject.controller.http.rest.resources.SupplierResource;

public final class SupplierResourceBuilder {

    private String companyId;
    private String companyName;
    private String country;


    private SupplierResourceBuilder() {
    }

    public static SupplierResourceBuilder aSupplierResource() {
        return new SupplierResourceBuilder();
    }

    public SupplierResourceBuilder withId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public SupplierResourceBuilder withName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public SupplierResourceBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public SupplierResource build() {
        return new SupplierResource(companyId,  companyName,  country);
    }
}
