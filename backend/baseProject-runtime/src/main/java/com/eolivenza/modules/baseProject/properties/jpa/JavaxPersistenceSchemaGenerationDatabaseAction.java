package com.eolivenza.modules.baseProject.properties.jpa;

/**
 * @author David Gracia Celemendi
 */
public enum JavaxPersistenceSchemaGenerationDatabaseAction {
    NONE("none"), CREATE("create"), DROP("drop"), DROP_AND_CREATE("drop-and-create");

    private String value;

    public static final String KEY = "javax.persistence.schema-generation.database.action";

    JavaxPersistenceSchemaGenerationDatabaseAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
