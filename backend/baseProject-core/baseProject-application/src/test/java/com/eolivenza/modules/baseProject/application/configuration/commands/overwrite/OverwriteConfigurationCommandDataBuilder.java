package com.eolivenza.modules.baseProject.application.configuration.commands.overwrite;

import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OverwriteConfigurationCommandDataBuilder {
    public static OverwriteConfigurationCommandBuilder defaultBuilder() {
        return OverwriteConfigurationCommandBuilder.anOverwriteConfigurationCommand()
                .withClientIdentifier("clientID")
                .withExportPath("c:")
                .withCountryIdentifier("countryID")
                .withDemographicIdentifier(1)
                .withEnableAutomaticExport(true)
                .withExecutionTime(LocalTime.MIDNIGHT)
                .withReportFrequency(Configuration.ReportFrequency.WEEKLY)
                .withDayOfWeek(DayOfWeek.SATURDAY)
                .withMonthDay(null);
    }
}