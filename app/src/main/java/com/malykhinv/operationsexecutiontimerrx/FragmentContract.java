package com.malykhinv.operationsexecutiontimerrx;

import java.util.List;

public interface FragmentContract {

    interface View {
        void resetCellText();
        void showProgress();
        void hideProgress();
        void hideProgress(int index);
        void updateTime(int index, String resultTime);
    }

    interface Presenter {
        void start(String size);
        void execute(String size);
        void detachView();
    }

    interface Model {
        List<IndexedData> getIndexedData();
        IndexedData fillIndexedData(IndexedData data, int size);
        OperationResult operateWithIndexedData(IndexedData data);
    }
}
