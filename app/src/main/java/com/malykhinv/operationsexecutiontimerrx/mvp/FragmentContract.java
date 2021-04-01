package com.malykhinv.operationsexecutiontimerrx.mvp;

import com.malykhinv.operationsexecutiontimerrx.IndexedData;
import com.malykhinv.operationsexecutiontimerrx.OperationResult;
import java.util.ArrayList;
import java.util.List;

public interface FragmentContract {

    interface View {
        void resetCellText();
        void showProgress();
        void hideProgress(int index);
        void updateTime(int index, String resultTime);
        void updateTime(ArrayList<String> listOfResultTime);
        void showError(String errorMessage);
    }

    interface Presenter {
        void onViewIsReady();
        void onStartButtonWasClicked(String size);
    }

    interface Model {
        List<IndexedData> getIndexedData();
        IndexedData fillIndexedData(IndexedData data, int size);
        OperationResult operateWithIndexedData(IndexedData data);
        void registerCallback(FragmentContract.Model.Callback callback);
        void execute(String size);
        void writeResultTimeOnDisk(int index, String resultTime);
        String readResultTimeFromDisk(int index);
        ArrayList<String> readResultTimeFromDisk();

        interface Callback {
            void onSingleCalculationComplete(int index, String resultTime);
            void onSingleResultWasSavedOnDisk(int index);
            void onError(Throwable e);
        }
    }
}
