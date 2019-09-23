package com.eolivenza.modules.baseProject.application.images.services;

import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.inject.Named;
import java.awt.image.BufferedImage;
import java.io.*;

@Named
public class ImageResolverImpl implements ImageResolver {

    @Autowired
    ImageStorage imageStorage;

    @Autowired
    ImageResizer imageResizer;

    private String ORIGINAL_TYPE = "original";

    public InputStream resolve(String type, String reference) throws Exception {
        if (!type.endsWith(ORIGINAL_TYPE)) {
            try {
                // Get image from storage if it is already resized.
                File storedResizedImage = imageStorage.get(getResizedFileName(reference, type));
                return new FileInputStream(storedResizedImage);
            } catch (FileNotFoundException exception) {
                // Resize image, store it and return it.
                InputStream inputStream = resizeAndSave(reference, type);
                return inputStream;
            }
        } else {
            try {
                // Return original.
                File file = imageStorage.get(reference);
                return new FileInputStream(file);
            } catch (FileNotFoundException exception) {
                throw new Exception( "The original image could not be found.");
            }
        }
    }

    private InputStream resizeAndSave(String reference, String type) throws Exception {
        try {
            File file = imageStorage.get(reference);
            BufferedImage resizedImage = getResizedImage(file, type);
            imageStorage.save(resizedImage, getResizedFileName(reference, type));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", outputStream);
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return inputStream;
        } catch (FileNotFoundException exception) {
            throw new Exception( "The original image could not be found.");
        } catch (IOException exception) {
            throw new Exception( "The resized image could not be saved.");
        }
    }

    private BufferedImage getResizedImage(File file, String type) throws Exception {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            BufferedImage resizedImage = imageResizer.resize(bufferedImage, type);
            return resizedImage;
        } catch (IOException exception) {
            throw new Exception( "Could not read the original image.");
        }
    }

    private String getResizedFileName(String reference, String type) {
        return type + "_" + reference;
    }

}