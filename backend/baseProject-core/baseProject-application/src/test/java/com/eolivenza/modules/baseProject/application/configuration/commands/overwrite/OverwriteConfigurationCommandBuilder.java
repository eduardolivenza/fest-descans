package com.eolivenza.modules.baseProject.application.configuration.commands.overwrite;

import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;

import java.time.DayOfWeek;
import java.time.LocalTime;

public final class OverwriteConfigurationCommandBuilder {
    private String clientIdentifier;
    private String exportPath;
    private String countryIdentifier;
    private Integer demographicIdentifier;
    private boolean enableAutomaticExport;
    private LocalTime executionTime;
    private Configuration.ReportFrequency reportFrequency;
    private DayOfWeek dayOfWeek;
    private Integer monthDay;

    private OverwriteConfigurationCommandBuilder() {
    }

    public static OverwriteConfigurationCommandBuilder anOverwriteConfigurationCommand() {
        return new OverwriteConfigurationCommandBuilder();
    }

    public OverwriteConfigurationCommandBuilder withClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
        return this;
    }

    public OverwriteConfigurationCommandBuilder withExportPath(String exportPath) {
        this.exportPath = exportPath;
        return this;
    }

    public OverwriteConfigurationCommandBuilder withCountryIdentifier(String countryIdentifier) {
        this.countryIdentifier = countryIdentifier;
        return this;
    }

    public OverwriteConfigurationCommandBuilder withDemographicIdentifier(Integer demographicIdentifier) {
        this.demographicIdentifier = demographicIdentifier;
        return this;
    }

    public OverwriteConfigurationCommandBuilder withEnableAutomaticExport(boolean enableAutomaticExport) {
        this.enableAutomaticExport = enableAutomaticExport;
        return this;
    }

    public OverwriteConfigurationCommandBuilder withExecutionTime(LocalTime executionTime) {
        this.executionTime = executionTime;
        return this;
    }

    public OverwriteConfigurationCommandBuilder withReportFrequency(Configuration.ReportFrequency reportFrequency) {
        this.reportFrequency = reportFrequency;
        return this;
    }

    public OverwriteConfigurationCommandBuilder withDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public OverwriteConfigurationCommandBuilder withMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
        return this;
    }

    public OverwriteConfigurationCommand build() {
        OverwriteConfigurationCommand command = new OverwriteConfigurationCommand(clientIdentifier)
                .setCountryIdentifier(countryIdentifier)
                .setExportPath(exportPath)
                .setDemographicIdentifier(demographicIdentifier)
                .setEnableAutomaticExport(enableAutomaticExport)
                .setDayOfWeek(dayOfWeek)
                .setLocalExecutionTime(executionTime)
                .setReportFrequency(reportFrequency)
                .setMonthDay(monthDay);
        return command;
    }
}
