package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Configuration", description = "Text for describing the configuration")
public class ConfigurationResource {

    @ApiModelProperty(value = "UUID", example = "cce8a203-e594-4c18-96bb-cc21eb2ab773", readOnly = true)
    public String uuid;

    @ApiModelProperty(required = true, value = "Relates to the highest level customer identification. The CustomerID value can come from a global SAP, customer master record, local identifier", example = "Bayley")
    public String clientIdentifier;

    @ApiModelProperty(required = true, value = "The path on the filesystem where to generate the report file. It must exists.", example = "d:")
    public String exportPath;

    @ApiModelProperty(required = true, value = "Unique identifier related to the sales organization or the country affiliate", example = "34")
    public String countryIdentifier;

    @ApiModelProperty(required = true, value = "An integer that identifies the demographic", example = "8")
    public int demographicIdentifier;

    @ApiModelProperty(required = true, value = "It indicates if the export is automatic.", example = "true")
    public boolean automaticExportEnabled;

    @ApiModelProperty(required = true, value = "It indicates the time of day the report is executed.", example = "23:59")
    public String localExecutionTime;

    @ApiModelProperty(required = true, value = "It indicates the report frequency.", example = "WEEKLY")
    public String reportFrequency;

    @ApiModelProperty(required = true, value = "It indicates the day of the week.", example = "MONDAY")
    public String dayOfWeek;

    @ApiModelProperty(required = true, value = "It indicates the day of the month that a report is generated on.", example = "null")
    public Integer monthDay;

    public ConfigurationResource() {
    }

    public ConfigurationResource(String uuid, String clientIdentifier, String exportPath, String countryIdentifier, int demographicIdentifier, boolean automaticExportEnabled, String localExecutionTime, String reportFrequency, String dayOfWeek, Integer monthDay) {
        this.uuid = uuid;
        this.localExecutionTime = localExecutionTime;
        this.automaticExportEnabled = automaticExportEnabled;
        this.clientIdentifier = clientIdentifier;
        this.exportPath = exportPath;
        this.countryIdentifier = countryIdentifier;
        this.demographicIdentifier = demographicIdentifier;
        this.reportFrequency = reportFrequency;
        this.dayOfWeek = dayOfWeek;
        this.monthDay = monthDay;
    }
}
