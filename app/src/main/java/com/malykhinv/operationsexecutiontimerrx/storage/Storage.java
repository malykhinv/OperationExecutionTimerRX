package com.malykhinv.operationsexecutiontimerrx.storage;

public interface Storage {
    void write(String prefix, int index, String string);
    String read(String prefix, int index);
    Boolean contains(String prefix, String key);
    void clear(String prefix);
}
