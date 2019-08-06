package com.eolivenza.modules.baseProject.controller.http.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(value = "Images")
@RestController
public class ImagesController {

    @Autowired
    public ImagesController(){
    }

    @GetMapping(path = "/{imageIdentifier}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@ApiParam(required = true, value = "External identifier of the picture", example = "sid")  @PathVariable final String imageIdentifier) throws IOException {
         Resource imgFile = new ClassPathResource("image/"+ imageIdentifier +".jpg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

}
