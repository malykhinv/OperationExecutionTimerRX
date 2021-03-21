package com.malykhinv.operationsexecutiontimerrx.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.malykhinv.operationsexecutiontimerrx.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.R;
import com.malykhinv.operationsexecutiontimerrx.databinding.FragmentMapsBinding;
import org.jetbrains.annotations.NotNull;

public class MapsFragment extends Fragment implements FragmentContract.View {

    private FragmentMapsBinding b;
    private View view = null;
    private Context context;
    private TextView[] textViews;
    private ProgressBar[] progressBars;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
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
        b = null;
        textViews = null;
        progressBars = null;
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
    public void updateTime(int index, String resultTime) {
        textViews[index].setText(String.format("%s%s", resultTime, context.getString(R.string.postfix_time_unit)));
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}