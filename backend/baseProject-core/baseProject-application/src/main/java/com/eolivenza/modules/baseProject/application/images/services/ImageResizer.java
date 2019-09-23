package com.eolivenza.modules.baseProject.application.images.services;

import java.awt.image.BufferedImage;


public interface ImageResizer {


    BufferedImage resize(BufferedImage bufferedImage, String type) throws Exception;

}
