package com.eolivenza.modules.baseProject.domain.model.configuration;

import java.time.DayOfWeek;
import java.time.LocalTime;

public final class ConfigurationBuilder {
    private String clientIdentifier;
    private String exportPath;
    private String countryIdentifier;
    private Integer demographicIdentifier;
    private boolean automaticExportEnabled;
    private LocalTime localExecutionTime;
    private Configuration.ReportFrequency reportFrequency;
    private DayOfWeek dayOfWeek;
    private Integer monthDay;

    private ConfigurationBuilder() {
    }

    public static ConfigurationBuilder aConfiguration() {
        return new ConfigurationBuilder();
    }

    public ConfigurationBuilder withClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
        return this;
    }

    public ConfigurationBuilder withExportPath(String exportPath) {
        this.exportPath = exportPath;
        return this;
    }

    public ConfigurationBuilder withCountryIdentifier(String countryIdentifier) {
        this.countryIdentifier = countryIdentifier;
        return this;
    }

    public ConfigurationBuilder withDemographicIdentifier(int demographicIdentifier) {
        this.demographicIdentifier = demographicIdentifier;
        return this;
    }

    public ConfigurationBuilder withAutomaticExportEnabled(boolean enableAutomaticExport) {
        this.automaticExportEnabled = enableAutomaticExport;
        return this;
    }

    public ConfigurationBuilder withLocalExecutionTime(LocalTime localExecutionTime) {
        this.localExecutionTime = localExecutionTime;
        return this;
    }

    public ConfigurationBuilder withReportFrequency(Configuration.ReportFrequency reportFrequency) {
        this.reportFrequency = reportFrequency;
        return this;
    }

    public ConfigurationBuilder withDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public ConfigurationBuilder withMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
        return this;
    }

    public Configuration build() {
        return new Configuration(clientIdentifier, exportPath, countryIdentifier, demographicIdentifier, automaticExportEnabled, localExecutionTime, reportFrequency, dayOfWeek, monthDay);
    }
}
