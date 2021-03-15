package com.malykhinv.operationsexecutiontimerrx.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.tabs.TabLayoutMediator;
import com.malykhinv.operationsexecutiontimerrx.model.MainModel;
import com.malykhinv.operationsexecutiontimerrx.presenter.MainPresenter;
import com.malykhinv.operationsexecutiontimerrx.view.fragments.AbstractFragment;
import com.malykhinv.operationsexecutiontimerrx.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity implements CalculationView {

    private static final String[] tabTitles = new String[] {"Collections", "Maps"};
    private ActivityMainBinding b;
    private ViewPagerAdapter pagerAdapter;
    private MainPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);

        if (pagerAdapter == null) pagerAdapter = new ViewPagerAdapter(this);
        b.pager.setAdapter(pagerAdapter);
        new TabLayoutMediator(b.tabLayout, b.pager,
                (tab, position) -> tab.setText(tabTitles[position])).attach();

        MainModel model = new MainModel();
        presenter = new MainPresenter(model, getApplicationContext());
        presenter.attachView(this);

        b.fabCalculate.setOnClickListener(v -> {
            AbstractFragment fragment = (AbstractFragment) getSupportFragmentManager().findFragmentByTag("f" + b.pager.getCurrentItem());
            String size = String.valueOf(b.textInputNumberOfElements.getText());
            presenter.start(fragment, size);
        });
    }

    @Override
    public void resetCellText(AbstractFragment fragment) {
        fragment.resetCellText();
    }

    @Override
    public void showProgress(AbstractFragment fragment) {
        fragment.showProgress();
    }

    @Override
    public void hideProgress(AbstractFragment fragment) {
        fragment.hideProgress();
    }

    @Override
    public void hideProgress(AbstractFragment fragment, int index) {
        fragment.hideProgress(index);
    }

    @Override
    public void updateTime(AbstractFragment fragment, int index, String resultTime) {
        fragment.updateTime(index, resultTime);
    }

    @Override
    public void showError(String errorText) {
        Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}