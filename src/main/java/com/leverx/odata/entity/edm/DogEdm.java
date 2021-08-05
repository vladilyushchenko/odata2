package com.leverx.odata.entity.edm;

import com.leverx.odata.util.StringConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;

import static com.leverx.odata.util.StringConstants.ES_DOGS_NAME;
import static com.leverx.odata.util.StringConstants.ET_DOG_NAME;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@EdmEntitySet(name = ES_DOGS_NAME, container = StringConstants.CONTAINER_NAME)
@EdmEntityType(name = ET_DOG_NAME, namespace = StringConstants.NAMESPACE)
public class DogEdm extends PetEdm {

    public DogEdm() {
        super.setPetType(ET_DOG_NAME);
    }
}

