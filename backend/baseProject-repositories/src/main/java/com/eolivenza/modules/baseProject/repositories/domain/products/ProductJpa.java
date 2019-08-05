package com.eolivenza.modules.baseProject.repositories.domain.products;

import com.eolivenza.modules.baseProject.repositories.domain.categories.CategoryJpa;
import com.eolivenza.modules.baseProject.repositories.domain.products.sizes.AvailableProductSizeJpa;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(schema = "baseproject_dm_tables", name = "PRODUCTS")
public class ProductJpa {

    @Id
    public String uuid;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryJpa_id",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private CategoryJpa categoryJpa;

    private String productIdentifier;

    private String description;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "productJpa", fetch = FetchType.EAGER)
    public Set<AvailableProductSizeJpa> productSizes;

    public ProductJpa() {
        //JPA demands it
    }

    public ProductJpa(String uuid, CategoryJpa categoryJpa, String productIdentifier,String description, Set<AvailableProductSizeJpa> productSizes ) {
        this.uuid = uuid;
        this.categoryJpa = categoryJpa;
        this.productIdentifier = productIdentifier;
        this.description = description;
        this.productSizes = productSizes;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public CategoryJpa getCategoryJpa() {
        return categoryJpa;
    }

    public void setCategoryJpa(CategoryJpa categoryJpa) {
        this.categoryJpa = categoryJpa;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AvailableProductSizeJpa> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(Set<AvailableProductSizeJpa> productSizes) {
        this.productSizes = productSizes;
    }
}
