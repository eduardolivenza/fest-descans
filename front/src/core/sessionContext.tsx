import * as React from "react";
import * as Cookies from "js-cookie";

export interface SessionContextProps {
  login: string;
  token: string;
}

export const setSessionCookie = (session: SessionContextProps): void => {
  Cookies.remove("session");
  Cookies.set("session", session, { expires: 0.1 });
};

export const getSessionCookie: any = () => {
  const sessionCookie = Cookies.get("session");
  if (sessionCookie === undefined) {
    return {};
  } else {
    return JSON.parse(sessionCookie);
  }
};

export const SessionContext = React.createContext(getSessionCookie());
