package com.eolivenza.modules.baseProject.application.files;

import org.springframework.util.ResourceUtils;
import javax.imageio.ImageIO;
import javax.inject.Named;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Named
public class ImageStorageImpl implements ImageStorage {

    private String RESOURCE_LOCATION_ROOT = "classpath:image/";

    public File get(String name) throws FileNotFoundException {
        File file = ResourceUtils.getFile(RESOURCE_LOCATION_ROOT + name);
        return file;
    }

    public File save(BufferedImage bufferedImage, String fileName) throws RuntimeException {
        try {
            File outputFile = new File(ResourceUtils.getFile(RESOURCE_LOCATION_ROOT).getAbsolutePath() + "/" + fileName);
            ImageIO.write(bufferedImage, "jpg", outputFile);
            return outputFile;
        } catch (IOException exception) {
            throw new RuntimeException( "File could not be saved.");
        }

    }

}
