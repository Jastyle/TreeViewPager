package com.jastyle.treeviewpager.treeView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jastyle.treeviewpager.R;

import java.util.List;



public class TreeViewAdapter extends BaseTreeViewAdapter {
    private LayoutInflater mInflater;
    List<List<LogisticsData>> logisticsDatas;
    public TreeViewAdapter(Context context, TreeView treeView, List<List<LogisticsData>> logisticsDatas) {
        super(treeView);
        mInflater = LayoutInflater.from(context);
        this.logisticsDatas = logisticsDatas;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return logisticsDatas.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return logisticsDatas.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return logisticsDatas.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
        convertView = mInflater.inflate(R.layout.tree_list_item_vieew, null);//复用会导致数据混乱
        }
        ChildHolder holder = getChildHolder(convertView);
        holder.nodeProgressView.setNodeProgressAdapter(new NodeProgressAdapter() {
            @Override
            public int getCount() {
                return logisticsDatas.get(groupPosition).size();
            }

            @Override
            public List<LogisticsData> getData() {
                return logisticsDatas.get(groupPosition);
            }
        });
        return convertView;
    }

    private ChildHolder getChildHolder(final View view) {
        ChildHolder  holder = new ChildHolder(view);
//        }
        return holder;
    }

    private class ChildHolder {
        NodeProgressView nodeProgressView;
        public ChildHolder(View view) {
            nodeProgressView = (NodeProgressView) view.findViewById(R.id.mynodepv);
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_group_view, null);
        }
//        GroupHolder holder = getGroupHolder(convertView);
        /*holder.name.setText(firstClassBean.getSecond_class().get(groupPosition).getName());
        if (isExpanded) {
            holder.expand_indicator.setImageResource(R.mipmap.indicator_expanded);
        } else {
            holder.expand_indicator.setImageResource(R.mipmap.indicator_unexpanded);
        }
        String rank = firstClassBean.getSecond_class().get(groupPosition).getRank();
        if (ReportActivity.LOW.equals(rank)) {
            holder.group_indicator.setImageResource(R.mipmap.credit_second_indicator_low);
        }else if (ReportActivity.MID.equals(rank)) {
            holder.group_indicator.setImageResource(R.mipmap.credit_second_indicator_mid);
        }else if (ReportActivity.HIGH.equals(rank)) {
            holder.group_indicator.setImageResource(R.mipmap.credit_second_indicator_high);
        }*/

        return convertView;
    }

    private GroupHolder getGroupHolder(final View view) {
        GroupHolder holder = (GroupHolder) view.getTag();
        if (null == holder) {
            holder = new GroupHolder(view);
            view.setTag(holder);
        }
        return holder;
    }

    private class GroupHolder {
        TextView name;
        ImageView expand_indicator;
        ImageView group_indicator;

        public GroupHolder(View view) {
            name = (TextView) view.findViewById(R.id.group_name);
            expand_indicator = (ImageView) view.findViewById(R.id.expand_indicator);
            group_indicator = (ImageView) view.findViewById(R.id.group_indicator);
        }
    }

    @Override
    public void updateHeader(View header, int groupPosition, int childPosition, int alpha) {
//        ((TextView) header.findViewById(R.id.group_name)).setText(mGroups.get(groupPosition));
        /*((TextView) header.findViewById(R.id.online_count)).setText(getChildrenCount
                (groupPosition) + "/" + getChildrenCount(groupPosition));*/
//        header.setAlpha(alpha);
    }

    @Override
    public int getHeaderClickStatus(int groupPosition) {
        return super.getHeaderClickStatus(groupPosition);
    }
}
