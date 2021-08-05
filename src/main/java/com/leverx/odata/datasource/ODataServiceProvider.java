package com.leverx.odata.datasource;

import com.leverx.odata.entity.edm.CatEdm;
import com.leverx.odata.entity.edm.DogEdm;
import com.leverx.odata.entity.edm.PetEdm;
import com.leverx.odata.entity.edm.UserEdm;
import com.leverx.odata.service.*;
import com.leverx.odata.util.StringConstants;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class ODataServiceProvider {

    private final Map<String, ODataService> oDataServices = new HashMap<>();

    public ODataServiceProvider() {
        ODataService<UserEdm> userODataService = new UserODataService();
        ODataService<PetEdm> petODataService = new PetOdataService();
        ODataService<CatEdm> catODataService = new CatODataService();
        ODataService<DogEdm> dogODataService = new DogODataService();

        oDataServices.put(StringConstants.ES_USERS_NAME, userODataService);
        oDataServices.put(StringConstants.ES_PETS_NAME, petODataService);
        oDataServices.put(StringConstants.ES_CATS_NAME, catODataService);
        oDataServices.put(StringConstants.ES_DOGS_NAME, dogODataService);
    }

    public ODataService getODataEntityService(String entitySetName) throws ODataNotFoundException {
        return ofNullable(oDataServices.get(entitySetName))
                .orElseThrow(() -> new ODataNotFoundException(ENTITY));
    }
}
