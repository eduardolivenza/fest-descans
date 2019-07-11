package com.eolivenza.modules.baseProject.domain.model.configuration;

import com.eolivenza.modules.baseProject.domain.model.Entity;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class ProductType extends Entity<ProductType> {


    public static final UUID CONFIGURATION_UUID = UUID.fromString("cce8a203-e594-4c18-96bb-cc21eb2ab773");

    @NotNull
    private UUID uuid;

    @NotBlank
    private String productName;


    //constructors
    public ProductType() {
        setUUID(new UUID(CONFIGURATION_UUID.getMostSignificantBits(), CONFIGURATION_UUID.getLeastSignificantBits()));
    }

    public ProductType(String productName) {
        this();
        setProductName(productName);
    }
    /**
     * Overwrites all property values except UUID
     *
     * @param anotherProductType the configuration used to overwrite all property values except UUID
     */
    public void overwriteWith(ProductType anotherProductType) {
        setProductName(anotherProductType.getProductName());
    }

    private void setUUID(UUID uuid) {
        this.uuid = uuid;
        validateProperty(this, "uuid");
    }

    public UUID getUUID() {
        return new UUID(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public int hashCodeCalculation() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean hasSameIdentity(ProductType other) {
        return Objects.equals(uuid, other.uuid);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && hasSameIdentity((ProductType) o);
    }

    @Override
    public int hashCode() { return super.hashCode();}

}
