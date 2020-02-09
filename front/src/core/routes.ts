import { generatePath } from "react-router";

interface BaseRoutes {
  login : string;
  register: string;
  userAccount: string;
  productCollection : string;
  cartView: string;
  productAdd: string;
  productView: string;
  productEdit: string;
  suppliersCollection: string;
  default: string;
  supplierAdd: string;
  supplierEdit: string;
}

const appBaseRoutes : BaseRoutes = {
  login: '/login',
  userAccount: '/myaccount',
  productCollection: '/product-collection',
  productAdd: '/product-add',
  productView: '/product-view',
  cartView: '/cart-view',
  productEdit: '/product-edit',
  suppliersCollection: '/suppliers-collection',
  supplierAdd: '/supplier-add',
  supplierEdit: '/supplier-edit',
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
  productEdit: `${appBaseRoutes.productEdit}/:${productViewRouteParams.id}`,
  supplierEdit: `${appBaseRoutes.supplierEdit}/:${productViewRouteParams.id}`,
}

export const routesLinks  =  {
  ...appBaseRoutes,  
  productView:  (id) => generatePath(routerSwitchRoutes.productView, {id}),
  productEdit:  (id) => generatePath(routerSwitchRoutes.productEdit, {id}),
  supplierEdit: (id) => generatePath(routerSwitchRoutes.supplierEdit, {id}),
}


