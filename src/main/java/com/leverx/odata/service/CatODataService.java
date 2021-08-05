package com.leverx.odata.service;

import com.leverx.odata.mapper.EdmMapper;
import com.leverx.odata.entity.edm.CatEdm;
import com.leverx.odata.entity.jpa.Cat;
import com.leverx.odata.repository.PetRepository;
import com.leverx.odata.repository.impl.PetRepositoryImpl;
import com.leverx.odata.util.StringConstants;
import lombok.SneakyThrows;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class CatODataService implements ODataService<CatEdm> {

    private final PetRepository petRepository;

    public CatODataService() {
        petRepository = PetRepositoryImpl.getInstance();
    }

    @Override
    public Object getRelatedData(Object sourceData, String targetEntityName) throws ODataNotFoundException {
        CatEdm catEdm = (CatEdm) sourceData;
        if (StringConstants.ES_USERS_NAME.equals(targetEntityName)) {
            return catEdm.getUserEdm();
        }
        throw new ODataNotFoundException(ENTITY);
    }

    @Override
    public CatEdm getDataObject() {
        return new CatEdm();
    }

    @Override
    public List<CatEdm> findAll() {
        return petRepository.findAllCats().stream()
                .map(EdmMapper::toCatEdm)
                .collect(toList());
    }

    @Override
    public CatEdm findById(long entityId) {
        return EdmMapper.toCatEdm((Cat) petRepository.findById(entityId));
    }

    @Override
    public CatEdm save(CatEdm sourceData) throws ODataNotImplementedException {
        Cat cat = EdmMapper.fromCatEdm(sourceData);
        petRepository.save(cat);
        return EdmMapper.toCatEdm(cat);
    }

    @SneakyThrows
    @Override
    public void updateTargetKeysBySourceData(Map<String, Object> targetKeys, Object sourceData) {
        throw new ODataNotImplementedException();
    }

    @Override
    @Transactional
    public void delete(long id) {
        Cat cat = (Cat) petRepository.findById(id);
        petRepository.delete(cat);
    }
}
