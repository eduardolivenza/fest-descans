package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.suppliers.commands.AddSupplierCommand;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.SupplierResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.SupplierResource;
import com.eolivenza.modules.baseProject.domain.model.suppliers.Supplier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Api(value = "Suppliers")
@RestController
public class SuppliersController {

    @Autowired
    private CommandHandler<AddSupplierCommand> addSupplierCommandCommandHandler;
    @Autowired
    private QueryHandler<Class<Void>, List<Supplier>> getAllSuppliersQueryHandler;
    @Autowired
    private QueryHandler<String, Supplier> getSupplierQueryHandler;
    @Autowired
    private SupplierResourceMapper supplierResourceMapper;

    @Autowired
    public SuppliersController( ) {
    }

    @ApiOperation(value = "Adds a new supplier")
    @PostMapping(path = "/suppliers")
    public void addSupplier(@RequestBody final SupplierResource supplierResource) {
        AddSupplierCommand addSupplierCommand = new AddSupplierCommand(
                supplierResource.companyId,
                supplierResource.companyName ,
                supplierResource.country );
        addSupplierCommandCommandHandler.accept(addSupplierCommand);
    }

    @ApiOperation(value = "Retrieves all suppliers")
    @GetMapping(path = "/suppliers")
    public List<SupplierResource> getSuppliers() {
        List<Supplier> supplierList = getAllSuppliersQueryHandler.apply(Void.TYPE);
        return supplierList.stream().map(supplierResourceMapper::toSecondType).collect(Collectors.toList());
    }


    @ApiOperation(value = "Retrieves one specific supplier")
    @GetMapping(path = "/suppliers/{identifier}")
    public SupplierResource getSupplier(  @PathVariable final String identifier) {
        Supplier supplier = getSupplierQueryHandler.apply(identifier);
        return supplierResourceMapper.toSecondType(supplier);
    }


}
