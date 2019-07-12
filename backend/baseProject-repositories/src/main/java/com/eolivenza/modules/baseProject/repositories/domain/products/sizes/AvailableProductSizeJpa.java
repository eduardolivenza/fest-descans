package com.eolivenza.modules.baseProject.repositories.domain.products.sizes;

import com.eolivenza.modules.baseProject.repositories.domain.products.ProductJpa;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(schema = "baseproject_dm_tables", name = "PRODUCT_SIZES")
public class AvailableProductSizeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    public String identifier;

    private String size;

    private Integer value;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "productJpa",
            foreignKey = @ForeignKey(name = "FK_PRODUCTSIZE_PRODUCT_IDENTIFIER"))
    public ProductJpa productJpa;

    public AvailableProductSizeJpa() {
        //JPA demands it
    }

    public AvailableProductSizeJpa(String size, Integer value) {
        setSize(size);
        setValue(value);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
