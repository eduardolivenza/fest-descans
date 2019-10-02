package com.eolivenza.modules.baseProject.application.repositories;


import com.eolivenza.modules.baseProject.application.repositories.generics.Repository;
import com.eolivenza.modules.baseProject.application.repositories.generics.RetrieveAllRepository;
import com.eolivenza.modules.baseProject.application.repositories.generics.RetrieveRepository;
import com.eolivenza.modules.baseProject.domain.model.products.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductsRepository extends Repository<Product, String>, RetrieveRepository<Product, String>, RetrieveAllRepository<Product> {

    boolean existsByProductIdentifier(String externalIdentifier);

    boolean existsByuuid(String internalIdentifier);

    Optional<Product> retrieveByProductIdentifier(String productIdentifier);

}
