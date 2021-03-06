package com.eolivenza.modules.baseProject.application.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import javax.imageio.ImageIO;
import javax.inject.Named;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Named
public class ImageStorageImpl implements ImageStorage {

    private Path uploadLocation;

    public ImageStorageImpl(@Value("${imageStorage.uploadDir}") String workingPath) {
        this.uploadLocation = Paths.get(workingPath);
        try {
            Files.createDirectories(uploadLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public File get(String name) throws FileNotFoundException {
        File file = ResourceUtils.getFile(uploadLocation + "//"  + name);
        return file;
    }

    public boolean saveFile(InputStream fileContent, String filename){
        try {
            Files.copy(fileContent, this.uploadLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
