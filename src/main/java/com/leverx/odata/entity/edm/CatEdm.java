package com.leverx.odata.entity.edm;

import com.leverx.odata.util.StringConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EdmEntitySet(name = StringConstants.ES_CATS_NAME, container = StringConstants.CONTAINER_NAME)
@EdmEntityType(name = StringConstants.ET_CAT_NAME, namespace = StringConstants.NAMESPACE)
public class CatEdm extends PetEdm {

}
