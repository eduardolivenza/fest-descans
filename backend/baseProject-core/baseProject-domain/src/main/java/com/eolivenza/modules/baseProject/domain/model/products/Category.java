package com.eolivenza.modules.baseProject.domain.model.products;

public enum Category {
    BED ("Bed"),
    PILLOW ("Pillow"),
    SOFA ("Sofa"),
    MATTRESS ("Mattress");


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
