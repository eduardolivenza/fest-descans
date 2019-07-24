package com.eolivenza.modules.baseProject.repositories.domain.suppliers;

import com.eolivenza.modules.baseProject.application.repositories.SuppliersRepository;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class SuppliersRepositoryImpl implements SuppliersRepository {

    private final SupplierMapper supplierMapper;
    private final SuppliersRepositoryJpaSpringData suppliersRepositoryJpaSpringData;

    @Autowired
    public SuppliersRepositoryImpl(SupplierMapper supplierMapper, SuppliersRepositoryJpaSpringData productsRepositoryJpaSpringData) {
        this.supplierMapper = supplierMapper;
        this.suppliersRepositoryJpaSpringData = productsRepositoryJpaSpringData;
    }

    @Override
    public Supplier update(Supplier entity) {
        SupplierJpa SupplierJpa = supplierMapper.fromDomain(entity);
        SupplierJpa = suppliersRepositoryJpaSpringData.saveAndFlush(SupplierJpa);
        return supplierMapper.toDomain(SupplierJpa);
    }

    @Override
    public Supplier retrieve(String uuid) {
        Supplier supplier = null;
        Optional<SupplierJpa> optionalUserJpa =
                suppliersRepositoryJpaSpringData.findById(uuid);
        if (optionalUserJpa.isPresent())
            supplier = supplierMapper.toDomain(optionalUserJpa.get());
        return supplier;
    }

    @Override
    public List<Supplier> retrieveAll() {
        List<SupplierJpa> usersJpaList = suppliersRepositoryJpaSpringData.findAll();
        return usersJpaList.stream().map(supplierMapper::toDomain).collect(Collectors.toList());
    }


    @Override
    public Supplier create(Supplier entity) {
        try {
            SupplierJpa SupplierJpa = supplierMapper.fromDomain(entity);
            SupplierJpa = suppliersRepositoryJpaSpringData.saveAndFlush(SupplierJpa);
            return supplierMapper.toDomain(SupplierJpa);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        suppliersRepositoryJpaSpringData.deleteById(uuid);
    }
}
