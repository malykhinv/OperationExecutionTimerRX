package com.malykhinv.operationsexecutiontimerrx.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.tabs.TabLayoutMediator;
import com.malykhinv.operationsexecutiontimerrx.di.App;
import com.malykhinv.operationsexecutiontimerrx.mvp.FragmentContract;
import com.malykhinv.operationsexecutiontimerrx.mvp.MainContract;
import com.malykhinv.operationsexecutiontimerrx.databinding.ActivityMainBinding;
import com.malykhinv.operationsexecutiontimerrx.mvp.presenter.CollectionsPresenter;
import com.malykhinv.operationsexecutiontimerrx.mvp.presenter.MainPresenter;
import com.malykhinv.operationsexecutiontimerrx.mvp.presenter.MapsPresenter;
import com.malykhinv.operationsexecutiontimerrx.mvp.view.fragments.CollectionsFragment;
import com.malykhinv.operationsexecutiontimerrx.mvp.view.fragments.MapsFragment;

public class MainActivity extends FragmentActivity implements MainContract.View {

    private static final String[] tabTitles = new String[] {"Collections", "Maps"};
    private ActivityMainBinding b;
    private ViewPagerAdapter pagerAdapter;
    private MainContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
        initializeViewPager();
        presenter = new MainPresenter(this);
    }

    public void bind() {
        b = ActivityMainBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);

        b.fabCalculate.setOnClickListener(v -> {
            hideKeyboard();
            String size = String.valueOf(b.textInputNumberOfElements.getText());
            FragmentContract.View currentFragment = (FragmentContract.View) getSupportFragmentManager().findFragmentByTag("f" + b.pager.getCurrentItem());
            if (currentFragment != null) {
                if (currentFragment instanceof CollectionsFragment) presenter.onStartButtonWasClicked(new CollectionsPresenter(currentFragment), size);
                else if (currentFragment instanceof MapsFragment) presenter.onStartButtonWasClicked(new MapsPresenter(currentFragment), size);
            }
        });
    }

    public void initializeViewPager() {
        if (pagerAdapter == null) pagerAdapter = new ViewPagerAdapter(this);
        b.pager.setAdapter(pagerAdapter);
        new TabLayoutMediator(b.tabLayout, b.pager,
                (tab, position) -> tab.setText(tabTitles[position])).attach();
    }

    public void hideKeyboard() {
        App.getAppComponent().getContext();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null) inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}