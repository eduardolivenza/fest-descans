export interface LookupEntity {
  value : string;
  description : string;
}

export const createLookupEmpty = (): LookupEntity => ({
  value: "",
  description: "",
});