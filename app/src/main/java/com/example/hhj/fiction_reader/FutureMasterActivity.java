package com.example.hhj.fiction_reader;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.hhj.fiction_reader.adapter.FragPagerAdapter;
import com.example.hhj.fiction_reader.fragment.FutureAllFragment;
import com.example.hhj.fiction_reader.fragment.FutureHotFragment;
import com.example.hhj.fiction_reader.fragment.FutureOtherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by backfire on 2016/10/18.
 */
public class FutureMasterActivity extends AppCompatActivity {
    private TabLayout mTablayout;
    private ViewPager viewPager;
    private Toolbar mToolbar;

    private LayoutInflater mInflater;
    private List<View> viewList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private View viewAll;
    private View viewHot;
    private View viewOther;

    private Button btnBack;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_master);
        initView();
        initTab();



    }

    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FutureMasterActivity.this.finish();
            }
        });
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });


    }

    private void initTab() {
        mInflater = LayoutInflater.from(this);
        viewAll = mInflater.inflate(R.layout.future_tab_item_all,null);
        viewHot = mInflater.inflate(R.layout.future_tab_item_hot,null);
        viewOther = mInflater.inflate(R.layout.future_tab_item_other,null);
        titleList.add("全部");
        titleList.add("热榜");
        titleList.add("活动");
        viewList.add(viewAll);
        viewList.add(viewHot);
        viewList.add(viewOther);

        mTablayout.addTab(mTablayout.newTab().setText(titleList.get(0)));
        mTablayout.addTab(mTablayout.newTab().setText(titleList.get(1)));
        mTablayout.addTab(mTablayout.newTab().setText(titleList.get(2)));


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FutureAllFragment());
        fragments.add(new FutureHotFragment());
        fragments.add(new FutureOtherFragment());
        FragPagerAdapter adapter = new FragPagerAdapter(getSupportFragmentManager(),titleList,fragments);
        viewPager.setAdapter(adapter);
        mTablayout.setupWithViewPager(viewPager);


       /* PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
            //做了两件事，第一：将当前视图添加到container中，第二：返回当前View
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return  viewList.get(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);*/
        // slider = (SliderLayout) findViewById(R.id.slider);
        // rvFutureAll = (RecyclerView) findViewById(R.id.rv_future_all);
        // refreshFutureAll = (MaterialRefreshLayout) findViewById(R.id.refresh_future_all);

    }











}

