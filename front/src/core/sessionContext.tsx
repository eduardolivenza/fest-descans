import * as React from "react";

export interface SessionContextProps {
  login: string;
  updateLogin: (value: string) => void;
}

export const createDefaultProduct = (): SessionContextProps => ({
  login: "no product",
  updateLogin: value => {
    console.warn(
      "if you are reading this, likely you forgot to add the provider on top of your app"
    );
  }
});

export const SessionContext =
  React.createContext <SessionContextProps>(createDefaultProduct());

export const SessionProvider: React.StatelessComponent = props => {
  const [login, setLogin] = React.useState<string>("");

  return (
    <SessionContext.Provider value={{ login, updateLogin: setLogin }}>
      {props.children}
    </SessionContext.Provider>
  );
};
  
