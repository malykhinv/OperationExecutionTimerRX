package com.malykhinv.operationsexecutiontimerrx.mvp.presenter;

import com.malykhinv.operationsexecutiontimerrx.di.App;
import com.malykhinv.operationsexecutiontimerrx.mvp.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.mvp.model.CollectionsModel;
import java.util.ArrayList;

public class CollectionsPresenter implements FragmentContract.Presenter, CollectionsModel.Callback {

    private final FragmentContract.View view;
    private FragmentContract.Model model;

    public CollectionsPresenter(FragmentContract.View view) {
        this.view = view;
        if (model == null) model = App.getAppComponent().getCollectionsModel();
        model.registerCallback(this);
    }


    // From View:

    @Override
    public void onViewIsReady() {
        ArrayList<String> listOfResultTime = model.readResultTimeFromDisk();
        if (listOfResultTime != null) view.updateTime(listOfResultTime);
    }

    @Override
    public void onStartButtonWasClicked(String size) {
        view.resetCellText();
        view.showProgress();
        model.clearSavedData();
        model.execute(size);
    }


    // From Model:

    @Override
    public void onSingleCalculationComplete(int index, String resultTime) {
        model.writeResultTimeOnDisk(index, resultTime);
    }

    @Override
    public void onSingleResultWasSavedOnDisk(int index) {
        String time = model.readResultTimeFromDisk(index);
        if (time != null) {
            view.hideProgress(index);
            view.updateTime(index, time);
        }
    }

    @Override
    public void onError(Throwable e) {
        view.showError(e.getMessage());
    }
}
