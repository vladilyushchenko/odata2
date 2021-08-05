package com.leverx.odata.datasource;

import com.leverx.odata.entity.edm.EntityEdm;
import lombok.SneakyThrows;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.List;
import java.util.Map;

public class AppDataSource implements DataSource {

    private final ODataServiceProvider oDataServiceProvider;

    public AppDataSource() {
        this.oDataServiceProvider = new ODataServiceProvider();
    }

    @Override
    public List<?> readData(EdmEntitySet entitySet) throws ODataNotFoundException, EdmException {
        String entitySetName = entitySet.getName();
        return oDataServiceProvider
                .getService(entitySetName)
                .findAll();
    }

    @Override
    public Object readData(EdmEntitySet entitySet, Map<String, Object> keys) throws ODataNotFoundException, EdmException {
        String entitySetName = entitySet.getName();
        Long firstLayerEntityId = (Long) keys.get("Id");
        return oDataServiceProvider.getService(entitySetName)
                .findById(firstLayerEntityId);
    }

    @Override
    public Object readData(EdmFunctionImport function, Map<String, Object> parameters, Map<String, Object> keys) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public Object readRelatedData(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet, Map<String, Object> targetKeys) throws EdmException, ODataNotImplementedException, ODataNotFoundException {
        String sourceEntityName = sourceEntitySet.getName();
        String targetEntityName = targetEntitySet.getName();
        return oDataServiceProvider.getService(sourceEntityName)
                .getRelatedData(sourceData, targetEntityName);
    }

    @Override
    public BinaryData readBinaryData(EdmEntitySet entitySet, Object mediaLinkEntryData) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @SneakyThrows
    @Override
    public Object newDataObject(EdmEntitySet entitySet) {
        String entitySetName = entitySet.getName();
        return oDataServiceProvider.getService(entitySetName)
                .getDataObject();
    }

    @Override
    public void writeBinaryData(EdmEntitySet entitySet, Object mediaLinkEntryData, BinaryData binaryData)
            throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public void deleteData(EdmEntitySet entitySet, Map<String, Object> keys)
            throws EdmException, ODataNotFoundException {
        String entitySetName = entitySet.getName();
        Long id = (Long) keys.get("Id");
        oDataServiceProvider.getService(entitySetName)
                .delete(id);
    }

    @SneakyThrows
    @Override
    public void createData(EdmEntitySet entitySet, Object data) {
        String entitySetName = entitySet.getName();
        oDataServiceProvider.getService(entitySetName)
                .save((EntityEdm) data);
    }

    @Override
    public void deleteRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet,
                               Map<String, Object> targetKeys) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @SneakyThrows
    @Override
    public void writeRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet,
                              Map<String, Object> targetKeys) {
        oDataServiceProvider.getService(targetEntitySet.getName())
                .updateTargetKeysBySourceData(targetKeys, sourceData);
    }
}
