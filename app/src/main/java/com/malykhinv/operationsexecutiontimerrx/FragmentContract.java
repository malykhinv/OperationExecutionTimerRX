package com.malykhinv.operationsexecutiontimerrx;

import java.util.List;

public interface FragmentContract {

    interface View {
        void resetCellText();
        void showProgress();
        void hideProgress();
        void hideProgress(int index);
        void updateTime(int index, String resultTime);
        void showError(String errorMessage);
    }

    interface Presenter {
        void onStartButtonWasClicked(String size);
    }

    interface Model {
        List<IndexedData> getIndexedData();
        IndexedData fillIndexedData(IndexedData data, int size);
        OperationResult operateWithIndexedData(IndexedData data);
        void registerCallback(FragmentContract.Model.Callback callback);
        void execute(String size);

        interface Callback {
            void onSingleCalculationComplete(int index, String time);
            void onError(Throwable e);
        }
    }
}
