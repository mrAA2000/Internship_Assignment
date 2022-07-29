package com.example.parthinterntask.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parthinterntask.Common.Helper;
import com.example.parthinterntask.Model.upcomingMatchModel;
import com.example.parthinterntask.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class upcomingRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<upcomingMatchModel> upcomingMatchModelList;
    private CountDownTimer countDownTimer;

    public upcomingRecyclerViewAdapter(Context context, List<upcomingMatchModel> upcomingMatchModelList) {
        this.context = context;
        this.upcomingMatchModelList = upcomingMatchModelList;
    }

    @Override
    public int getItemViewType(int position) {
        if (upcomingMatchModelList.get(position).getItemType() == 0) {
            return 0;
        }
        if (upcomingMatchModelList.get(position).getItemType() == 1) {
            return 1;
        }
        if (upcomingMatchModelList.get(position).getItemType() == 2) {
            return 2;
        }
        if (upcomingMatchModelList.get(position).getItemType() == 4) {
            return 4;
        }
        return 3;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.card2, parent, false);
            return new RecyclerViewHolderOne_One(view);
        }
        if (viewType == 1) {
            view = inflater.inflate(R.layout.card3, parent, false);
            return new RecyclerViewHolderTwo(view);
        }
        if (viewType == 2) {
            view = inflater.inflate(R.layout.card1, parent, false);
            return new RecyclerViewHolderOne(view);
        }
        if (viewType == 3) {
            view = inflater.inflate(R.layout.upcoming_matches, parent, false);
            return new RecyclerViewHolderThree(view);
        }
        if (viewType == 4) {
            view = inflater.inflate(R.layout.card4, parent, false);
            return new RecyclerViewHolderFour(view);
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        upcomingMatchModel upcomingMatchModel = upcomingMatchModelList.get(position);
        if (upcomingMatchModelList.get(position).getItemType() == 0) {
            RecyclerViewHolderOne_One holderOne = (RecyclerViewHolderOne_One) holder;
            holderOne.tvTeam1.setText(upcomingMatchModel.getTvTeam1());
            holderOne.tvTeam2.setText(upcomingMatchModel.getTvTeam2());
            holderOne.ivTeam1.setImageURI(upcomingMatchModel.getIvTeam1());
            holderOne.ivTeam2.setImageURI(upcomingMatchModel.getIvTeam2());
            holderOne.tvRateTeam.setText(upcomingMatchModel.getTvRateTeam());
            holderOne.tvRate1.setText(upcomingMatchModel.getTvRate1());
            holderOne.tvRate2.setText(upcomingMatchModel.getTvRate2());
            if (Helper.compareTime(upcomingMatchModel.getTvTime())) {
//                Log.d("Check", "Less Than 3 hrs");
                holderOne.tvTime.setText("Starting in:");
                holderOne.tvDay.setTextColor(Color.parseColor("#EAAE54"));
                holderOne.tvDay.setText(Helper.getTime(upcomingMatchModel.getTvTime()));
            } else {
//                Log.d("Check", "Greater Than 3 hrs");
                holderOne.tvDay.setText(Helper.formatDateForCard1(upcomingMatchModel.getTvDate()));
                holderOne.tvTime.setText(Helper.formatDateInMilliToTime(upcomingMatchModel.getTvTime()));
            }
        }

        if (upcomingMatchModelList.get(position).getItemType() == 1) {
            RecyclerViewHolderTwo holderTwo = (RecyclerViewHolderTwo) holder;
            Log.d("Date", upcomingMatchModel.getTvDate());
            holderTwo.tvDate.setText(Helper.formatDateForCard3(upcomingMatchModel.getTvDate()));
        }

        if (upcomingMatchModelList.get(position).getItemType() == 2) {
            RecyclerViewHolderOne holderOne = (RecyclerViewHolderOne) holder;
            holderOne.tvTeam1.setText(upcomingMatchModel.getTvTeam1());
            holderOne.tvTeam2.setText(upcomingMatchModel.getTvTeam2());
            holderOne.ivTeam1.setImageURI(upcomingMatchModel.getIvTeam1());
            holderOne.ivTeam2.setImageURI(upcomingMatchModel.getIvTeam2());
            holderOne.llRate.setVisibility(View.GONE);
            if (Helper.compareTime(upcomingMatchModel.getTvTime())) {
//                Log.d("Check", "Less Than 3 hrs");
                holderOne.tvTime.setText("Starting in:");
                holderOne.tvDay.setTextColor(Color.parseColor("#EAAE54"));
                holderOne.tvDay.setText(Helper.getTime(upcomingMatchModel.getTvTime()));
            } else {
//                Log.d("Check", "Greater Than 3 hrs");
                holderOne.tvDay.setText(Helper.formatDateForCard1(upcomingMatchModel.getTvDate()));
                holderOne.tvTime.setText(Helper.formatDateInMilliToTime(upcomingMatchModel.getTvTime()));
            }
        }

        if (upcomingMatchModelList.get(position).getItemType() == 3) {
            RecyclerViewHolderThree holderThree = (RecyclerViewHolderThree) holder;
            holderThree.cvUpcoming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Open All Upcoming Matches", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (upcomingMatchModelList.get(position).getItemType() == 4) {
            RecyclerViewHolderFour holderFour = (RecyclerViewHolderFour) holder;
            holderFour.tvTeam1.setText(upcomingMatchModel.getTvTeam1());
            holderFour.tvTeam2.setText(upcomingMatchModel.getTvTeam2());
            holderFour.ivTeam1.setImageURI(upcomingMatchModel.getIvTeam1());
            holderFour.ivTeam2.setImageURI(upcomingMatchModel.getIvTeam2());
            holderFour.tvScore1.setText(Helper.formatScore(upcomingMatchModel.getTvScore1()));
            holderFour.tvScore2.setText(Helper.formatScore(upcomingMatchModel.getTvScore2()));
            holderFour.tvOvers1.setText(upcomingMatchModel.getTvOvers1());
            holderFour.tvOvers2.setText(upcomingMatchModel.getTvOvers2());
            holderFour.tvWin.setText(Helper.extractTeam(upcomingMatchModel.getTvResult()));
            holderFour.tvRun.setText(Helper.extractRun(upcomingMatchModel.getTvResult()));
            holderFour.tvWin.setTextColor(ColorUtils.blendARGB(Color.parseColor("#D6D056"), Color.parseColor("#FFFFFF"), (float) 0.4));
        }
    }

    @Override
    public int getItemCount() {
        return upcomingMatchModelList.size();
    }

    public class RecyclerViewHolderOne extends RecyclerView.ViewHolder {
        TextView tvStadiumName, tvTeam1, tvTeam2, tvTime, tvDay, tvRateTeam, tvRate1, tvRate2;
        SimpleDraweeView ivTeam1, ivTeam2;
        LinearLayout llRate;

        public RecyclerViewHolderOne(@NonNull View itemView) {
            super(itemView);
            tvStadiumName = itemView.findViewById(R.id.tvStadiumName);
            tvTeam1 = itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = itemView.findViewById(R.id.tvTeam2);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvRateTeam = itemView.findViewById(R.id.tvRateTeam);
            tvRate1 = itemView.findViewById(R.id.tvRate1);
            tvRate2 = itemView.findViewById(R.id.tvRate2);
            ivTeam1 = itemView.findViewById(R.id.ivTeam1);
            ivTeam2 = itemView.findViewById(R.id.ivTeam2);
            llRate = itemView.findViewById(R.id.llRate);
        }
    }

    public class RecyclerViewHolderOne_One extends RecyclerView.ViewHolder {
        TextView tvStadiumName, tvTeam1, tvTeam2, tvTime, tvDay, tvRateTeam, tvRate1, tvRate2;
        SimpleDraweeView ivTeam1, ivTeam2;
        LinearLayout llRate;

        public RecyclerViewHolderOne_One(@NonNull View itemView) {
            super(itemView);
            tvStadiumName = itemView.findViewById(R.id.tvStadiumName);
            tvTeam1 = itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = itemView.findViewById(R.id.tvTeam2);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvRateTeam = itemView.findViewById(R.id.tvRateTeam);
            tvRate1 = itemView.findViewById(R.id.tvRate1);
            tvRate2 = itemView.findViewById(R.id.tvRate2);
            ivTeam1 = itemView.findViewById(R.id.ivTeam1);
            ivTeam2 = itemView.findViewById(R.id.ivTeam2);
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
        CardView cvUpcoming;

        public RecyclerViewHolderThree(@NonNull View itemView) {
            super(itemView);
            cvUpcoming = itemView.findViewById(R.id.cvUpcoming);
        }
    }

    public class RecyclerViewHolderFour extends RecyclerView.ViewHolder {
        TextView tvStadiumName, tvTeam1, tvTeam2, tvScore1, tvScore2, tvOvers1, tvOvers2, tvWin, tvRun;
        SimpleDraweeView ivTeam1, ivTeam2;

        public RecyclerViewHolderFour(@NonNull View itemView) {
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
}
