package com.example.parthinterntask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parthinterntask.Activity.Static_Recycler;
import com.example.parthinterntask.Common.Helper;
import com.example.parthinterntask.Model.finishedMatchModel;
import com.example.parthinterntask.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class finishedRecyclerViewAdapter extends RecyclerView.Adapter {
    Context context;
    List<finishedMatchModel> finishedMatchModelList;

    public finishedRecyclerViewAdapter(Context context, List<finishedMatchModel> finishedMatchModelList) {
        this.context = context;
        this.finishedMatchModelList = finishedMatchModelList;
    }

    @Override
    public int getItemViewType(int position) {
        if (finishedMatchModelList.get(position).getItemType() == 0) {
            return 0;
        }
        if (finishedMatchModelList.get(position).getItemType() == 1) {
            return 1;
        }
        return 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.card4, parent, false);
            return new finishedRecyclerViewAdapter.RecyclerViewHolderOne(view);
        }
        if (viewType == 1) {
            view = inflater.inflate(R.layout.card3, parent, false);
            return new finishedRecyclerViewAdapter.RecyclerViewHolderTwo(view);
        }
        if (viewType == 2) {
            view = inflater.inflate(R.layout.finished_matches, parent, false);
            return new finishedRecyclerViewAdapter.RecyclerViewHolderThree(view);
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        finishedMatchModel finishedMatchModel = finishedMatchModelList.get(position);
        if (finishedMatchModel.getItemType() == 0) {
            finishedRecyclerViewAdapter.RecyclerViewHolderOne holderOne = (finishedRecyclerViewAdapter.RecyclerViewHolderOne) holder;
            holderOne.tvTeam1.setText(finishedMatchModel.getTeam1());
            holderOne.tvTeam2.setText(finishedMatchModel.getTeam2());
            holderOne.ivTeam1.setImageURI(finishedMatchModel.getTeamFlag1());
            holderOne.ivTeam2.setImageURI(finishedMatchModel.getTeamFlag2());
            holderOne.tvScore1.setText(Helper.formatScore(finishedMatchModel.getScore1()));
            holderOne.tvScore2.setText(Helper.formatScore(finishedMatchModel.getScore2()));
            holderOne.tvOvers1.setText(finishedMatchModel.getOvers1());
            holderOne.tvOvers2.setText(finishedMatchModel.getOvers2());
            holderOne.tvWin.setText(Helper.extractTeam(finishedMatchModel.getResult()));
            holderOne.tvRun.setText(Helper.extractRun(finishedMatchModel.getResult()));
            holderOne.tvWin.setTextColor(ColorUtils.blendARGB(Color.parseColor("#D6D056"), Color.parseColor("#FFFFFF"), (float) 0.4));
        }

        if (finishedMatchModel.getItemType() == 1) {
            finishedRecyclerViewAdapter.RecyclerViewHolderTwo holderTwo = (finishedRecyclerViewAdapter.RecyclerViewHolderTwo) holder;
            Log.d("Date", finishedMatchModel.getDate());
            holderTwo.tvDate.setText(Helper.formatDateForCard3(finishedMatchModel.getDate()));
        }

        if (finishedMatchModel.getItemType() == 2) {
            finishedRecyclerViewAdapter.RecyclerViewHolderThree holderThree = (finishedRecyclerViewAdapter.RecyclerViewHolderThree) holder;
            holderThree.cvFinished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Open All Finished Matches", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Static_Recycler.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return finishedMatchModelList.size();
    }

    public class RecyclerViewHolderOne extends RecyclerView.ViewHolder {
        TextView tvStadiumName, tvTeam1, tvTeam2, tvScore1, tvScore2, tvOvers1, tvOvers2, tvWin, tvRun;
        SimpleDraweeView ivTeam1, ivTeam2;

        public RecyclerViewHolderOne(@NonNull View itemView) {
            super(itemView);
            tvStadiumName = itemView.findViewById(R.id.tvStadiumName);
            tvTeam1 = itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = itemView.findViewById(R.id.tvTeam2);
            tvScore1 = itemView.findViewById(R.id.tvScoreWicket1);
            tvScore2 = itemView.findViewById(R.id.tvScoreWicket2);
            tvOvers1 = itemView.findViewById(R.id.tvOvers1);
            tvOvers2 = itemView.findViewById(R.id.tvOvers2);
            ivTeam1 = itemView.findViewById(R.id.ivTeam1);
            ivTeam2 = itemView.findViewById(R.id.ivTeam2);
            tvWin = itemView.findViewById(R.id.tvWinTeam);
            tvRun = itemView.findViewById(R.id.tvRun);
        }
    }

    public class RecyclerViewHolderTwo extends RecyclerView.ViewHolder {
        TextView tvDate;

        public RecyclerViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    public class RecyclerViewHolderThree extends RecyclerView.ViewHolder {
        CardView cvFinished;

        public RecyclerViewHolderThree(@NonNull View itemView) {
            super(itemView);
            cvFinished = itemView.findViewById(R.id.cvFinished);
        }
    }
}
