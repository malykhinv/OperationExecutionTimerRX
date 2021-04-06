package com.malykhinv.operationsexecutiontimerrx.mvp.presenter;

import com.malykhinv.operationsexecutiontimerrx.R;
import com.malykhinv.operationsexecutiontimerrx.di.App;
import com.malykhinv.operationsexecutiontimerrx.mvp.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.mvp.MainContract;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    public void onStartButtonWasClicked(FragmentContract.View fragmentView, String size) {
        if (fragmentView != null) {
            if (!size.isEmpty() && Integer.parseInt(size) > 0) fragmentView.onStartButtonWasClicked(size);
            else view.showError(App.getAppComponent().getContext().getString(R.string.error_wrong_size));
        }
    }

    public void detachView() {
        view = null;
    }
}