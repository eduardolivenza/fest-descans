package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import javax.persistence.*;

@Entity
@Table(schema = "festdescans_dm_tables", name = "ProductTypes")
public class ProductTypeJpa {

    @Id
    public String uuid;

    private String productName;

    public ProductTypeJpa() {
        //JPA demands it
    }

    public ProductTypeJpa( String uuid, String productName) {
        this.uuid = uuid;
        this.productName = productName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}
