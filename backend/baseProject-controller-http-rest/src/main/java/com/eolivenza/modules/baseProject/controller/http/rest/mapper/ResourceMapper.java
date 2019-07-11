package com.eolivenza.modules.baseProject.controller.http.rest.mapper;

/**
 * A generic mapper
 *
 * @param <FirstType> a type
 * @param <SecondType> a type
 */
public interface ResourceMapper<FirstType, SecondType> {

    /**
     * Maps a {@link FirstType} into a {@link SecondType}
     *
     * @param object an instance of {@link SecondType}
     * @return a {@link FirstType}
     */
    FirstType toFirstType(SecondType object);

    /**
     * @param object an instance of {@link FirstType}
     * @return An instance of {@link SecondType}
     */
    SecondType toSecondType(FirstType object);

}
