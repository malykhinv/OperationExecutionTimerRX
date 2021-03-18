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

import com.malykhinv.operationsexecutiontimerrx.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.MainContract;
import com.malykhinv.operationsexecutiontimerrx.databinding.FragmentMapsBinding;
import org.jetbrains.annotations.NotNull;

public class MapsFragment extends Fragment implements FragmentContract.View {

    public final int NUMBER_OF_OPERATIONS = 3;
    public final int NUMBER_OF_MAPS = 2;
    private static final String PREFERENCE_PREFIX = "M";        // Prefix for maps saved data ("M0"..."M11")
    private FragmentMapsBinding b;
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

    @Override
    public void resetCellText() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void hideProgress(int index) {

    }

    @Override
    public void updateTime(int index, String resultTime) {

    }
}