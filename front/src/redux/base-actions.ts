import { Action } from 'redux';

export interface BaseAction<T = any> extends Action<string> {
  payload?: T;
}

export interface StringBoolean {
  name: string,
  value: boolean,
}