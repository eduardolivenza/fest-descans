package com.eolivenza.modules.baseProject.domain.model.configuration;

import com.eolivenza.modules.baseProject.domain.model.Entity;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class Configuration extends Entity<Configuration> {

    public enum ReportFrequency {
        DAILY, WEEKLY, MONTHLY
    }

    public static final UUID CONFIGURATION_UUID = UUID.fromString("cce8a203-e594-4c18-96bb-cc21eb2ab773");

    @NotNull
    private UUID uuid;
    @NotBlank
    private String clientIdentifier;
    @NotBlank
    private String exportPath;
    @NotBlank
    private String countryIdentifier;
    @NotNull
    @Min(1)
    private int demographicIdentifier;
    @NotNull
    private boolean automaticExportEnabled;
    private LocalTime localExecutionTime;
    private ReportFrequency reportFrequency;
    private DayOfWeek dayOfWeek;
    @Range(min = 1, max = 31)
    private Integer monthDay;

    //constructors
    public Configuration() {
        setUUID(new UUID(CONFIGURATION_UUID.getMostSignificantBits(), CONFIGURATION_UUID.getLeastSignificantBits()));
    }

    public Configuration(String clientIdentifier, String exportPath, String countryIdentifier, int demographicIdentifier, boolean automaticExportEnabled, LocalTime localExecutionTime, ReportFrequency reportFrequency, DayOfWeek dayOfWeek, Integer monthDay) {
        this();

        setLocalExecutionTime(localExecutionTime == null ? null : LocalTime.of(localExecutionTime.getHour(), localExecutionTime.getMinute(), localExecutionTime.getSecond(), localExecutionTime.getNano()));
        setAutomaticExportEnabled(automaticExportEnabled);
        setClientIdentifier(clientIdentifier);
        setExportPath(exportPath);
        setCountryIdentifier(countryIdentifier);
        setDemographicIdentifier(demographicIdentifier);
        setReportFrequency(reportFrequency);
        setDayOfWeek(dayOfWeek);
        setMonthDay(monthDay == null ? null : monthDay);

        validateInitialState();
    }

    private void validateInitialState() {
        if (automaticExportEnabled && (reportFrequency == null || localExecutionTime == null)) {
            throw new InvalidConfigurationStateException(ConfigurationExceptionCodes.NULL_EXPORT_FREQUENCY_OR_TIME);
        }
        if (!automaticExportEnabled && (reportFrequency != null || dayOfWeek != null || monthDay != null)) {
            throw new InvalidConfigurationStateException(ConfigurationExceptionCodes.DISABLED_EXPORT_BUT_FREQUENCY_SET);
        }
        if (ReportFrequency.DAILY.equals(reportFrequency) && (dayOfWeek != null || monthDay != null)) {
            throw new InvalidConfigurationStateException(ConfigurationExceptionCodes.DAILY_FREQUENCY_BUT_CONFIGURATION_NOT_CORRECT);
        }
        if (ReportFrequency.WEEKLY.equals(reportFrequency) && (dayOfWeek == null || monthDay != null)) {
            throw new InvalidConfigurationStateException(ConfigurationExceptionCodes.WEEKLY_FREQUENCY_BUT_CONFIGURATION_NOT_CORRECT);
        }
        if (ReportFrequency.MONTHLY.equals(reportFrequency) && (dayOfWeek != null || monthDay == null)) {
            throw new InvalidConfigurationStateException(ConfigurationExceptionCodes.MONTHLY_FREQUENCY_BUT_CONFIGURATION_NOT_CORRECT);
        }
    }

    //non-private modifiers

    /**
     * Overwrites all property values except UUID
     *
     * @param anotherConfiguration the configuration used to overwrite all property values except UUID
     */
    public void overwriteWith(Configuration anotherConfiguration) {
        setClientIdentifier(anotherConfiguration.clientIdentifier);
        setMonthDay(anotherConfiguration.monthDay == null ? null : anotherConfiguration.monthDay);
        setDayOfWeek(anotherConfiguration.dayOfWeek);
        setReportFrequency(anotherConfiguration.reportFrequency);
        setCountryIdentifier(anotherConfiguration.countryIdentifier);
        setDemographicIdentifier(anotherConfiguration.demographicIdentifier);
        setExportPath(anotherConfiguration.exportPath);
        setLocalExecutionTime(anotherConfiguration.localExecutionTime == null ? null : LocalTime.of(anotherConfiguration.localExecutionTime.getHour(), anotherConfiguration.localExecutionTime.getMinute(), anotherConfiguration.localExecutionTime.getSecond(), anotherConfiguration.localExecutionTime.getNano()));
        setAutomaticExportEnabled(anotherConfiguration.automaticExportEnabled);

        validateInitialState();
    }

    public void disableAutomaticExport() {
        setAutomaticExportEnabled(false);
        setReportFrequency(null);
        setDayOfWeek(null);
        setMonthDay(null);
        setLocalExecutionTime(null);
    }

    public void setDailyFrequency(LocalTime localExecutionTime) {
        setReportFrequency(ReportFrequency.DAILY);
        enableAutomaticExport(LocalTime.of(localExecutionTime.getHour(), localExecutionTime.getMinute(), localExecutionTime.getSecond(), localExecutionTime.getNano()));
    }

    public void setWeeklyFrequency(DayOfWeek dayOfWeek, LocalTime localExecutionTime) {
        if (dayOfWeek == null) {
            throw new InvalidConfigurationStateException(ConfigurationExceptionCodes.DAY_OF_WEEK_CAN_NOT_BE_NULL);
        }
        setReportFrequency(ReportFrequency.WEEKLY);
        setDayOfWeek(dayOfWeek);
        enableAutomaticExport(LocalTime.of(localExecutionTime.getHour(), localExecutionTime.getMinute(), localExecutionTime.getSecond(), localExecutionTime.getNano()));
    }

    public void setMonthlyFrequency(Integer monthDay, LocalTime localExecutionTime) {
        if (monthDay == null) {
            throw new InvalidConfigurationStateException(ConfigurationExceptionCodes.MONTHDAY_CAN_NOT_BE_NULL);
        }
        setReportFrequency(ReportFrequency.MONTHLY);
        setMonthDay(monthDay);
        enableAutomaticExport(LocalTime.of(localExecutionTime.getHour(), localExecutionTime.getMinute(), localExecutionTime.getSecond(), localExecutionTime.getNano()));
    }

    //private modifiers
    private void setReportFrequency(ReportFrequency reportFrequency) {
        this.reportFrequency = reportFrequency;
        validateProperty(this, "reportFrequency");
    }

    private void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        validateProperty(this, "dayOfWeek");
    }

    private void setMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
        validateProperty(this, "monthDay");
    }

    private void setLocalExecutionTime(LocalTime localExecutionTime) {
        this.localExecutionTime = localExecutionTime;
        validateProperty(this, "localExecutionTime");
    }

    private void setUUID(UUID uuid) {
        this.uuid = uuid;
        validateProperty(this, "uuid");
    }

    public void setClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
        validateProperty(this, "clientIdentifier");
    }

    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
        validateProperty(this, "exportPath");
    }

    public void setCountryIdentifier(String countryIdentifier) {
        this.countryIdentifier = countryIdentifier;
        validateProperty(this, "countryIdentifier");
    }

    public void setDemographicIdentifier(int demographicIdentifier) {
        this.demographicIdentifier = demographicIdentifier;
        validateProperty(this, "demographicIdentifier");
    }

    //non-private read-only accessors
    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getMonthDay() {
        return monthDay == null ? null : monthDay.intValue();
    }

    public ReportFrequency getReportFrequency() {
        return reportFrequency;
    }

    public UUID getUUID() {
        return new UUID(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    public String getExportPath() {
        return exportPath;
    }

    public LocalTime getLocalExecutionTime() {
        return localExecutionTime == null ? null : LocalTime.of(localExecutionTime.getHour(), localExecutionTime.getMinute(), localExecutionTime.getSecond(), localExecutionTime.getNano());
    }

    public String getCountryIdentifier() {
        return countryIdentifier;
    }

    public int getDemographicIdentifier() {
        return demographicIdentifier;
    }

    public boolean isAutomaticExportEnabled() {
        return automaticExportEnabled;
    }

    @Override
    public int hashCodeCalculation() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean hasSameIdentity(Configuration other) {
        return Objects.equals(uuid, other.uuid);
    }

    //private modifiers
    private void enableAutomaticExport(LocalTime localExecutionTime) {
        if (localExecutionTime == null) {
            throw new InvalidConfigurationStateException(ConfigurationExceptionCodes.LOCAL_EXECUTION_TIME_CAN_NOT_BE_NULL);
        }
        setAutomaticExportEnabled(true);
        setLocalExecutionTime(localExecutionTime);
    }

    private void setAutomaticExportEnabled(boolean state) {
        this.automaticExportEnabled = state;
        validateProperty(this, "automaticExportEnabled");
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && hasSameIdentity((Configuration) o);
    }

    @Override
    public int hashCode() { return super.hashCode();}

}
