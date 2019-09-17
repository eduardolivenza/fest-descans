package com.eolivenza.modules.baseProject.controller.http.rest.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ValUser", description = "Entity to login a user")
public class ValUserResource {

    @ApiModelProperty(required = true, value = "The path on the filesystem where to generate the report file. It must exists.", example = "xxx@mail.com")
    public String login;

    @ApiModelProperty(required = true, value = "Unique identifier related to the sales organization or the country affiliate", example = "123456")
    public String password;

    public ValUserResource(){}

    public ValUserResource(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
