import { ProductEntityApi } from "core/apiModel/product-entity.api";
import { mapFromApiToVm } from "core/mapper/product-entity.mapper";

describe('Product entity mapper tests', () => {

    it('check product entity and its content is correctly mapped to domain objects', () => {
        const productEntityApi : ProductEntityApi = {
          category: "string",
          productIdentifier : "string",
          productName : "string",
          productDescription: "string",
          sizes: [],
          supplier: {
            companyId: "string",
            companyName: "string",
            country: "string",
          },
          comfortLevel: 4,
          images: [],
        };

        // Act
        const product = mapFromApiToVm(productEntityApi);

        expect(product.productIdentifier).toEqual(productEntityApi.productIdentifier);
        expect(product.supplier.id).toEqual(productEntityApi.supplier.companyId);

      });
});