package com.eolivenza.modules.baseProject.domain;

/**
 * @author David Gracia Celemendi
 */
public interface Validateable {
    /**
     * @param object See {@link ValidatorWrapper#validate(Object, Class[])}
     * @param groups See {@link ValidatorWrapper#validate(Object, Class[])}
     * @param <T>    See {@link ValidatorWrapper#validate(Object, Class[])}
     */
    default <T> void validate(T object, Class<?>... groups) {
        ValidatorWrapper.validate(object, groups);
    }

    /**
     * @param object       See {@link ValidatorWrapper#validate(Object, Class[])}
     * @param propertyName See {@link ValidatorWrapper#validate(Object, Class[])}
     * @param groups       See {@link ValidatorWrapper#validate(Object, Class[])}
     * @param <T>          See {@link ValidatorWrapper#validate(Object, Class[])}
     */
    default <T> void validateProperty(T object, String propertyName, Class<?>... groups) {
        ValidatorWrapper.validateProperty(object, propertyName, groups);
    }
}
