package com.malykhinv.operationsexecutiontimerrx.mvp.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.malykhinv.operationsexecutiontimerrx.mvp.view.fragments.CollectionsFragment;
import com.malykhinv.operationsexecutiontimerrx.mvp.view.fragments.MapsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private static final int TAB_COUNT = 2;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new CollectionsFragment();
            case 1: return new MapsFragment();
        }
        return new CollectionsFragment();
    }

    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }

}
