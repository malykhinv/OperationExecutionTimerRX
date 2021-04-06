package com.malykhinv.operationsexecutiontimerrx.mvp;

public interface MainContract {
    interface View {
        void showError(String errorText);
    }

    interface Presenter {
        void onStartButtonWasClicked(FragmentContract.View fragmentView, String size);
        void detachView();
    }
}
