package com.malykhinv.operationsexecutiontimerrx.view.fragments;

import androidx.fragment.app.Fragment;

public abstract class AbstractFragment extends Fragment{

    public abstract void resetCellText();
    public abstract void showProgress();
    public abstract void hideProgress();
    public abstract void hideProgress(int index);
    public abstract void updateTime(int index, String text);
}
