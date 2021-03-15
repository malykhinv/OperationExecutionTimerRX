package com.malykhinv.operationsexecutiontimerrx;

public class IndexedData<Integer, T> {
    public Integer index;
    public T data;

    public IndexedData(Integer index, T data) {
        this.index = index;
        this.data = data;
    }

    public Integer getIndex() {
        return index;
    }

    public T getData() {
        return data;
    }
}
