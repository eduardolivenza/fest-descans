package com.eolivenza.modules.baseProject.application.configuration.commands.overwrite;


import com.eolivenza.modules.baseProject.application.Command;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OverwriteConfigurationCommand implements Command {

    private String clientIdentifier;
    private String exportPath;
    private String countryIdentifier;
    private int demographicIdentifier;
    private boolean enableAutomaticExport;
    private LocalTime localExecutionTime;
    private Configuration.ReportFrequency reportFrequency;
    private DayOfWeek dayOfWeek;
    private Integer monthDay;

    public OverwriteConfigurationCommand(String clientIdentifier){
        this.clientIdentifier = clientIdentifier;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public String getExportPath() {
        return exportPath;
    }

    public String getCountryIdentifier() {
        return countryIdentifier;
    }

    public int getDemographicIdentifier() {
        return demographicIdentifier;
    }

    public boolean isEnableAutomaticExport() {
        return enableAutomaticExport;
    }

    public LocalTime getLocalExecutionTime() {
        return localExecutionTime;
    }

    public Configuration.ReportFrequency getReportFrequency() {
        return reportFrequency;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getMonthDay() {
        return monthDay;
    }

    public OverwriteConfigurationCommand setExportPath(String exportPath) {
        this.exportPath = exportPath;
        return this;
    }

    public OverwriteConfigurationCommand setCountryIdentifier(String countryIdentifier) {
        this.countryIdentifier = countryIdentifier;
        return this;
    }

    public OverwriteConfigurationCommand setDemographicIdentifier(int demographicIdentifier) {
        this.demographicIdentifier = demographicIdentifier;
        return this;
    }

    public OverwriteConfigurationCommand setEnableAutomaticExport(boolean enableAutomaticExport) {
        this.enableAutomaticExport = enableAutomaticExport;
        return this;
    }

    public OverwriteConfigurationCommand setLocalExecutionTime(LocalTime localExecutionTime) {
        this.localExecutionTime = localExecutionTime;
        return this;
    }

    public OverwriteConfigurationCommand setReportFrequency(Configuration.ReportFrequency reportFrequency) {
        this.reportFrequency = reportFrequency;
        return this;
    }

    public OverwriteConfigurationCommand setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public OverwriteConfigurationCommand setMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
        return this;
    }
}
