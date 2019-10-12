package com.eolivenza.modules.baseProject.domain.model.suppliers;

import com.eolivenza.modules.baseProject.domain.model.Entity;

import javax.validation.constraints.NotNull;

public class Supplier extends Entity<Supplier> {

    @NotNull
    private String externalIdentifier;

    @NotNull
    private String companyName;

    private String country;

    public Supplier(String externalIdentifier, String companyName, String country){
        this.externalIdentifier = externalIdentifier;
        this.companyName = companyName;
        this.country= country;
    }

    public String getExternalIdentifier() {
        return externalIdentifier;
    }

    public void setExternalIdentifier(String externalIdentifier) {
        this.externalIdentifier = externalIdentifier;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCodeCalculation() {
        return 0;
    }

    @Override
    public boolean hasSameIdentity(Supplier other) {
        return false;
    }
}
