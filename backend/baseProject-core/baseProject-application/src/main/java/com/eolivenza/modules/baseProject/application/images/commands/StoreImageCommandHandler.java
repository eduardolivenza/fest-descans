package com.eolivenza.modules.baseProject.application.images.commands;

import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;

import com.eolivenza.modules.baseProject.application.files.ImageStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


@Named
public class StoreImageCommandHandler implements CommandHandler<StoreImageCommand> {

    private Logger logger = LoggerFactory.getLogger(StoreImageCommandHandler.class);

    @Autowired
    ImageStorage imageStorage;

    @Inject
    public StoreImageCommandHandler() {

    }

    @DomainStrictTransactional
    @Override
    public void accept(StoreImageCommand storeImageCommand) {
        if (storeImageCommand.getFilename().contains("..")) {
            throw new RuntimeException("Cannot store file with relative path outside current directory " + storeImageCommand.getFilename());
        }
        imageStorage.saveFile(storeImageCommand.getFileContent(), storeImageCommand.getFilename());
    }

    @Override
    public String getName() { return "Store an image in the system"; }

}


