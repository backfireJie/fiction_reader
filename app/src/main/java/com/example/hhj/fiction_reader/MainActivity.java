package com.example.hhj.fiction_reader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.hhj.fiction_reader.bean.Tab;
import com.example.hhj.fiction_reader.fragment.AnecdoteFragment;
import com.example.hhj.fiction_reader.fragment.CommentFragment;
import com.example.hhj.fiction_reader.fragment.MasterpiecesFragment;
import com.example.hhj.fiction_reader.fragment.MineFragment;
import com.example.hhj.fiction_reader.fragment.OtherFragment;
import com.example.hhj.fiction_reader.fragment.SubjectFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LayoutInflater mInflater;
    private FragmentTabHost mTabHost ;
    private Fragment mFragment;
    private List<Tab> mTabs = new ArrayList<>(5);
    private Button btnBack ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnBack = (Button) findViewById(R.id.btn_back_main);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
        initTab();
    }

    private void initTab() {
        Tab tabMaster = new Tab(R.string.master, R.drawable.selector_master,MasterpiecesFragment.class);
        Tab tabAnecdote = new Tab(R.string.anecdote, R.drawable.selector_anecdote,AnecdoteFragment.class);
        Tab tabSubject = new Tab(R.string.subject, R.drawable.selector_subject,SubjectFragment.class);
        Tab tabComment = new Tab(R.string.comment, R.drawable.selector_comment,CommentFragment.class);
        Tab tabOther = new Tab(R.string.other, R.drawable.selector_other,OtherFragment.class);
        Tab tabMine = new Tab(R.string.mine,R.drawable.selector_mine, MineFragment.class);
        mTabs.add(tabMaster);
        mTabs.add(tabAnecdote);
        mTabs.add(tabSubject);
        mTabs.add(tabComment);
        mTabs.add(tabOther);
        mTabs.add(tabMine);
        mInflater = LayoutInflater.from(this);
        mTabHost= (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabHost.setup(this,this.getSupportFragmentManager(),R.id.real_tabcontent);

        for(Tab tab :mTabs){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabHost.addTab(tabSpec,tab.getFragment(),null);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        mTabHost.setCurrentTab(0);


    }
    private View buildIndicator(Tab tab){
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        ImageView icon = (ImageView) view.findViewById(R.id.iv_icon);
        icon.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;

    }

}
