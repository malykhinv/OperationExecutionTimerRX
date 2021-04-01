package com.malykhinv.operationsexecutiontimerrx.mvp.presenter;

import com.malykhinv.operationsexecutiontimerrx.mvp.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.mvp.model.MapsModel;
import java.util.ArrayList;

public class MapsPresenter implements FragmentContract.Presenter, FragmentContract.Model.Callback {

    private final FragmentContract.View view;
    private final FragmentContract.Model model;

    public MapsPresenter(FragmentContract.View view) {
        this.view = view;
        this.model = new MapsModel();
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
        model.registerCallback(this);
        model.execute(size);
    }


    // From Model:

    @Override
    public void onSingleCalculationComplete(int index, String resultTime) {
        model.writeResultTimeOnDisk(index, resultTime);
    }

    @Override
    public void onSingleResultWasSavedOnDisk(int index) {
        view.hideProgress(index);
        String time = model.readResultTimeFromDisk(index);
        if (time != null) {
            view.updateTime(index, time);
        }
    }

    @Override
    public void onError(Throwable e) {
        view.showError(e.getMessage());
    }
}
