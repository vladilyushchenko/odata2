package com.leverx.odata.entity.edm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;

import static com.leverx.odata.util.StringConstants.*;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@EdmEntitySet(name = ES_CATS_NAME, container = CONTAINER_NAME)
@EdmEntityType(name = ET_CAT_NAME, namespace = NAMESPACE)
public class CatEdm extends PetEdm {

    public CatEdm() {
        super.setPetType(ET_CAT_NAME);
    }
}
