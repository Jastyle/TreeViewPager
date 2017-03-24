package com.jastyle.treeviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jastyle.treeviewpager.treeView.LogisticsData;
import com.jastyle.treeviewpager.treeView.WrapContentHeightViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WrapContentHeightViewPager viewPager;
    private TreePagerAdapter treePagerAdapter;
    private List<List<LogisticsData>> logisticsDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        initView();
        initData();
    }

    private void initView() {
        viewPager = (WrapContentHeightViewPager) findViewById(R.id.report_pager);
    }
    private void initData() {
        logisticsDatas = new ArrayList<>();
        for (int i =0;i<=3;i++) {
            List<LogisticsData> logtmp = logtmp = new ArrayList<>();
            for (int j=0;j<=3;j++) {
                logtmp.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("在【相城中转仓】,正发往【无锡分拨中心】已签收,签收人是【王漾快件在【相城中转仓】装车").setTitle("依然都没有"));
            }
            logisticsDatas.add(logtmp);
        }
        treePagerAdapter = new TreePagerAdapter(MainActivity.this,logisticsDatas);
        viewPager.setAdapter(treePagerAdapter);
        viewPager.setOffscreenPageLimit(4);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
