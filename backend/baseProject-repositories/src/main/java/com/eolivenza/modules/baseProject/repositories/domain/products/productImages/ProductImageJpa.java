package com.eolivenza.modules.baseProject.repositories.domain.products.productImages;

import com.eolivenza.modules.baseProject.repositories.domain.products.ProductJpa;

import javax.persistence.*;

@Entity
@Table(schema = "baseproject_dm_tables", name = "PRODUCT_IMAGES")
public class ProductImageJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    public String identifier;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "productJpa",
            foreignKey = @ForeignKey(name = "FK_PRODUCTIMAGE_PRODUCT_IDENTIFIER"))
    public ProductJpa productJpa;

    public ProductImageJpa() {
        //JPA demands it
    }

    public ProductImageJpa(String identifier, String filename) {
        setIdentifier(identifier);
        setFilename(filename);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ProductJpa getProductJpa() {
        return productJpa;
    }

    public void setProductJpa(ProductJpa productJpa) {
        this.productJpa = productJpa;
    }
}
