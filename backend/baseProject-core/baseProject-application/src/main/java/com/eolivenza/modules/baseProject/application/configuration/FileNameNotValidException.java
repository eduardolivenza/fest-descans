package com.eolivenza.modules.baseProject.application.configuration;


import com.eolivenza.modules.baseProject.domain.model.configuration.ConfigurationExceptionCodes;

public class FileNameNotValidException extends RuntimeException {
    private final String filename;

    public FileNameNotValidException(final String filename) {
        super(ConfigurationExceptionCodes.FILENAME_NOT_VALID);
        this.filename = filename;
    }

    @Override
    public String toString() {
        return " ExportPathNotWritableException. Filename introduced contains some invalid character: " + getName();
    }

    public String getName() {
        return this.filename;
    }
}
