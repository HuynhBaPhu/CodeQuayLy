package com.example.doanjv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout1;
    private ViewPager viewPager1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout1 = findViewById(R.id.tab_layout);
        viewPager1 = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter =  new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager1.setAdapter(viewPagerAdapter);
        tabLayout1.setupWithViewPager(viewPager1);
    }
}