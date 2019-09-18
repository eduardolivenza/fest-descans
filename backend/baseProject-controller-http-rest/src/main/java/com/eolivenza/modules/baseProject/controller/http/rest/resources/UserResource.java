package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User", description = "Text for describing the user")
public class UserResource {


    @ApiModelProperty(required = true, value = "First name", example = "John")
    public String firstName;

    @ApiModelProperty(required = true, value = "Last name", example = "Doe")
    public String lastName;

    @ApiModelProperty(required = true, value = "Email of the introduced user. It must be unique.", example = "xxx@mail.com")
    public String email;

    @ApiModelProperty(required = true, value = "Unique identifier related to the sales organization or the country affiliate", example = "123456")
    public String password;

    @ApiModelProperty(required = true, value = "Rights and permissions that user will have", example = "administrator")
    public String userRights;

    public UserResource(){}

    public UserResource( String firstName, String lastName, String email, String password, String userRights) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRights = userRights;
    }
}
