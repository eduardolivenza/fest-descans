package com.eolivenza.modules.baseProject.application.images;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import javax.inject.Named;
import java.io.IOException;

@Named
public class ResizeConfigurationImpl implements ResizeConfiguration {

    private JsonObject jsonObject;

    private final String RESIZE_CONFIGURATION = "resize_configuration.json";

    private ResizeConfigurationImpl() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            String result = IOUtils.toString(classLoader.getResourceAsStream(RESIZE_CONFIGURATION));
            Gson gson = new Gson();
            jsonObject = gson.fromJson(result, JsonObject.class);
        } catch (IOException exception) {
            throw new Exception("Configuration could not be loaded from: " + RESIZE_CONFIGURATION);
        }
    }

    @Override
    public JsonObject getConfiguration(String type) {
        return jsonObject.getAsJsonObject(type);
    }
}
