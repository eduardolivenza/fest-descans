package com.eolivenza.modules.baseProject.application.files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ImageStorage {

    /**
     * Gets an image file from the disk.
     * @param name File name.
     * @return
     * @throws FileNotFoundException
     */
    File get(String name) throws FileNotFoundException;

    boolean saveFile(InputStream fileContent, String filename);
}
