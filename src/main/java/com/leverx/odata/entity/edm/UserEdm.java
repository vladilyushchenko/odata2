package com.leverx.odata.entity.edm;

import com.leverx.odata.util.StringConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.olingo.odata2.api.annotation.edm.*;

import java.util.List;

import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.MANY;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Data
@SuperBuilder
@NoArgsConstructor
@EdmEntitySet(name = StringConstants.ES_USERS_NAME, container = StringConstants.CONTAINER_NAME)
@EdmEntityType(name = StringConstants.ET_USER_NAME, namespace = StringConstants.NAMESPACE)
public class UserEdm implements EntityEdm {

    @EdmKey
    @EdmProperty(type = INT64)
    private Long id;

    @EdmProperty(type = STRING)
    private String name;

    @EdmProperty(type = STRING)
    private String email;

    @EdmNavigationProperty(toMultiplicity = MANY,
            toType = PetEdm.class,
            association = StringConstants.USER_PET_ASSOCIATION,
            toRole = StringConstants.ROLE_PET,
            name = StringConstants.ES_PETS_NAME)
    private List<PetEdm> petsEdm;
}
