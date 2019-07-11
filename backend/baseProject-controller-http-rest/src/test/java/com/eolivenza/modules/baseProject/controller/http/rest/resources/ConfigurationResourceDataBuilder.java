package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ConfigurationResourceDataBuilder {
    public static ConfigurationResourceBuilder defaultBuilder() {
        return ConfigurationResourceBuilder.aConfigurationResource()
                .withUuid(Configuration.CONFIGURATION_UUID.toString())
                .withClientIdentifier("clientId")
                .withExportPath("d:/")
                .withCountryIdentifier("countryID")
                .withDemographicIdentifier(1)
                .withAutomaticExportEnabled(true)
                .withLocalExecutionTime(LocalTime.NOON.toString())
                .withReportFrequency(Configuration.ReportFrequency.WEEKLY.toString())
                .withDayOfWeek(DayOfWeek.SATURDAY.toString())
                .withMonthDay(null);
    }
}
