package com.eolivenza.modules.baseProject.application;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class QueryLoggerTest {

    @Mock
    private Logger mockLogger;

    @Mock
    private QueryHandler<Object, Object> mockQueryHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void logBeforeStartAndAfterFinish() {
        Object command = new Object();
        QueryLogger<Object,Object> cmdLogger = new QueryLogger<>(mockQueryHandler, mockLogger);
        when(mockLogger.isDebugEnabled()).thenReturn(true);
        cmdLogger.apply(command);
        verify(mockLogger, times(2)).debug(anyString());
    }

    @Test
    public void noLoggingWithoutDebug() {
        Object command = new Object();
        QueryLogger<Object, Object> cmdLogger = new QueryLogger<>(mockQueryHandler, mockLogger);
        when(mockLogger.isDebugEnabled()).thenReturn(false);
        cmdLogger.apply(command);
        verify(mockLogger, times(0)).debug(anyString());
    }


}