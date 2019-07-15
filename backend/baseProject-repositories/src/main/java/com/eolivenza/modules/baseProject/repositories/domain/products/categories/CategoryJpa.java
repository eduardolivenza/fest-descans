package com.eolivenza.modules.baseProject.repositories.domain.products.categories;

import com.eolivenza.modules.baseProject.repositories.domain.products.ProductJpa;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "baseproject_dm_tables", name = "CATEGORIES")
public class CategoryJpa {

    @Id
    @Column(updatable = false, nullable = false)
    public String identifier;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryJpa", cascade = CascadeType.ALL)
    private Set<ProductJpa> products = new HashSet<ProductJpa>();

    public CategoryJpa(){}

    public CategoryJpa(String identifier, String description, Set<ProductJpa> products) {
        this.identifier = identifier;
        this.description = description;
        this.products = products;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductJpa> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductJpa> products) {
        this.products = products;
    }
}
