package com.eolivenza.modules.baseProject.application.images.commands;

import java.io.InputStream;

public class StoreImageCommand {

    private String filename;
    private InputStream fileContent;

    public StoreImageCommand(String filename, InputStream fileContent) {
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public String getFilename() {
        return filename;
    }

    public InputStream getFileContent() {
        return fileContent;
    }
}
