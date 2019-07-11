package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User", description = "Text for describing the user")
public class UserResource {


    @ApiModelProperty(required = true, value = "Relates to the highest level customer identification. The CustomerID value can come from a global SAP, customer master record, local identifier", example = "Bayley")
    public String firstName;

    @ApiModelProperty(required = true, value = "The path on the filesystem where to generate the report file. It must exists.", example = "d:")
    public String lastName;

    @ApiModelProperty(required = true, value = "The path on the filesystem where to generate the report file. It must exists.", example = "d:")
    public String email;

    @ApiModelProperty(required = true, value = "Unique identifier related to the sales organization or the country affiliate", example = "34")
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
