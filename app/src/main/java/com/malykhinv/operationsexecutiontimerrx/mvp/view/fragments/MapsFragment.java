package com.malykhinv.operationsexecutiontimerrx.mvp.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.malykhinv.operationsexecutiontimerrx.di.App;
import com.malykhinv.operationsexecutiontimerrx.mvp.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.R;
import com.malykhinv.operationsexecutiontimerrx.databinding.FragmentMapsBinding;
import com.malykhinv.operationsexecutiontimerrx.mvp.presenter.MapsPresenter;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class MapsFragment extends Fragment implements FragmentContract.View {

    private FragmentMapsBinding b;
    private View view;
    private Context context;
    private TextView[] textViews;
    private ProgressBar[] progressBars;
    private FragmentContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = App.getAppComponent().getContext();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentMapsBinding.inflate(inflater, container, false);
        if (view == null) view = b.getRoot();
        else ((ViewGroup) view.getParent()).removeView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViews = new TextView[] {
                b.textAddingTreeMapTime, b.textAddingHashMapTime,
                b.textSearchByKeyTreeMapTime, b.textSearchByKeyHashMapTime,
                b.textRemovingTreeMapTime, b.textRemovingHashMapTime
        };

        progressBars = new ProgressBar[] {
                b.progressBarAddingTreeMap, b.progressBarAddingHashMap,
                b.progressBarSearchByKeyTreeMap, b.progressBarSearchByKeyHashMap,
                b.progressBarRemovingTreeMap, b.progressBarRemovingHashMap
        };

        if (presenter == null) presenter = new MapsPresenter(this);
        presenter.onViewIsReady();
    }

    @Override
    public void onStartButtonWasClicked(String size) {
        presenter.onStartButtonWasClicked(size);
    }

    @Override
    public void resetCellText() {
        for (TextView tv : textViews) tv.setText(context.getString(R.string.default_cell_text));
    }

    @Override
    public void showProgress() {
        for (ProgressBar pb : progressBars) pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress(int index) {
        if (progressBars != null) progressBars[index].setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateTime(int index, String resultTime) {
        if (textViews != null) textViews[index].setText(String.format("%s%s", resultTime, context.getString(R.string.postfix_time_unit)));
    }

    @Override
    public void updateTime(ArrayList<String> listOfResultTime) {
        if (listOfResultTime != null && textViews.length == listOfResultTime.size()) {
            for (int i = 0; i < textViews.length; i++)
                if (listOfResultTime.get(i) != null) textViews[i].setText(String.format("%s%s", listOfResultTime.get(i), context.getString(R.string.postfix_time_unit)));
                else {
                    textViews[i].setText(context.getString(R.string.default_cell_text));
                    progressBars[i].setVisibility(View.VISIBLE);
                }
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        context = null;
        b = null;
        textViews = null;
        progressBars = null;
    }
}