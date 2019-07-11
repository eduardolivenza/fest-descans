package com.eolivenza.modules.baseProject.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryLogger<RequestType, ResponseType> implements QueryHandler<RequestType, ResponseType> {

    private Logger logger = LoggerFactory.getLogger(QueryLogger.class);
    private QueryHandler<RequestType, ResponseType> queryHandler;

    public QueryLogger(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    public QueryLogger(QueryHandler queryHandler, Logger logger) {
        this.queryHandler = queryHandler;
        this.logger = logger;
    }

    public ResponseType apply(RequestType request) {
        if (logger.isDebugEnabled()) {
            logger.debug("Starting query " + queryHandler.getName());
        }
        ResponseType response = queryHandler.apply(request);
        if (logger.isDebugEnabled()) {
            logger.debug("Completed query " + queryHandler.getName());
        }
        return response;
    }
}
