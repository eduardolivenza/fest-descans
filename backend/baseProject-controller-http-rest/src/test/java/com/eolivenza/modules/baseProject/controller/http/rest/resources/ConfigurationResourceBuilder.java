package com.eolivenza.modules.baseProject.controller.http.rest.resources;

public final class ConfigurationResourceBuilder {
    public String uuid;
    public String clientIdentifier;
    public String exportPath;
    public String countryIdentifier;
    public int demographicIdentifier;
    public boolean automaticExportEnabled;
    public String localExecutionTime;
    public String reportFrequency;
    public String dayOfWeek;
    public Integer monthDay;

    private ConfigurationResourceBuilder() {
    }

    public static ConfigurationResourceBuilder aConfigurationResource() {
        return new ConfigurationResourceBuilder();
    }

    public ConfigurationResourceBuilder withUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public ConfigurationResourceBuilder withClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
        return this;
    }

    public ConfigurationResourceBuilder withExportPath(String exportPath) {
        this.exportPath = exportPath;
        return this;
    }

    public ConfigurationResourceBuilder withCountryIdentifier(String countryIdentifier) {
        this.countryIdentifier = countryIdentifier;
        return this;
    }

    public ConfigurationResourceBuilder withDemographicIdentifier(int demographicIdentifier) {
        this.demographicIdentifier = demographicIdentifier;
        return this;
    }

    public ConfigurationResourceBuilder withAutomaticExportEnabled(boolean automaticExportEnabled) {
        this.automaticExportEnabled = automaticExportEnabled;
        return this;
    }

    public ConfigurationResourceBuilder withLocalExecutionTime(String localExecutionTime) {
        this.localExecutionTime = localExecutionTime;
        return this;
    }

    public ConfigurationResourceBuilder withReportFrequency(String reportFrequency) {
        this.reportFrequency = reportFrequency;
        return this;
    }

    public ConfigurationResourceBuilder withDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public ConfigurationResourceBuilder withMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
        return this;
    }

    public ConfigurationResource build() {
        return new ConfigurationResource(uuid, clientIdentifier, exportPath, countryIdentifier, demographicIdentifier, automaticExportEnabled, localExecutionTime, reportFrequency, dayOfWeek, monthDay);
    }
}
