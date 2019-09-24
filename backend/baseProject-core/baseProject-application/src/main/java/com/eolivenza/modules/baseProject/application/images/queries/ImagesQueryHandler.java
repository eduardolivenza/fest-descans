package com.eolivenza.modules.baseProject.application.images.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.images.ImageResizer;
import com.eolivenza.modules.baseProject.application.files.ImageStorage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Named
public class ImagesQueryHandler implements QueryHandler<ImageCommand, List<InputStream>> {

    @Autowired
    ImageStorage imageStorage;

    @Autowired
    ImageResizer imageResizer;

    private String ORIGINAL_TYPE = "original";

    @Inject
    public ImagesQueryHandler( ) {

    }


    @DomainStrictTransactional
    @Override
    public List<InputStream> apply( ImageCommand command) throws RuntimeException{
        List<InputStream> list = new ArrayList<InputStream>();
        if (!command.type.endsWith(ORIGINAL_TYPE)) {
            try {
                // Get image from storage if it is already resized.
                File storedResizedImage = imageStorage.get(getResizedFileName(command.reference, command.type));
                list.add(new FileInputStream(storedResizedImage));
            } catch (FileNotFoundException exception) {
                // Resize image, store it and return it.
                InputStream inputStream = resizeAndSave(command.reference, command.type);
                list.add(inputStream);
            }
        } else {
            try {
                // Return original.
                File file = imageStorage.get(command.reference);
                list.add(new FileInputStream(file));
            } catch (FileNotFoundException exception) {
                throw new RuntimeException( "The original image could not be found.");
            }
        }
        return list;
    }








    private InputStream resizeAndSave(String reference, String type) throws RuntimeException {
        try {
            File file = imageStorage.get(reference);
            BufferedImage resizedImage = getResizedImage(file, type);
            imageStorage.save(resizedImage, getResizedFileName(reference, type));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", outputStream);
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return inputStream;
        } catch (FileNotFoundException exception) {
            throw new RuntimeException( "The original image could not be found.");
        } catch (IOException exception) {
            throw new RuntimeException( "The resized image could not be saved.");
        }
    }

    private BufferedImage getResizedImage(File file, String type) throws RuntimeException {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            BufferedImage resizedImage = imageResizer.resize(bufferedImage, type);
            return resizedImage;
        } catch (IOException exception) {
            throw new RuntimeException( "Could not read the original image.");
        }
    }

    private String getResizedFileName(String reference, String type) {
        return type + "_" + reference;
    }

    @Override
    public String getName() { return "Get images core"; }
}
