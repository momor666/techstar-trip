package com.dglproject.tour.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dglproject.tour.R;
import com.dglproject.tour.fragments.AddFragment;
import com.dglproject.tour.fragments.CompanyFragment;
import com.dglproject.tour.fragments.HomeFragment;
import com.dglproject.tour.fragments.NavigationFragment;
import com.dglproject.tour.fragments.SearchFragment;
import com.dglproject.tour.widgets.CustomViewPager;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private int[] mTabsIcons = {
            R.drawable.dgl_white_home,
            R.drawable.dgl_white_favorite,
            R.drawable.dgl_white_add,
            R.drawable.dgl_white_search,
            R.drawable.dgl_white_info};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        if (viewPager != null) {
            viewPager.setPagingEnabled(false);
            viewPager.setAdapter(pagerAdapter);
        }

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (mTabLayout != null) {
            mTabLayout.setupWithViewPager(viewPager);

            for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = mTabLayout.getTabAt(i);
                if (tab != null)
                    tab.setCustomView(pagerAdapter.getTabView(i));
            }

            mTabLayout.getTabAt(0).getCustomView().setSelected(true);
        }
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public final int PAGE_COUNT = 5;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public View getTabView(int position) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            icon.setImageResource(mTabsIcons[position]);
            return view;
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return HomeFragment.newInstance(1);
                case 1:
                    return CompanyFragment.newInstance(2);
                case 2:
                    return AddFragment.newInstance(3);
                case 3:
                    return SearchFragment.newInstance(4);
                case 4:
                    return NavigationFragment.newInstance(5);

            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }
}