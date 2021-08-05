package com.leverx.odata.entity.edm;

import com.leverx.odata.util.StringConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;

import static com.leverx.odata.util.StringConstants.ES_CATS_NAME;
import static com.leverx.odata.util.StringConstants.ET_CAT_NAME;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@EdmEntitySet(name = ES_CATS_NAME, container = StringConstants.CONTAINER_NAME)
@EdmEntityType(name = ET_CAT_NAME, namespace = StringConstants.NAMESPACE)
public class CatEdm extends PetEdm {

    public CatEdm() {
        super.setPetType(ET_CAT_NAME);
    }
}
