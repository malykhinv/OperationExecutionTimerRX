package com.malykhinv.operationsexecutiontimerrx.view;

import com.malykhinv.operationsexecutiontimerrx.view.fragments.AbstractFragment;

public interface CalculationView {
    void resetCellText(AbstractFragment fragment);
    void showProgress(AbstractFragment fragment);
    void hideProgress(AbstractFragment fragment);
    void hideProgress(AbstractFragment fragment, int index);
    void updateTime(AbstractFragment fragment, int index, String resultTime);
    void showError(String errorText);
}
