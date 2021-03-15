package com.malykhinv.operationsexecutiontimerrx.presenter;

import com.malykhinv.operationsexecutiontimerrx.view.CalculationView;
import com.malykhinv.operationsexecutiontimerrx.view.fragments.AbstractFragment;

public interface CalculationPresenter {
    void attachView(CalculationView view);
    void detachView();
    void start(AbstractFragment fragment, String size);
}
