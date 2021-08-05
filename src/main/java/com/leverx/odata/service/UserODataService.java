package com.leverx.odata.service;

import com.leverx.odata.mapper.EdmMapper;
import com.leverx.odata.entity.edm.UserEdm;
import com.leverx.odata.entity.jpa.User;
import com.leverx.odata.repository.impl.PetRepositoryImpl;
import com.leverx.odata.repository.impl.UserRepositoryImpl;
import com.leverx.odata.util.StringConstants;
import lombok.SneakyThrows;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class UserODataService implements ODataService<UserEdm> {

    @Override
    public Object getRelatedData(Object sourceData, String targetEntityName) throws ODataNotFoundException {
        User user = EdmMapper.fromUserEdm((UserEdm) sourceData);
        if (StringConstants.ES_PETS_NAME.equals(targetEntityName)) {
            return PetRepositoryImpl.getInstance().findAllByUser(user)
                    .stream()
                    .map(EdmMapper::toPetEdm)
                    .toList();
        }
        throw new ODataNotFoundException(ENTITY);
    }

    @Override
    public UserEdm getDataObject() {
        return new UserEdm();
    }

    @Override
    public List<UserEdm> findAll() {
        return UserRepositoryImpl.getInstance().findAll().stream()
                .map(EdmMapper::toUserEdm)
                .collect(toList());
    }

    @Override
    public UserEdm findById(long entityId) {
        return EdmMapper.toUserEdm(UserRepositoryImpl.getInstance().findById(entityId));
    }

    @Override
    @Transactional
    public UserEdm save(UserEdm sourceData) {
        User user = EdmMapper.fromUserEdm(sourceData);
        UserRepositoryImpl.getInstance().save(user);
        sourceData.setId(user.getId());
        return EdmMapper.toUserEdm(user);
    }

    @Override
    @SneakyThrows
    public void updateTargetKeysBySourceData(Map<String, Object> targetKeys, Object sourceData) {
        throw new ODataNotImplementedException();
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = UserRepositoryImpl.getInstance().findById(id);
        UserRepositoryImpl.getInstance().delete(user);
    }
}
