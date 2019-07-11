package com.eolivenza.modules.baseProject.domain.model.configuration;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ConfigurationDataBuilder {
    public static ConfigurationBuilder defaultBuilder() {
        return ConfigurationBuilder.aConfiguration()
                .withClientIdentifier("clientID")
                .withExportPath("d:/")
                .withCountryIdentifier("countryID")
                .withDemographicIdentifier(1)
                .withAutomaticExportEnabled(false);
    }

    public static ConfigurationBuilder defaultWithAutomaticExportEnabledBuilder() {
        return ConfigurationBuilder.aConfiguration()
                .withClientIdentifier("clientID")
                .withExportPath("d:/")
                .withCountryIdentifier("countryID")
                .withDemographicIdentifier(1)
                .withAutomaticExportEnabled(true)
                .withLocalExecutionTime(LocalTime.NOON)
                .withReportFrequency(Configuration.ReportFrequency.WEEKLY)
                .withDayOfWeek(DayOfWeek.SATURDAY)
                .withMonthDay(null);
    }
}
