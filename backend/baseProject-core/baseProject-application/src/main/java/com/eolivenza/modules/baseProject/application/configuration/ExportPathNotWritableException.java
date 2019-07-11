package com.eolivenza.modules.baseProject.application.configuration;


import com.eolivenza.modules.baseProject.domain.model.configuration.ConfigurationExceptionCodes;

public class ExportPathNotWritableException extends RuntimeException {
    private final String path;

    public ExportPathNotWritableException(final String path) {
        super(ConfigurationExceptionCodes.EXPORT_PATH_NOT_VALID);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return " ExportPathNotWritableException. Unable to write to path: " + this.getPath();
    }
}
