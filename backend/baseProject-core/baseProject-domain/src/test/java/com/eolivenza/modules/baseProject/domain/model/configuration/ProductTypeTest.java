package com.eolivenza.modules.baseProject.domain.model.configuration;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ProductTypeTest {
    @Test
    public void all_instances_of_configuration_should_have_correct_configuration_id() {
        ProductType productType = new ProductType();

        assertThat(productType.getUUID()).isEqualTo(ProductType.CONFIGURATION_UUID);
    }

    @Test
    public void check_report_frequency_is_set_correctly() {
        ProductType productType = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                .build();

        productType.setDailyFrequency(LocalTime.NOON);
        assertThat(productType.getReportFrequency()).isEqualTo(ProductType.ReportFrequency.DAILY);

        productType.setWeeklyFrequency(DayOfWeek.MONDAY, LocalTime.NOON);
        assertThat(productType.getReportFrequency()).isEqualTo(ProductType.ReportFrequency.WEEKLY);

        productType.setMonthlyFrequency(1, LocalTime.NOON);
        assertThat(productType.getReportFrequency()).isEqualTo(ProductType.ReportFrequency.MONTHLY);
    }

    @Test
    public void disable_automatic_export() {
        ProductType productType = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                .withAutomaticExportEnabled(true)
                .build();

        productType.disableAutomaticExport();

        assertThat(productType.isAutomaticExportEnabled()).isEqualTo(false);
    }

    @Test
    public void overwrite_an_instrument_set_the_new_values_except_UUID() {
        String clientId = "clientId";
        String exportPath = "c:";
        String countryIdentifier = "ESP";
        int demographicIdentifier = 1;
        boolean automaticExportEnabled = true;
        LocalTime executionTime = LocalTime.MIDNIGHT;
        ProductType.ReportFrequency reportFrequency = ProductType.ReportFrequency.WEEKLY;
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        Integer monthDay = null;

        ProductType productType1 = ConfigurationDataBuilder
                .defaultBuilder()
                .withClientIdentifier("client")
                .withExportPath("aPath")
                .withCountryIdentifier("USA")
                .withDemographicIdentifier(2)
                .withAutomaticExportEnabled(true)
                .withLocalExecutionTime(LocalTime.NOON)
                .withReportFrequency(ProductType.ReportFrequency.DAILY)
                .withDayOfWeek(null)
                .withMonthDay(null)
                .build();

        ProductType productType2 = ConfigurationDataBuilder
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

        productType1.overwriteWith(productType2);

        assertThat(productType1).isEqualToIgnoringGivenFields(productType2, "uuid");
    }

    @Test
    public void hasSameIdentity_returns_true() {
        ProductType productType1 = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build();
        ProductType productType2 = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder().build();

        assertThat(productType1.hasSameIdentity(productType2)).isTrue();
    }

    @Test
    public void hashCodeCalculation_has_correct_value() {
        String clientIdentifier = "clientId";
        String exportPath = "c:";
        String countryIdentifier = "ESP";
        Integer demographicIdentifier = 1;

        ProductType productType = ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                .withClientIdentifier(clientIdentifier)
                .withExportPath(exportPath)
                .withCountryIdentifier(countryIdentifier)
                .withDemographicIdentifier(demographicIdentifier)
                .build();

        assertThat(productType.hashCodeCalculation()).isNotNull();
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
                        .withReportFrequency(ProductType.ReportFrequency.DAILY)
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
                        .withReportFrequency(ProductType.ReportFrequency.WEEKLY)
                        .build());
    }

    @Test
    public void create_a_configuration_with_daily_report_frequency_and_dayOfWeek_as_not_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(ProductType.ReportFrequency.DAILY)
                        .withDayOfWeek(DayOfWeek.MONDAY)
                        .build());
    }

    @Test
    public void create_a_configuration_with_daily_report_frequency_and_monthDay_as_not_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(ProductType.ReportFrequency.DAILY)
                        .withMonthDay(1)
                        .build());
    }

    @Test
    public void create_a_configuration_with_weekly_report_frequency_and_dayOfWeek_as_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(ProductType.ReportFrequency.WEEKLY)
                        .withDayOfWeek(null)
                        .build());
    }

    @Test
    public void create_a_configuration_with_weekly_report_frequency_and_monthDay_as_not_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(ProductType.ReportFrequency.WEEKLY)
                        .withMonthDay(1)
                        .build());
    }

    @Test
    public void create_a_configuration_with_monthly_report_frequency_and_dayOfWeek_as_not_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(ProductType.ReportFrequency.MONTHLY)
                        .withDayOfWeek(DayOfWeek.MONDAY)
                        .build());
    }

    @Test
    public void create_a_configuration_with_monthly_report_frequency_and_monthDay_as_null_should_throw_exception() {
        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> ConfigurationDataBuilder.defaultWithAutomaticExportEnabledBuilder()
                        .withReportFrequency(ProductType.ReportFrequency.MONTHLY)
                        .withMonthDay(null)
                        .build());
    }

    @Test
    public void set_weekly_frequency_with_dayOfWeek_as_null_should_throw_exception() {
        ProductType productType = ConfigurationDataBuilder
                .defaultBuilder()
                .build();

        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> productType.setWeeklyFrequency(null, LocalTime.NOON));
    }

    @Test
    public void set_monthly_frequency_with_monthDay_as_null_should_throw_exception() {
        ProductType productType = ConfigurationDataBuilder
                .defaultBuilder()
                .build();

        assertThatExceptionOfType(InvalidConfigurationStateException.class)
                .isThrownBy(() -> productType.setMonthlyFrequency(null, LocalTime.NOON));
    }
}