import { LoginEntityApi } from  "core/apiModel/login-entity.api";
import { LoginEntityVm } from "core/dataModel/login-entity.vm";

export const mapCredentials = (loginEntity: LoginEntityVm): LoginEntityApi => (
{
    email: loginEntity.email,
    password: loginEntity.password
});