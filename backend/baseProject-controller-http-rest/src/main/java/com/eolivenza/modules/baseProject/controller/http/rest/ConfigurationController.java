package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.configuration.commands.overwrite.OverwriteConfigurationCommand;
import com.eolivenza.modules.baseProject.application.security.BaseProjectGrantPermission;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.ConfigurationResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ConfigurationResource;
import com.eolivenza.modules.baseProject.domain.model.configuration.ProductType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;


@Api(value = "ProductType")
@RestController
public class ConfigurationController {

    private final ConfigurationResourceMapper configurationResourceMapper;
    private final QueryHandler<Class<Void>, Optional<ProductType>> getConfigurationQueryHandler;
    private final CommandHandler<OverwriteConfigurationCommand> overwriteConfigurationCommandHandler;

    /**
     * ConfigurationController Constructor
     *
     * @param getConfigurationQueryHandler         query handler for the getConfiguration operation
     * @param configurationResourceMapper          mapper for the configuration resource
     * @param overwriteConfigurationCommandHandler command handler for the overwriteConfiguration operation
     */
    @Autowired
    public ConfigurationController(
            QueryHandler<Class<Void>, Optional<ProductType>> getConfigurationQueryHandler,
            ConfigurationResourceMapper configurationResourceMapper,
            CommandHandler<OverwriteConfigurationCommand> overwriteConfigurationCommandHandler) {
        this.getConfigurationQueryHandler = getConfigurationQueryHandler;
        this.configurationResourceMapper = configurationResourceMapper;
        this.overwriteConfigurationCommandHandler = overwriteConfigurationCommandHandler;
    }

    /**
     * Retrieve the general configuration
     *
     * @return {@link ConfigurationResource} ConfigurationResource}
     */
    @ApiOperation(value = "Retrieve the general configuration")
    @GetMapping(path = "/configurations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RolesAllowed(BaseProjectGrantPermission.baseProject_SCREEN)
    public ResponseEntity<ConfigurationResource> retrieveConfiguration() {
        Optional<ProductType> maybeConfiguration = getConfigurationQueryHandler.apply(Void.TYPE);

        if (!maybeConfiguration.isPresent()) {
            return ResponseEntity.ok().build();
        }

        ProductType productType = maybeConfiguration.get();
        ConfigurationResource configurationResource = configurationResourceMapper.toSecondType(productType);
        return ResponseEntity.ok(configurationResource);
    }

    /**
     * Overwrites the general configuration.
     *
     * @param configurationResource new configuration Resource
     */
    @ApiOperation(value = "Update the general configuration")
    @PutMapping(path = "/configurations")
    @RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public void overwriteConfiguration(
            @RequestBody ConfigurationResource configurationResource) {
        ProductType productType = configurationResourceMapper.toFirstType(configurationResource);
        OverwriteConfigurationCommand command = new OverwriteConfigurationCommand(productType.getClientIdentifier())
                .setCountryIdentifier(productType.getCountryIdentifier())
                .setExportPath(productType.getExportPath())
                .setDemographicIdentifier(productType.getDemographicIdentifier())
                .setEnableAutomaticExport(productType.isAutomaticExportEnabled())
                .setDayOfWeek(productType.getDayOfWeek())
                .setLocalExecutionTime(productType.getLocalExecutionTime())
                .setReportFrequency(productType.getReportFrequency())
                .setMonthDay(productType.getMonthDay());
        overwriteConfigurationCommandHandler.accept(command);
    }

    @ApiOperation(value = "Check app")
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RolesAllowed(BaseProjectGrantPermission.baseProject_SCREEN)
    public String helloWorld() {
        return "Hello cariño, I love you ";
    }
    //TR43W3843HNHSQ
}
