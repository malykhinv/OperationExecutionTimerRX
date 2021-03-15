package com.malykhinv.operationsexecutiontimerrx;

public class OperationResult {
    int index;
    long executionTime;

    public OperationResult(int index, long executionTime) {
        this.index = index;
        this.executionTime = executionTime;
    }

    public int getIndex() {
        return index;
    }

    public Long getExecutionTime() {
        return executionTime;
    }
}
