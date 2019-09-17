import { generatePath } from "react-router";

interface BaseRoutes {
  login : string;
  register: string;
  userAccount: string;
  productCollection : string;
  productView: string;
  suppliers: string;
  default: string;
}

const appBaseRoutes : BaseRoutes = {
  login: '/login',
  userAccount: '/myaccount',
  productCollection: '/product-collection',
  productView: '/product-view',
  suppliers: '/suppliers-collection',
  register: '/register',
  default: '/',
}

type RouterSwitchRoutes = BaseRoutes;

export const productViewRouteParams = {
  id : 'id',
}

// We need to create this because in future pages we will include parameters
// e.g. '/hotel/:productId' this wiyll differ from the link
export const routerSwitchRoutes : RouterSwitchRoutes =  {
  ...appBaseRoutes,
  productView: `${appBaseRoutes.productView}/:${productViewRouteParams.id}`,
}

// https://stackoverflow.com/questions/48215950/exclude-property-from-type
type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>>

type RoutesLinks = Omit<BaseRoutes, 'productView'> & {productView : (id) => string};

// We need to create this because in future pages we will include parameters
// e.g. 'hotel: (hotelId) => /hotel/{hotelId}' this will differ from the route definition
export const routesLinks : RoutesLinks =  {
  ...appBaseRoutes,  
  productView: (id) => generatePath(routerSwitchRoutes.productView, {id}) 
}


