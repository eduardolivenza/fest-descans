import { LookupEntity } from "./model";

export const noCategorySelectedLiteral = "No category selected";

export const categoriesLookup: LookupEntity[] = [
  {
    id: noCategorySelectedLiteral,
    value: noCategorySelectedLiteral
  },
  {
    id: "BED",
    value: "Bed"
  },
  {
    id: "SOFA",
    value: "Sofa"
  },
  {
    id: "MATTRESS",
    value: "Mattress or canape"
  },
  {
    id: "OTHER",
    value: "Pillows/ other type"
  }
];
