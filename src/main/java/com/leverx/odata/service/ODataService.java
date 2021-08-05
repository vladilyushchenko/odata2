package com.leverx.odata.service;

import com.leverx.odata.entity.edm.EntityEdm;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.List;
import java.util.Map;

public interface ODataService<T extends EntityEdm> {

    // TODO: delete parameter T in this function
    <T extends EntityEdm> Object getRelatedData(Object sourceData, String targetEntityName) throws ODataNotFoundException, ODataNotImplementedException;

    T getDataObject();

    List<T> findAll();

    T findById(long entityId);

    T save(T sourceData) throws ODataNotImplementedException;

    void updateTargetKeysBySourceData(Map<String, Object> targetKeys, Object sourceData);

    void delete(long id);
}
