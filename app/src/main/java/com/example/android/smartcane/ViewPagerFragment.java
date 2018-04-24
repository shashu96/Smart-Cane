package com.example.android.smartcane;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shravan on 4/17/2018.
 */

public class ViewPagerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        //TabLayout tabLayout= (TabLayout) view.findViewById(R.id.tabLayout);

        final com.example.android.smartcane.ButtonFragment buttonFragment = new com.example.android.smartcane.ButtonFragment();
        final com.example.android.smartcane.BluetoothFragment bluetoothFragment = new com.example.android.smartcane.BluetoothFragment();

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position==0?buttonFragment:bluetoothFragment;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "" : "";
            }
        });

        //tabLayout.setupWithViewPager(viewPager);
        return view;

    }
}