CREATE SCHEMA IF NOT EXISTS ica_baseProject_dm_tables;

CREATE TABLE ica_baseProject_dm_tables.ALARM
(
  identifier                BIGINT identity,
  value                     VARCHAR(255) NULL,
  reagentConsumerJpa        VARCHAR(255) NOT NULL,
  PRIMARY KEY (identifier)
);

CREATE TABLE ica_baseProject_dm_tables.ANALYZER_CODE_NUMBER
(
    identifier              BIGINT identity,
    value                   VARCHAR(255) NOT NULL,
    reagentConsumerJpa      VARCHAR(255) NOT NULL,
    PRIMARY KEY (identifier)
);

CREATE TABLE ica_baseProject_dm_tables.CONFIGURATION
(
  uuid                     VARCHAR(255) NOT NULL,
  automaticExportEnabled   BIT NULL,
  clientIdentifier         VARCHAR(255) NULL,
  countryIdentifier        VARCHAR(255) NULL,
  dayOfWeek                VARCHAR(255) NULL,
  demographicIdentifier    INTEGER NOT NULL,
  exportPath               VARCHAR(255) NULL,
  localExecutionTime       TIME NULL,
  monthDay                 INTEGER NULL,
  reportFrequency          VARCHAR(255) NULL,
  PRIMARY KEY (uuid)
);

CREATE TABLE ica_baseProject_dm_tables.INSTRUMENT
(
  nameGivenByDriver   VARCHAR(255) NULL,
  identifier          VARCHAR(255) NOT NULL,
  PRIMARY KEY (identifier)
);

CREATE TABLE ica_baseProject_dm_tables.MODULES
(
  identifier              VARCHAR(255) NOT NULL,
  instrument   VARCHAR(255) NOT NULL,
  PRIMARY KEY (identifier)
);

CREATE TABLE ica_baseProject_dm_tables.REAGENT_CONSUMER
(
  identifier           VARCHAR(255) NOT NULL,
  externalIdentifier   VARCHAR(255) NULL,
  nameGivenByLis       VARCHAR(255) NULL,
  serialNumber         VARCHAR(255) NULL,
  status               VARCHAR(255) NULL,
  PRIMARY KEY (identifier)
);

alter table ica_baseProject_dm_tables.ALARM ADD CONSTRAINT FK_ALARM_REAGENTCONSUMERJPA_REAGENT_CONSUMER_IDENTIFIER FOREIGN KEY (reagentConsumerJpa) REFERENCES ica_baseProject_dm_tables.REAGENT_CONSUMER (identifier);

alter table ica_baseProject_dm_tables.ANALYZER_CODE_NUMBER ADD CONSTRAINT FK_ACN_REAGENT_CONSUMER_REAGENTCONSUMERSET_IDENTIFIER FOREIGN KEY (reagentConsumerJpa) REFERENCES ica_baseProject_dm_tables.REAGENT_CONSUMER (identifier);

alter table ica_baseProject_dm_tables.INSTRUMENT ADD CONSTRAINT FK_INSTRUMENT_IDENTIFIER_REAGENT_CONSUMER_IDENTIFIER FOREIGN KEY  (identifier) REFERENCES ica_baseProject_dm_tables.REAGENT_CONSUMER (identifier) ;

alter table ica_baseProject_dm_tables.MODULES ADD CONSTRAINT FK_MODULES_INSTRUMENT_INSTRUMENT_IDENTIFIER FOREIGN KEY  (instrument) REFERENCES ica_baseProject_dm_tables.INSTRUMENT (identifier) ;

alter table ica_baseProject_dm_tables.MODULES ADD CONSTRAINT FK_MODULES_IDENTIFIER_REAGENT_CONSUMER_IDENTIFIER FOREIGN KEY (identifier) REFERENCES ica_baseProject_dm_tables.REAGENT_CONSUMER (identifier) ;