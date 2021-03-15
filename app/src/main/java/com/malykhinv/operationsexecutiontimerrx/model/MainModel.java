package com.malykhinv.operationsexecutiontimerrx.model;

import com.malykhinv.operationsexecutiontimerrx.IndexedData;
import com.malykhinv.operationsexecutiontimerrx.OperationResult;
import com.malykhinv.operationsexecutiontimerrx.model.operators.CollectionsOperator;
import com.malykhinv.operationsexecutiontimerrx.view.fragments.AbstractFragment;
import com.malykhinv.operationsexecutiontimerrx.view.fragments.CollectionsFragment;

import java.util.List;

public class MainModel {

    CollectionsOperator collectionsOperator = new CollectionsOperator();

    public List<?> getIndexedData(AbstractFragment fragment) {
        if (fragment instanceof CollectionsFragment) return (List<IndexedData<Integer, List<Byte>>>) collectionsOperator.getIndexedData();
        else return null;
    }

    public IndexedData<Integer, ?> fillIndexedData(IndexedData<Integer, ?> indexedData, int size) {
        if (indexedData.data instanceof List) return collectionsOperator.fillIndexedData((IndexedData<Integer, List<Byte>>)indexedData, size);
        else return null;
    }

    public OperationResult operateWithData(IndexedData<Integer, ?> indexedData) {
        if (indexedData.data instanceof List) return collectionsOperator.operateWithData((IndexedData<Integer, List<Byte>>) indexedData);
        else return null;
    }


}
