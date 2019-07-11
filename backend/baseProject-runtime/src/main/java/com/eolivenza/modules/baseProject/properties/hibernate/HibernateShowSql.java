package com.eolivenza.modules.baseProject.properties.hibernate;

/**
 * @author David Gracia Celemendi
 */
public enum HibernateShowSql {
    TRUE("true"),
    FALSE("false");

    private String value;

    public static final String KEY = "hibernate.show_sql";

    HibernateShowSql(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
