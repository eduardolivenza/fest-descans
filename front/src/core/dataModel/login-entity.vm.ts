export interface LoginEntityVm {
  email: string;
  password: string;
}

export const createEmptyLogin = (): LoginEntityVm => ({
  email: "",
  password: ""
});

