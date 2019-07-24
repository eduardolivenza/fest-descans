package com.eolivenza.modules.baseProject.repositories.domain.suppliers;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(schema = "baseproject_dm_tables", name = "SUPPLIERS")
public class SupplierJpa {

    @Id
    public String uuid;

    private String companyName;

    private String country;

    public SupplierJpa() {
        //JPA demands it
    }

    public SupplierJpa(String uuid, String companyName, String country) {
        this.uuid = uuid;
        this.companyName = companyName;
        this.country = country;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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



