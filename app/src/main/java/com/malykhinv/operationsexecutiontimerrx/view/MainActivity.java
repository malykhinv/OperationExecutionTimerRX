package com.malykhinv.operationsexecutiontimerrx.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.tabs.TabLayoutMediator;
import com.malykhinv.operationsexecutiontimerrx.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.MainContract;
import com.malykhinv.operationsexecutiontimerrx.databinding.ActivityMainBinding;
import com.malykhinv.operationsexecutiontimerrx.presenter.CollectionsPresenter;
import com.malykhinv.operationsexecutiontimerrx.presenter.MainPresenter;
import com.malykhinv.operationsexecutiontimerrx.presenter.MapsPresenter;
import com.malykhinv.operationsexecutiontimerrx.view.fragments.CollectionsFragment;
import com.malykhinv.operationsexecutiontimerrx.view.fragments.MapsFragment;

public class MainActivity extends FragmentActivity implements MainContract.View {

    private static final String[] tabTitles = new String[] {"Collections", "Maps"};
    private ActivityMainBinding b;
    private ViewPagerAdapter pagerAdapter;
    private MainContract.Presenter presenter;

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

        presenter = new MainPresenter(this);

        b.fabCalculate.setOnClickListener(v -> {
            String size = String.valueOf(b.textInputNumberOfElements.getText());
            FragmentContract.View currentFragment = (FragmentContract.View) getSupportFragmentManager().findFragmentByTag("f" + b.pager.getCurrentItem());
            if (currentFragment != null) {
                if (currentFragment instanceof CollectionsFragment) presenter.onStartButtonWasClicked(new CollectionsPresenter(currentFragment), size);
                else if (currentFragment instanceof MapsFragment) presenter.onStartButtonWasClicked(new MapsPresenter(currentFragment), size);
            }
        });
    }

    @Override
    public void showError(String errorText) {
        Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}