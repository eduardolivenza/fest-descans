package com.eolivenza.modules.baseProject;

import com.eolivenza.modules.baseProject.configuration.SwaggerUIConfiguration;
import org.junit.Before;
import org.junit.Test;

public class SwaggerUIConfigurationTest {

    private SwaggerUIConfiguration swaggerUIConfiguration;

    @Before
    public void setUp(){
        swaggerUIConfiguration = new SwaggerUIConfiguration();
    }

    @Test
    public void docket() {
        swaggerUIConfiguration.docket();
    }
}