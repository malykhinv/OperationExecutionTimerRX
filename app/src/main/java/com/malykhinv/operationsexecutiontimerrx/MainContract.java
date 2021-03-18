package com.malykhinv.operationsexecutiontimerrx;

public interface MainContract {
    interface View {
        void showError(String errorText);
    }

    interface Presenter {
        void onStartButtonWasClicked(FragmentContract.Presenter fragmentPresenter, String size);
        void detachView();
    }
}
