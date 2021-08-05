package com.leverx.odata.service;

import com.leverx.odata.entity.jpa.Cat;
import com.leverx.odata.entity.jpa.Dog;
import com.leverx.odata.mapper.EdmMapper;
import com.leverx.odata.entity.edm.PetEdm;
import com.leverx.odata.entity.edm.UserEdm;
import com.leverx.odata.entity.jpa.Pet;
import com.leverx.odata.repository.impl.PetRepositoryImpl;
import com.leverx.odata.util.StringConstants;
import lombok.SneakyThrows;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class PetOdataService implements ODataService<PetEdm> {

    UserODataService userService = new UserODataService();

    @Override
    public UserEdm getRelatedData(Object sourceData, String targetEntityName) throws ODataNotFoundException {
        PetEdm petEdm = (PetEdm) sourceData;
        if (StringConstants.ES_USERS_NAME.equals(targetEntityName)) {
            return petEdm.getUserEdm();
        }
        throw new ODataNotFoundException(ENTITY);
    }

    @Override
    public PetEdm getDataObject() {
        return new PetEdm();
    }

    @Override
    public List<PetEdm> findAll() {
        return PetRepositoryImpl.getInstance().findAll().stream()
                .map(EdmMapper::toPetEdm)
                .collect(toList());
    }

    @Override
    public PetEdm findById(long entityId) {
        return EdmMapper.toPetEdm(PetRepositoryImpl.getInstance().findById(entityId));
    }

    @Override
    @Transactional
    public PetEdm save(PetEdm sourceData) {
        Pet pet = EdmMapper.fromPetEdm(sourceData);
        if (StringConstants.ET_DOG_NAME.equals(sourceData.getPetType())) {
            PetRepositoryImpl.getInstance().save((Dog) pet);
        } else {
            PetRepositoryImpl.getInstance().save((Cat) pet);
        }
        sourceData.setId(pet.getId());
        return EdmMapper.toPetEdm(pet);
    }

    @Override
    @SneakyThrows
    public void updateTargetKeysBySourceData(Map<String, Object> targetKeys, Object sourceData) {
        long petId = (long) targetKeys.get(StringConstants.PET_EDM_JSON_FIELD_NAME);
        long userId = ((UserEdm) sourceData).getId();
        PetRepositoryImpl.getInstance().updateOwnerUserId(petId, userId);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Pet pet = PetRepositoryImpl.getInstance().findById(id);
        PetRepositoryImpl.getInstance().delete(pet);
    }
}