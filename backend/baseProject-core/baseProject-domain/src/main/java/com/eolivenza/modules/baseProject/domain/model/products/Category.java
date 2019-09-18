package com.eolivenza.modules.baseProject.domain.model.products;

public enum Category {
    BED ("Bed"),
    SOFA ("Sofa"),
    MATTRESS ("Mattress"),
    OTHER ("Others");

    /** A human-friendly string value */
    private String description;

    private Category(String description) {
        this.description = description;
    }

    /**
     * Public accessor for the human friendly description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
