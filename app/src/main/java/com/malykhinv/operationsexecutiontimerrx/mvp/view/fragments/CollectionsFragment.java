package com.malykhinv.operationsexecutiontimerrx.mvp.view.fragments;

import android.content.Context;
import android.os.Bundle;
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
import com.malykhinv.operationsexecutiontimerrx.databinding.FragmentCollectionsBinding;
import com.malykhinv.operationsexecutiontimerrx.mvp.presenter.CollectionsPresenter;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class CollectionsFragment extends Fragment implements FragmentContract.View {

    private FragmentCollectionsBinding b;
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
        b = FragmentCollectionsBinding.inflate(inflater, container, false);
        if (view == null) view = b.getRoot();
        else ((ViewGroup) view.getParent()).removeView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViews = new TextView[] {
                b.textAddingInTheBeginningArrayListTime, b.textAddingInTheMiddleArrayListTime, b.textAddingInTheEndArrayListTime, b.textSearchByValueArrayListTime, b.textRemoveInTheBeginningArrayListTime, b.textRemoveInTheMiddleArrayListTime, b.textRemoveInTheEndArrayListTime,
                b.textAddingInTheBeginningLinkedListTime, b.textAddingInTheMiddleLinkedListTime, b.textAddingInTheEndLinkedListTime, b.textSearchByValueLinkedListTime, b.textRemoveInTheBeginningLinkedListTime, b.textRemoveInTheMiddleLinkedListTime, b.textRemoveInTheEndLinkedListTime,
                b.textAddingInTheBeginningCopyOnWriteArrayListTime, b.textAddingInTheMiddleCopyOnWriteArrayListTime, b.textAddingInTheEndCopyOnWriteArrayListTime, b.textSearchByValueCopyOnWriteArrayListTime, b.textRemoveInTheBeginningCopyOnWriteArrayListTime, b.textRemoveInTheMiddleCopyOnWriteArrayListTime, b.textRemoveInTheEndCopyOnWriteArrayListTime
        };

        progressBars = new ProgressBar[] {
                b.progressBarAddingInTheBeginningArrayList, b.progressBarAddingInTheMiddleArrayList, b.progressBarAddingInTheEndArrayList, b.progressBarSearchByValueArrayList, b.progressBarRemoveInTheBeginningArrayList, b.progressBarRemoveInTheMiddleArrayList, b.progressBarRemoveInTheEndArrayList,
                b.progressBarAddingInTheBeginningLinkedList, b.progressBarAddingInTheMiddleLinkedList, b.progressBarAddingInTheEndLinkedList, b.progressBarSearchByValueLinkedList, b.progressBarRemoveInTheBeginningLinkedList, b.progressBarRemoveInTheMiddleLinkedList, b.progressBarRemoveInTheEndLinkedList,
                b.progressBarAddingInTheBeginningCopyOnWriteArrayList, b.progressBarAddingInTheMiddleCopyOnWriteArrayList, b.progressBarAddingInTheEndCopyOnWriteArrayList, b.progressBarSearchByValueCopyOnWriteArrayList, b.progressBarRemoveInTheBeginningCopyOnWriteArrayList, b.progressBarRemoveInTheMiddleCopyOnWriteArrayList, b.progressBarRemoveInTheEndCopyOnWriteArrayList
        };

        if (presenter == null) presenter = new CollectionsPresenter(this);
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