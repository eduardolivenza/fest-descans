package com.eolivenza.modules.baseProject.properties.hibernate;

/**
 * @author David Gracia Celemendi
 */
public enum HibernateFormatSql {
    TRUE("true"),
    FALSE("false");

    private String value;

    public static final String KEY = "hibernate.format_sql";

    HibernateFormatSql(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
