package com.eolivenza.modules.baseProject.domain.model.configuration;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ConfigurationTest {
    @Test
    public void all_instances_of_configuration_should_have_correct_configuration_id() {
        Configuration configuration = new Configuration();

        assertThat(configuration.getUUID()).isEqualTo(Configuration.CONFIGURATION_UUID);
    }

    @Test
    public void check_report_frequency_is_set_correctly() {
        Configuration configuration = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                .build();

        configuration.setDailyFrequency(LocalTime.NOON);
        assertThat(configuration.getReportFrequency()).isEqualTo(Configuration.ReportFrequency.DAILY);

        configuration.setWeeklyFrequency(DayOfWeek.MONDAY, LocalTime.NOON);
        assertThat(configuration.getReportFrequency()).isEqualTo(Configuration.ReportFrequency.WEEKLY);

        configuration.setMonthlyFrequency(1, LocalTime.NOON);
        assertThat(configuration.getReportFrequency()).isEqualTo(Configuration.ReportFrequency.MONTHLY);
    }

    @Test
    public void disable_automatic_export() {
        Configuration configuration = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                .withAutomaticExportEnabled(true)
                .build();

        configuration.disableAutomaticExport();

        assertThat(configuration.isAutomaticExportEnabled()).isEqualTo(false);
    }

    @Test
    public void overwrite_an_instrument_set_the_new_values_except_UUID() {
        String clientId = "clientId";
        String exportPath = "c:";
        String countryIdentifier = "ESP";
        int demographicIdentifier = 1;
        boolean automaticExportEnabled = true;
        LocalTime executionTime = LocalTime.MIDNIGHT;
        Configuration.ReportFrequency reportFrequency = Configuration.ReportFrequency.WEEKLY;
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        Integer monthDay = null;

        Configuration configuration1 = ConfigurationDataBuilder
                .defaultBuilder()
                .withClientIdentifier("client")
                .withExportPath("aPath")
                .withCountryIdentifier("USA")
                .withDemographicIdentifier(2)
                .withAutomaticExportEnabled(true)
                .withLocalExecutionTime(LocalTime.NOON)
                .withReportFrequency(Configuration.ReportFrequency.DAILY)
                .withDayOfWeek(null)
                .withMonthDay(null)
                .build();

        Configuration configuration2 = ConfigurationDataBuilder
                .defaultBuilder()
                .withClientIdentifier(clientId)
                .withExportPath(exportPath)
                .withCountryIdentifier(countryIdentifier)
                .withDemographicIdentifier(demographicIdentifier)
                .withAutomaticExportEnabled(automaticExportEnabled)
                .withLocalExecutionTime(executionTime)
                .withReportFrequency(reportFrequency)
                .withDayOfWeek(dayOfWeek)
                .withMonthDay(monthDay)
                .build();

        configuration1.overwriteWith(configuration2);

        assertThat(configuration1).isEqualToIgnoringGivenFields(configuration2, "uuid");
    }

    @Test
    public void hasSameIdentity_returns_true() {
        Configuration configuration1 = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build();
        Configuration configuration2 = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build();

        assertThat(configuration1.hasSameIdentity(configuration2)).isTrue();
    }

    @Test
    public void hashCodeCalculation_has_correct_value() {
        String clientIdentifier = "clientId";
        String exportPath = "c:";
        String countryIdentifier = "ESP";
        Integer demographicIdentifier = 1;

        Configuration configuration = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                .withClientIdentifier(clientIdentifier)
                .withExportPath(exportPath)
                .withCountryIdentifier(countryIdentifier)
                .withDemographicIdentifier(demographicIdentifier)
                .build();

        assertThat(configuration.hashCodeCalculation()).isNotNull();
    }

    @Test
    public void create_a_configuration_with_automatic_export_enabled_and_report_frequency_as_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withAutomaticExportEnabled(true)
                        .withReportFrequency(null)
                        .build());
    }

    @Test
    public void create_a_configuration_with_automatic_export_enabled_and_localExecutionTime_as_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withAutomaticExportEnabled(true)
                        .withReportFrequency(Configuration.ReportFrequency.DAILY)
                        .withDayOfWeek(null)
                        .withMonthDay(null)
                        .withLocalExecutionTime(null)
                        .build());
    }

    @Test
    public void create_a_configuration_with_automatic_export_disabled_and_report_frequency_as_not_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withAutomaticExportEnabled(false)
                        .withReportFrequency(Configuration.ReportFrequency.WEEKLY)
                        .build());
    }

    @Test
    public void create_a_configuration_with_daily_report_frequency_and_dayOfWeek_as_not_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(Configuration.ReportFrequency.DAILY)
                        .withDayOfWeek(DayOfWeek.MONDAY)
                        .build());
    }

    @Test
    public void create_a_configuration_with_daily_report_frequency_and_monthDay_as_not_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(Configuration.ReportFrequency.DAILY)
                        .withMonthDay(1)
                        .build());
    }

    @Test
    public void create_a_configuration_with_weekly_report_frequency_and_dayOfWeek_as_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(Configuration.ReportFrequency.WEEKLY)
                        .withDayOfWeek(null)
                        .build());
    }

    @Test
    public void create_a_configuration_with_weekly_report_frequency_and_monthDay_as_not_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(Configuration.ReportFrequency.WEEKLY)
                        .withMonthDay(1)
                        .build());
    }

    @Test
    public void create_a_configuration_with_monthly_report_frequency_and_dayOfWeek_as_not_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(Configuration.ReportFrequency.MONTHLY)
                        .withDayOfWeek(DayOfWeek.MONDAY)
                        .build());
    }

    @Test
    public void create_a_configuration_with_monthly_report_frequency_and_monthDay_as_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(Configuration.ReportFrequency.MONTHLY)
                        .withMonthDay(null)
                        .build());
    }

    @Test
    public void set_weekly_frequency_with_dayOfWeek_as_null_should_throw_exception() {
        Configuration configuration = ConfigurationDataBuilder
                .defaultBuilder()
                .build();

        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> configuration.setWeeklyFrequency(null, LocalTime.NOON));
    }

    @Test
    public void set_monthly_frequency_with_monthDay_as_null_should_throw_exception() {
        Configuration configuration = ConfigurationDataBuilder
                .defaultBuilder()
                .build();

        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> configuration.setMonthlyFrequency(null, LocalTime.NOON));
    }
}