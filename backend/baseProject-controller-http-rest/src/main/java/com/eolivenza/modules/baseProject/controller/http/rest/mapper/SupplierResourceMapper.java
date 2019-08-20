package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.SupplierResource;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierResourceMapper implements ResourceMapper<Supplier, SupplierResource> {

    @Override
    public Supplier toFirstType(SupplierResource object) {
        return new Supplier(object.companyId, object.companyName, object.country);
    }

    @Override
    public SupplierResource toSecondType(Supplier object) {
        return new SupplierResource(
            object.getExternalIdentifier(),
            object.getCompanyName(),
            object.getCountry());
    }
}
