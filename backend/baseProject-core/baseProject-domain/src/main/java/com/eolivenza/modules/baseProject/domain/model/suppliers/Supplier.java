package com.eolivenza.modules.baseProject.domain.model.suppliers;

import com.eolivenza.modules.baseProject.domain.model.Entity;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Supplier extends Entity<Supplier> {

    @NotNull
    private UUID uuid;

    @NotNull
    private String companyName;

    private String country;

    public Supplier( String companyName, String country){
        this(UUID.randomUUID(),  companyName, country);
    }

    public Supplier(UUID uuid,   String companyName,String country){
        this.uuid = uuid;
        this.companyName = companyName;
        this.country= country;

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
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

    public void overwriteWith(Supplier otherUser) {
        setUuid(otherUser.getUuid());
        setCompanyName(otherUser.getCompanyName() );
        setCountry(otherUser.getCountry());
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
