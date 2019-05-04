package com.example.giantviewsample.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.giantviewsample.R;
import com.example.giantviewsample.model.NewsDataModel;
import com.example.giantviewsample.utils.Constants;
import com.example.giantviewsample.view.adapters.ViewPagerFragmentAdapter;
import com.example.giantviewsample.view.fragments.NewsByCategoriesFragment;
import com.example.giantviewsample.view.fragments.NewsHeadLinesFragment;
import com.example.giantviewsample.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private  List<Fragment> mFragments = new ArrayList<>();


    private static final String[] pageTitles = new String[]{"For U", "Pune", "IPL2019", "Entertainment", "India",
            "Lifestyle", "Sports", "Fun", "Education", "Technology"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createFragmentList();
        initViewPager();
    }




    private void initViewPager() {

        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);

        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), mFragments, pageTitles);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mViewPager.setOffscreenPageLimit(mFragments.size());

    }

    private void createFragmentList() {

        mFragments.clear();

        mFragments.add(new NewsHeadLinesFragment());

        for (int i = 1; i < pageTitles.length ; i++){
            Bundle bundle = new Bundle();
            bundle.putString(Constants.TOPIC_NAME, pageTitles[i]);

            NewsByCategoriesFragment fragment = new NewsByCategoriesFragment();
            fragment.setArguments(bundle);

            mFragments.add(fragment);
        }
    }

}
