package com.eolivenza.modules.baseProject.properties.hibernate;

/**
 * @author David Gracia Celemendi
 */
public enum HibernateUseSqlComments {
    TRUE("true"),
    FALSE("false");

    private String value;

    public static final String KEY = "hibernate.use_sql_comments";

    HibernateUseSqlComments(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
