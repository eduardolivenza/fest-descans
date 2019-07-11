package com.eolivenza.modules.baseProject.configuration.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.csrf-configuration")
public class CSRFConfiguration {

    private boolean activateCsrf;

    /**
     * Sets activate csrf.
     *
     * @param activateCsrf the activate csrf
     */
    public void setActivateCsrf(boolean activateCsrf) {
        this.activateCsrf = activateCsrf;
    }

    public boolean getActivateCsrf() {
        return this.activateCsrf;
    }
}