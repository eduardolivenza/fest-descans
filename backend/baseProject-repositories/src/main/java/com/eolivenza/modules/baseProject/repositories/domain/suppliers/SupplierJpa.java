package com.eolivenza.modules.baseProject.repositories.domain.suppliers;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(schema = "baseproject_dm_tables", name = "SUPPLIERS")
public class SupplierJpa {

    @Id
    public String externalIdentifier;

    private String companyName;

    private String country;

    public SupplierJpa() {
        //JPA demands it
    }

    public SupplierJpa(String externalIdentifier, String companyName, String country) {
        this.externalIdentifier = externalIdentifier;
        this.companyName = companyName;
        this.country = country;
    }

    public String getExternalIdentifier() {
        return externalIdentifier;
    }

    public void setExternalIdentifier(String uuid) {
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
}



