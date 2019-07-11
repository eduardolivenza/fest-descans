package com.eolivenza.modules.baseProject.application;

import java.util.function.Function;

/**
 * Generic contract intended to handle queries. The term "query" refers to
 * Bertrand Meyer's meaning in the book "Object Oriented Software Construction".
 * A query returns a result and do not change the state of the system (are free of side effects).
 *
 * @param <RequestType>  the query request object
 * @param <ResponseType> the query response object
 * @author David Gracia Celemendi
 */
@FunctionalInterface
public interface QueryHandler<RequestType, ResponseType> extends Function<RequestType, ResponseType> {
    default String getName() { return getClass().getName(); }
}
