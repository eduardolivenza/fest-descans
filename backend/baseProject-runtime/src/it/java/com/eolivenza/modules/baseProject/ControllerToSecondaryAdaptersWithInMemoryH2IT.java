package com.eolivenza.modules.baseProject;

import com.eolivenza.modules.baseProject.configuration.ProfileNames;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ProductResource;
import com.eolivenza.modules.baseProject.resources.ProductResourceDataBuilder;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ActiveProfiles(value = ProfileNames.CONTROLLER_TO_SECONDARY_ADAPTERS_WITH_IN_MEMORY_H2)
public class ControllerToSecondaryAdaptersWithInMemoryH2IT {

    @LocalServerPort
    private int port;

    private final String HEALTH_UP = "UP";
    private final String tokenSuperUser = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJST0NIRSIsInRlbmFudElkIjoiMzA1MzhGRkYtRDE2RC0xMUUzLTkwNzgtNTYzNDEyMDAwMEZGIiwibGFuZyI6ImVuIiwiaWF0IjoxNTM3OTc2MDU0LCJhdXRoIjoiUk9DSEUiLCJzdXBlcnVzZXIiOiJ0cnVlIiwicmlnaHRzIjp7ImFjdGlvbnMiOltdLCJyZXNvdXJjZXMiOltdfX0.mP5QrDTdZJNzNTG8JdprGknGLtegXuGlW_zQmVubp50";
    private Header header;

    @Before
    public void setUp() {
        RestAssured.port = port;
        header = new Header("Authorization", tokenSuperUser);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void whenQueryActuatorHealth_ThenReturnsOK() {
        given().contentType(APPLICATION_JSON_VALUE)
                .when().get(URI.create("/actuator/health"))
                .then().log().all().statusCode(HttpStatus.OK.value())
                .and().body("status", is(HEALTH_UP));
    }

    @Test
    public void whenAProductIsAdded_ThenWeCanRetrieveIt() {
        /*CategoryResource addedCategoryResource = CategoryResourceDataBuilder
                .defaultBuilder()
                .build();

         */
        ProductResource addedProductResource = ProductResourceDataBuilder
                .defaultBuilder()
                .withDescription("desc1")
                .build();
         /*
        String url = "/categories";
        given().contentType(APPLICATION_JSON_VALUE)
                .body(addedCategoryResource)
                .when().post(url)
                .then().log().all().assertThat().statusCode(HttpStatus.OK.value());*/
        String url = "/products";
        given().contentType(APPLICATION_JSON_VALUE)
                .body(addedProductResource)
                .when().post("/products")
                .then().log().all().assertThat().statusCode(HttpStatus.OK.value());
        List<String> expectedReturnedList = new ArrayList();
        expectedReturnedList.add("desc1");
        given().contentType(APPLICATION_JSON_VALUE)
                //.pathParams(pathVariables)
                .when().get(url)
                .then().log().all().assertThat()
                .statusCode(HttpStatus.OK.value())
                .and().body("productDescription", is(expectedReturnedList));
    }

}