package com.eolivenza.modules.baseProject.domain.model.configuration;

/**
 * The type Constants to Domain Exception codes
 */
public class ConfigurationExceptionCodes {

    /**
     * Path introduced is not valid
     */
    public static final String EXPORT_PATH_NOT_VALID = "configurationException.exportPathNotValidExceptionMessage";

    /**
     * Filename introduced is not valid
     */
    public static final String FILENAME_NOT_VALID = "configurationException.fileNameNotValidExceptionMessage";

    /**
     * Automatic export is enabled but any of [reportFrequency, localExecutionTime] are null
     */
    public static final String NULL_EXPORT_FREQUENCY_OR_TIME = "configurationException.invalidConfigurationStateException.nullExportFrequencyOrTime";
    /**
     * Automatic export is disabled but any of [reportFrequency, dayOfWeek, monthDay] are set
     */
    public static final String DISABLED_EXPORT_BUT_FREQUENCY_SET = "configurationException.invalidConfigurationStateException.disabledExportButFrequencySet";
    /**
     * Report frequency is daily but any of [dayOfWeek, monthDay] are set
     */
    public static final String DAILY_FREQUENCY_BUT_CONFIGURATION_NOT_CORRECT = "configurationException.invalidConfigurationStateException.dailyFrequencyButConfigurationNotCorrect";
    /**
     * Report frequency is weekly but dayOfWeek is null or monthDay is set
     */
    public static final String WEEKLY_FREQUENCY_BUT_CONFIGURATION_NOT_CORRECT = "configurationException.invalidConfigurationStateException.weeklyFrequencyButConfigurationNotCorrect";
    /**
     * Report frequency is monthly but dayOfWeek is set or monthDay is null
     */
    public static final String MONTHLY_FREQUENCY_BUT_CONFIGURATION_NOT_CORRECT = "configurationException.invalidConfigurationStateException.monthlyFrequencyButConfigurationNotCorrect";
    /**
     * dayOfWeek can not be null
     */
    public static final String DAY_OF_WEEK_CAN_NOT_BE_NULL = "configurationException.invalidConfigurationStateException.dayOfWeekCanNotBeNull";
    /**
     * monthDay can not be null
     */
    public static final String MONTHDAY_CAN_NOT_BE_NULL = "configurationException.invalidConfigurationStateException.monthDayCanNotBeNull";
    /**
     * localExecutionTime can not be null
     */
    public static final String LOCAL_EXECUTION_TIME_CAN_NOT_BE_NULL = "configurationException.invalidConfigurationStateException.localExecutionTimeCanNotBeNull";

    private ConfigurationExceptionCodes() {
    }
}
