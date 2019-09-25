package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.CommandLogger;
import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.images.commands.StoreImageCommand;
import com.eolivenza.modules.baseProject.application.images.queries.ImageCommand;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@Api("Images")
public class ImagesController {

    @Autowired
    private QueryHandler<ImageCommand, List<InputStream>> getImageQueryHandler;
    @Autowired
    private CommandHandler<StoreImageCommand> storeImageCommandHandler;

    @Autowired
    public ImagesController(){
    }

    @GetMapping("/image/{predefined-type-name}/{reference}")
    public ResponseEntity getImage(@PathVariable("predefined-type-name") String predefinedTypeName,
                                   @PathVariable("reference") String reference) {
        final HttpHeaders headers = new HttpHeaders();
        ResponseEntity responseEntity = null;
        try {
            headers.setContentType(MediaType.IMAGE_JPEG);
            ImageCommand imageCommand = new ImageCommand(predefinedTypeName, reference);
            List<InputStream> imageList = getImageQueryHandler.apply(imageCommand);
            InputStreamResource inputStreamResource = new InputStreamResource(imageList.get(0));
            responseEntity = new ResponseEntity(inputStreamResource, headers, HttpStatus.OK);
        } catch (Exception exception) {
            headers.setContentType(MediaType.TEXT_HTML);
            responseEntity = new ResponseEntity(exception.getMessage(), headers, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping(value = "/image/upload/{productIdentifier}")
    public void handleFileUpload(@PathVariable("productIdentifier") String productIdentifier,
                                 @RequestParam("file") MultipartFile file) {
        try{
            StoreImageCommand command = new StoreImageCommand(productIdentifier, file.getOriginalFilename(), file.getInputStream());
            storeImageCommandHandler.accept(command);
        } catch ( IOException e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }


}
