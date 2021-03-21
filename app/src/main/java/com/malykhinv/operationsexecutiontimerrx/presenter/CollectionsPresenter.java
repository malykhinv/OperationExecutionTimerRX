package com.malykhinv.operationsexecutiontimerrx.presenter;

import com.malykhinv.operationsexecutiontimerrx.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.model.operators.CollectionsModel;

public class CollectionsPresenter implements FragmentContract.Presenter, CollectionsModel.Callback {

    private final FragmentContract.View view;
    private final FragmentContract.Model model;

    public CollectionsPresenter(FragmentContract.View view) {
        this.view = view;
        this.model = new CollectionsModel();
    }

    // From View:

    @Override
    public void onStartButtonWasClicked(String size) {
        view.showProgress();
        view.resetCellText();
        model.registerCallback(this);
        model.execute(size);
    }


    // From Model:

    @Override
    public void onSingleCalculationComplete(int index, String time) {
        view.hideProgress(index);
        view.updateTime(index, time);
    }

    @Override
    public void onError(Throwable e) {
        view.hideProgress();
        view.resetCellText();
        view.showError(e.getMessage());
    }
}
