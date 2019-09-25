package com.eolivenza.modules.baseProject.application.images.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.files.ImageStorage;
import com.eolivenza.modules.baseProject.application.images.ImageResizer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
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

    @Autowired
    public ImagesQueryHandler( ) {}

    @DomainStrictTransactional
    @Override
    public List<InputStream> apply( ImageCommand command) throws RuntimeException{
        List<InputStream> list = new ArrayList<InputStream>();
        try {
            if (!command.type.endsWith(ORIGINAL_TYPE)) {
                list.add( resizeImage(command.reference, command.type));
            }
            else {
                File file = imageStorage.get(command.reference);
                list.add(new FileInputStream(file));
            }
        }
        catch (Exception exception) {
            throw new RuntimeException( "The original image could not be found.");
        }
        return list;
    }

    private InputStream resizeImage(String reference, String type) throws IOException {
        File file = imageStorage.get(reference);
        BufferedImage resizedImage = getResizedImage(file, type);
        //imageStorage.save(resizedImage, getResizedFileName(reference, type));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", outputStream);
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream;
    }

    private BufferedImage getResizedImage(File file, String type) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage resizedImage = imageResizer.resize(bufferedImage, type);
        return resizedImage;
    }

    @Override
    public String getName() {
        return "Get images core";
    }
}
