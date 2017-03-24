package com.jastyle.treeviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jastyle.treeviewpager.treeView.LogisticsData;
import com.jastyle.treeviewpager.treeView.TreeView;
import com.jastyle.treeviewpager.treeView.TreeViewAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jast on 2016/11/18.
 */

public class TreePagerAdapter extends PagerAdapter {
    private List<List<LogisticsData>> logisticsDatas;
    private LinkedList<View> mViewCache;
    private LayoutInflater mLayoutInflater;
    private Context context;
    public TreePagerAdapter(Context context, List<List<LogisticsData>> logisticsDatas) {
        this.logisticsDatas = logisticsDatas;
        this.mViewCache =  new LinkedList<View>();
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return logisticsDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = null;
        if (mViewCache.size() == 0) {
            convertView = this.mLayoutInflater.inflate(R.layout.pager_item,null,false);
        }else {
            convertView = mViewCache.removeFirst();
        }
        ChildHolder childHolder = getChildHolder(convertView);
        TreeViewAdapter treeViewAdapter = new TreeViewAdapter(context,childHolder.treeView,logisticsDatas);
        childHolder.treeView.setAdapter(treeViewAdapter);
        container.addView(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Log.d("创建","position:"+position);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViewCache.add((View) object);
        Log.d("销毁","position:"+position);

    }

    private class ChildHolder {
        TreeView treeView;
        public ChildHolder(View view) {
            treeView = (TreeView) view.findViewById(R.id.tree_view);
        }
    }
    private ChildHolder getChildHolder(View view) {
        ChildHolder holder = (ChildHolder) view.getTag();
        if (null == holder) {
            holder = new ChildHolder(view);
            view.setTag(holder);
        }
        return holder;
    }
}
