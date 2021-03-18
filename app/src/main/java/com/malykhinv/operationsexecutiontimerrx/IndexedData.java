package com.malykhinv.operationsexecutiontimerrx;

import java.util.List;
import java.util.Map;

public class IndexedData {
    public Integer index;
    public List<Byte> collection;
    public Map<Integer, Byte> map;

    public IndexedData(Integer index, List<Byte> collection) {
        this.index = index;
        this.collection = collection;
    }

    public IndexedData(Integer index, Map<Integer, Byte> map) {
        this.index = index;
        this.map = map;
    }

    public Integer getIndex() {
        return index;
    }

    public List<Byte> getCollection() {
        return collection;
    }

    public Map<Integer, Byte> getMap() {
        return map;
    }
}
