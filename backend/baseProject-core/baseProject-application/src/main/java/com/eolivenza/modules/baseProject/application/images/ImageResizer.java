package com.eolivenza.modules.baseProject.application.images;

import java.awt.image.BufferedImage;


public interface ImageResizer {


    BufferedImage resize(BufferedImage bufferedImage, String type) throws RuntimeException;

}
