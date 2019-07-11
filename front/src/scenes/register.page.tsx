import * as React from "react";
import { Link } from "react-router-dom";
import { SingleViewLayout } from "layout";
import { routesLinks } from "core";
import { LoginContainer } from "pods/login";
import { RegisterContainer } from "pods/register";

export const RegisterPage = () => (
  <SingleViewLayout>
    <RegisterContainer />
  </SingleViewLayout>
);