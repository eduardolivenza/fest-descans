package com.eolivenza.modules.baseProject.application.images.services;

import java.io.InputStream;

public interface ImageResolver {

    InputStream resolve(String type, String reference) throws Exception;
}
