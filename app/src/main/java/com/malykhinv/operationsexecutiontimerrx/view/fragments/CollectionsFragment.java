package com.malykhinv.operationsexecutiontimerrx.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.malykhinv.operationsexecutiontimerrx.R;
import com.malykhinv.operationsexecutiontimerrx.databinding.FragmentCollectionsBinding;
import org.jetbrains.annotations.NotNull;

import dagger.Module;

@Module
public class CollectionsFragment extends AbstractFragment {

    private static final int NUMBER_OF_OPERATIONS = 7;
    private static final int NUMBER_OF_COLLECTIONS = 3;
    private static final String PREFERENCE_PREFIX = "C";        // Prefix for collections saved data ("C0"..."C20")
    private FragmentCollectionsBinding b;
    private View view = null;
    private Context context;
    private SharedPreferences sharedPreferences;
    private TextView[] textViews;
    private ProgressBar[] progressBars;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
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
    public void hideProgress() {
        for (ProgressBar pb : progressBars) pb.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress(int index) {
        progressBars[index].setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateTime(int index, String text) {
        textViews[index].setText(String.format("%s%s", text, context.getString(R.string.postfix_time_unit)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        context = null;
        sharedPreferences = null;
        b = null;
        textViews = null;
        progressBars = null;
    }
}