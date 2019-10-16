import { CategoryEntityApi} from 'core/apiModel/product-entity.api'
import { CheckBoxConfigValue } from "common/components";

export const mapToCheckBoxValue= (apiEntity : CategoryEntityApi) : CheckBoxConfigValue => ({
  name: apiEntity.value,
  label: apiEntity.description,
  value: true,
});

