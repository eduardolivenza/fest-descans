package com.eolivenza.modules.baseProject.repositories.domain.products;

import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeJpa;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(schema = "baseproject_dm_tables", name = "PRODUCTS")
public class ProductJpa {

    @Id
    public String uuid;

    private String productIdentifier;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "productJpa", fetch = FetchType.EAGER)
    public Set<AvailableProductSizeJpa> productSizes;

    public ProductJpa() {
        //JPA demands it
    }

    public ProductJpa(String uuid,  String productIdentifier, Set<AvailableProductSizeJpa> productSizes ) {
        this.uuid = uuid;
        this.productIdentifier = productIdentifier;
        this.productSizes = productSizes;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public Set<AvailableProductSizeJpa> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(Set<AvailableProductSizeJpa> productSizes) {
        this.productSizes = productSizes;
    }
}
