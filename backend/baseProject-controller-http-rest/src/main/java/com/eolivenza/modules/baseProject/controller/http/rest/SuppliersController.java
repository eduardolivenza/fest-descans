package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.suppliers.commands.commands.AddSupplierCommand;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.SupplierResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "Suppliers")
@RestController
public class SuppliersController {

    private final CommandHandler<AddSupplierCommand> addSupplierCommandCommandHandler;

    @Autowired
    public SuppliersController(
            CommandHandler<AddSupplierCommand> addSupplierCommandCommandHandler)   {
           this.addSupplierCommandCommandHandler = addSupplierCommandCommandHandler;
    }



    @ApiOperation(value = "Adds a new supplier")
    @PostMapping(path = "/suppliers")
    public void addCategory(@RequestBody final SupplierResource supplierResource) {

        AddSupplierCommand addSupplierCommand = new AddSupplierCommand(
                supplierResource.companyName ,
                supplierResource.country );

        addSupplierCommandCommandHandler.accept(addSupplierCommand);
    }




}
