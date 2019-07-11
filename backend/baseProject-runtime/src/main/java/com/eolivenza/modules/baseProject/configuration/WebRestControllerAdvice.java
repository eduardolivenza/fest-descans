package com.eolivenza.modules.baseProject.configuration;

import com.eolivenza.modules.baseProject.application.users.UserNotCorrectException;
import com.eolivenza.modules.baseProject.application.users.UserNotExistsException;
import com.eolivenza.modules.baseProject.domain.model.ModelValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebRestControllerAdvice {

    private final Logger Logger = LoggerFactory.getLogger(WebRestControllerAdvice.class);

    @Autowired
    public WebRestControllerAdvice() {
    }

    @ExceptionHandler(value =
            {   ModelValidationException.class
            })
    public ResponseEntity<String> handleInvalidRequestExceptions(RuntimeException ex) {
        String message = ex.toString();
        Logger.error(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN)
                .body(createBodyMessageForException(ex));
    }


    @ExceptionHandler(value = { RuntimeException.class,
                                Exception.class})
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        String message = ex.toString();
        Logger.error(message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN)
                .body(createBodyMessageForException(ex));
    }

    @ExceptionHandler(value = { UserNotCorrectException.class})
    public ResponseEntity<String> handleUserNotValidatedException(UserNotCorrectException ex) {
        String message = ex.toString();
        Logger.error(message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.TEXT_PLAIN)
                .body(createBodyMessageForException(ex));
    }

    @ExceptionHandler(value = { UserNotExistsException.class})
    public ResponseEntity<String> handleUserNotExistsException(UserNotExistsException ex) {
        String message = ex.toString();
        Logger.error(message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.TEXT_PLAIN)
                .body(createBodyMessageForException(ex));
    }

    private String createBodyMessageForException(Exception ex) {
        String message = ex.getMessage();

        return message;
    }


}