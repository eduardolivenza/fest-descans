
export const validateCredentials = (product : string, password : string) : Promise<boolean> => 
  new Promise<boolean>((resolve) => 
    setTimeout(() => 
      resolve((product === 'admin' && password === 'test'))
    , 500)
  );  

