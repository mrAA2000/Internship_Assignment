package com.example.parthinterntask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parthinterntask.Common.Helper;
import com.example.parthinterntask.Model.finishedMatchModel;
import com.example.parthinterntask.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class CustomizedExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableTitleList;
    private HashMap<String,List<finishedMatchModel>> expandableDetailList;

    public CustomizedExpandableListAdapter(Context context, List<String> expandableTitleList, HashMap<String,List<finishedMatchModel>> expandableDetailList) {
        this.context = context;
        this.expandableTitleList = expandableTitleList;
        this.expandableDetailList = expandableDetailList;
    }

    @Override
    public int getGroupCount() {
        return expandableTitleList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandableDetailList.get(expandableTitleList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expandableTitleList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expandableDetailList.get(expandableTitleList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String date = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.card5, null);
        }
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        tvDate.setText(date);

        if (isExpanded) {
            ImageView imageView=convertView.findViewById(R.id.ivExpand);
            imageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        } else {
            ImageView imageView=convertView.findViewById(R.id.ivExpand);
            imageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        finishedMatchModel finishedMatchModel= (finishedMatchModel) getChild(groupPosition,childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.card4, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.tvWinTeam);
        SimpleDraweeView ivTeam1=convertView.findViewById(R.id.ivTeam1);
        SimpleDraweeView ivTeam2=convertView.findViewById(R.id.ivTeam2);
        TextView tvTeam1=convertView.findViewById(R.id.tvTeam1);
        TextView tvTeam2=convertView.findViewById(R.id.tvTeam2);
        TextView tvScore1=convertView.findViewById(R.id.tvScoreWicket1);
        TextView tvScore2=convertView.findViewById(R.id.tvScoreWicket2);
        TextView tvOvers1=convertView.findViewById(R.id.tvOvers1);
        TextView tvOvers2=convertView.findViewById(R.id.tvOvers2);
        TextView tvRun=convertView.findViewById(R.id.tvRun);
        expandedListTextView.setText(finishedMatchModel.getWinner());
        ivTeam1.setImageURI(finishedMatchModel.getTeamFlag1());
        ivTeam2.setImageURI(finishedMatchModel.getTeamFlag2());
        tvTeam1.setText(finishedMatchModel.getTeam1());
        tvTeam2.setText(finishedMatchModel.getTeam2());
        tvScore1.setText(Helper.formatScore(finishedMatchModel.getScore1()));
        tvScore2.setText(Helper.formatScore(finishedMatchModel.getScore2()));
        tvOvers1.setText(finishedMatchModel.getOvers1());
        tvOvers2.setText(finishedMatchModel.getOvers2());
        tvRun.setText(Helper.extractRun(finishedMatchModel.getResult()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
