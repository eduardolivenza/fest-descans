package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

import com.eolivenza.modules.baseProject.controller.http.rest.resources.ConfigurationResource;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class ConfigurationResourceMapper implements ResourceMapper<Configuration, ConfigurationResource> {
    public Configuration toFirstType(ConfigurationResource object) {
        LocalTime localExecutionTime = (object.localExecutionTime == null) ? null : LocalTime.parse(object.localExecutionTime, DateTimeFormatter.ofPattern("HH:mm"));
        DayOfWeek dayOfWeek = (object.dayOfWeek == null) ? null : DayOfWeek.valueOf(object.dayOfWeek);
        Configuration.ReportFrequency reportFrequency = (object.reportFrequency == null) ? null : Configuration.ReportFrequency.valueOf(object.reportFrequency);

        return new Configuration(
                object.clientIdentifier,
                object.exportPath,
                object.countryIdentifier,
                object.demographicIdentifier,
                object.automaticExportEnabled,
                localExecutionTime,
                reportFrequency,
                dayOfWeek,
                object.monthDay);
    }

    @Override
    public ConfigurationResource toSecondType(Configuration object) {
        String dayOfWeek = (object.getDayOfWeek() == null) ? null : object.getDayOfWeek().toString();
        String reportFrequency = (object.getReportFrequency() == null) ? null : object.getReportFrequency().toString();
        String localExecutionTime = (object.getLocalExecutionTime() == null) ? null : object.getLocalExecutionTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        return new ConfigurationResource(
                object.getUUID().toString(),
                object.getClientIdentifier(),
                object.getExportPath(),
                object.getCountryIdentifier(),
                object.getDemographicIdentifier(),
                object.isAutomaticExportEnabled(),
                localExecutionTime,
                reportFrequency,
                dayOfWeek,
                object.getMonthDay()
        );
    }
}
