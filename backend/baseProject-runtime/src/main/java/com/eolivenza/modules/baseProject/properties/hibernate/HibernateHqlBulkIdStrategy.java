package com.eolivenza.modules.baseProject.properties.hibernate;

/**
 * @author David Gracia Celemendi
 */
public enum HibernateHqlBulkIdStrategy {
    INILINE_IDS("org.hibernate.hql.spi.id.inline.InlineIdsInClauseBulkIdStrategy"),
    INILINE_IDS_SUB_SELECT("org.hibernate.hql.spi.id.inline.InlineIdsSubSelectValueListBulkIdStrategy"),
    INILINE_IDS_OR_BULK_IDS("org.hibernate.hql.spi.id.inline.InlineIdsOrClauseBulkIdStrategy"),
    CTE_LIST_BULK_IDS("org.hibernate.hql.spi.id.inline.CteValuesListBulkIdStrategy");

    private String value;

    public static final String KEY = "hibernate.hql.bulk_id_strategy";

    HibernateHqlBulkIdStrategy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
