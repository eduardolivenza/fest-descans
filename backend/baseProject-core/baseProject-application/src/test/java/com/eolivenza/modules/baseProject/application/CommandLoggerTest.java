package com.eolivenza.modules.baseProject.application;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CommandLoggerTest {

    @Mock
    private Logger mockLogger;

    @Mock
    private CommandHandler<Object> mockCommandHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void logBeforeStartAndAfterFinish() {
        Object command = new Object();
        CommandLogger<Object> cmdLogger = new CommandLogger<>(mockCommandHandler, mockLogger);
        when(mockLogger.isDebugEnabled()).thenReturn(true);
        when(mockCommandHandler.getName()).thenReturn("");
        cmdLogger.accept(command);
        verify(mockLogger, times(1)).debug( "Starting command {}", "");
        verify(mockLogger, times(1)).debug( "Completed command {}", "");
    }

    @Test
    public void noLoggingWithoutDebug() {
        Object command = new Object();
        CommandLogger<Object> cmdLogger = new CommandLogger<>(mockCommandHandler, mockLogger);
        when(mockLogger.isDebugEnabled()).thenReturn(false);
        cmdLogger.accept(command);
        verify(mockLogger, times(0)).debug(anyString());
    }


}