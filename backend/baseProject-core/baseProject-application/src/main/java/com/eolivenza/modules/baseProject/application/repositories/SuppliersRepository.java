package com.eolivenza.modules.baseProject.application.repositories;


import com.eolivenza.modules.baseProject.application.repositories.generics.Repository;
import com.eolivenza.modules.baseProject.application.repositories.generics.RetrieveAllRepository;
import com.eolivenza.modules.baseProject.application.repositories.generics.RetrieveRepository;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;

public interface SuppliersRepository extends Repository<Supplier, String>, RetrieveRepository<Supplier, String>, RetrieveAllRepository<Supplier> {

    boolean existsBySupplierIdentifier(String externalIdentifier);

}
