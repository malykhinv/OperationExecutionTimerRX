package com.malykhinv.operationsexecutiontimerrx.model;


import com.malykhinv.operationsexecutiontimerrx.IndexedData;
import com.malykhinv.operationsexecutiontimerrx.OperationResult;
import java.util.List;

public interface CalculationModel<T> {
    List<IndexedData<Integer, T>> getIndexedData();
    IndexedData<Integer, T>  fillIndexedData(IndexedData<Integer, T> indexedData, int size);
    OperationResult operateWithData(IndexedData<Integer, T>  indexedData);
}
