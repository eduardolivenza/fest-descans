package com.eolivenza.modules.baseProject.repositories.domain.configuration;

import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(schema = "baseproject_dm_tables", name = "CONFIGURATION")
public class ConfigurationJpa {
    @Id
    public String uuid;

    private String clientIdentifier;

    private String exportPath;

    private String countryIdentifier;

    private int demographicIdentifier;

    @Column(columnDefinition = "BIT")
    private boolean automaticExportEnabled;

    private LocalTime localExecutionTime;

    @Enumerated(EnumType.STRING)
    private Configuration.ReportFrequency reportFrequency;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private Integer monthDay;

    public ConfigurationJpa() {
        //JPA demands it
    }

    public ConfigurationJpa(String uuid, String clientIdentifier, String exportPath, String countryIdentifier, int demographicIdentifier, boolean automaticExportEnabled, LocalTime localExecutionTime, Configuration.ReportFrequency reportFrequency, DayOfWeek dayOfWeek, Integer monthDay) {
        this.uuid = uuid;
        this.clientIdentifier = clientIdentifier;
        this.exportPath = exportPath;
        this.countryIdentifier = countryIdentifier;
        this.demographicIdentifier = demographicIdentifier;
        this.automaticExportEnabled = automaticExportEnabled;
        this.localExecutionTime = localExecutionTime;
        this.reportFrequency = reportFrequency;
        this.dayOfWeek = dayOfWeek;
        this.monthDay = monthDay;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }

    public String getExportPath() {
        return exportPath;
    }

    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
    }

    public String getCountryIdentifier() {
        return countryIdentifier;
    }

    public void setCountryIdentifier(String countryIdentifier) {
        this.countryIdentifier = countryIdentifier;
    }

    public int getDemographicIdentifier() {
        return demographicIdentifier;
    }

    public void setDemographicIdentifier(int demographicIdentifier) {
        this.demographicIdentifier = demographicIdentifier;
    }

    public boolean isAutomaticExportEnabled() {
        return automaticExportEnabled;
    }

    public void setAutomaticExportEnabled(boolean automaticExportEnabled) {
        this.automaticExportEnabled = automaticExportEnabled;
    }

    public LocalTime getLocalExecutionTime() {
        return localExecutionTime;
    }

    public void setLocalExecutionTime(LocalTime localExecutionTime) {
        this.localExecutionTime = localExecutionTime;
    }

    public Configuration.ReportFrequency getReportFrequency() {
        return reportFrequency;
    }

    public void setReportFrequency(Configuration.ReportFrequency reportFrequency) {
        this.reportFrequency = reportFrequency;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
    }
}
