package com.leverx.odata.service;

import com.leverx.odata.mapper.EdmMapper;
import com.leverx.odata.entity.edm.DogEdm;
import com.leverx.odata.entity.edm.EntityEdm;
import com.leverx.odata.entity.jpa.Dog;
import com.leverx.odata.repository.impl.PetRepositoryImpl;
import com.leverx.odata.util.StringConstants;
import lombok.SneakyThrows;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class DogODataService implements ODataService<DogEdm> {

    @Override
    public <T extends EntityEdm> Object getRelatedData(Object sourceData, String targetEntityName) throws ODataNotFoundException, ODataNotImplementedException {
        DogEdm dogEdm = (DogEdm) sourceData;
        if (StringConstants.ES_USERS_NAME.equals(targetEntityName)) {
            return dogEdm.getUserEdm();
        }
        throw new ODataNotFoundException(ENTITY);
    }

    @Override
    public DogEdm getDataObject() {
        return new DogEdm();
    }

    @Override
    public List<DogEdm> findAll() {
        return PetRepositoryImpl.getInstance()
                .findAllDogs().stream()
                .map(EdmMapper::toDogEdm)
                .collect(toList());
    }

    @Override
    public DogEdm findById(long entityId) {
        return EdmMapper.toDogEdm((Dog) PetRepositoryImpl.getInstance().findById(entityId));
    }

    @Override
    public DogEdm save(DogEdm sourceData) throws ODataNotImplementedException {
        Dog dog = EdmMapper.fromDogEdm(sourceData);
        PetRepositoryImpl.getInstance().save(dog);
        return EdmMapper.toDogEdm(dog);
    }

    @SneakyThrows
    @Override
    public void updateTargetKeysBySourceData(Map<String, Object> targetKeys, Object sourceData) {
        throw new ODataNotImplementedException();
    }

    @Override
    public void delete(long id) {
        Dog dog = (Dog) PetRepositoryImpl.getInstance().findById(id);
        PetRepositoryImpl.getInstance().delete(dog);
    }
}
