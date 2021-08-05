package com.leverx.odata.entity.edm;

import com.leverx.odata.util.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.olingo.odata2.api.annotation.edm.*;

import static com.leverx.odata.util.StringConstants.*;
import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.ONE;

@Data
@SuperBuilder
@NoArgsConstructor
@EdmEntitySet(name = ES_PETS_NAME, container = CONTAINER_NAME)
@EdmEntityType(name = ET_PET_NAME, namespace = NAMESPACE)
public class PetEdm implements EntityEdm {

    @EdmKey
    @EdmProperty
    protected Long id;

    @EdmProperty
    protected String name;

    @EdmProperty
    protected Integer age;

    @EdmProperty
    protected String petType;

    @EdmNavigationProperty(toMultiplicity = ONE,
            toType = UserEdm.class,
            association = USER_PET_ASSOCIATION,
            toRole = ROLE_USER,
            name = ET_USER_NAME
    )
    private UserEdm userEdm;
}
