import { generatePath } from "react-router";

interface BaseRoutes {
  login : string;
  register: string;
  productCollection : string;
  hotelEdit: string;
  default: string;
}

const appBaseRoutes : BaseRoutes = {
  login: '/login',
  productCollection: '/product-collection',
  hotelEdit: '/hotel-edit',
  register: '/register',
  default: '/',
}

type RouterSwitchRoutes = BaseRoutes;

export const hotelEditRouteParams = {
  id : 'id',
}

// We need to create this because in future pages we will include parameters
// e.g. '/hotel/:productId' this wiyll differ from the link
export const routerSwitchRoutes : RouterSwitchRoutes =  {
  ...appBaseRoutes,
  hotelEdit: `/${appBaseRoutes.hotelEdit}/:${hotelEditRouteParams.id}`,
}

// https://stackoverflow.com/questions/48215950/exclude-property-from-type
type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>>

type RoutesLinks = Omit<BaseRoutes, 'hotelEdit'> & {hotelEdit : (id) => string};

// We need to create this because in future pages we will include parameters
// e.g. 'hotel: (hotelId) => /hotel/{hotelId}' this will differ from the route definition
export const routesLinks : RoutesLinks =  {
  ...appBaseRoutes,  
  hotelEdit: (id) => generatePath(routerSwitchRoutes.hotelEdit, {id}) 
}


