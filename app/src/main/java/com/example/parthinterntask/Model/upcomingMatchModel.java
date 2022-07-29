package com.example.parthinterntask.Model;

public class upcomingMatchModel {
    private int itemType;
    private String tvTeam1,tvTeam2,ivTeam1,ivTeam2,tvDate,tvRateTeam,tvRate1,tvRate2,tvStadium,tvScore1,tvScore2,tvOvers1,tvOvers2,tvWinner,tvResult;
    private Long tvTime;

    public upcomingMatchModel(int itemType) {
        this.itemType = itemType;
    }

    public upcomingMatchModel(int itemType, String tvDate) {
        this.itemType = itemType;
        this.tvDate = tvDate;
    }

    public upcomingMatchModel(int itemType, String tvTeam1, String tvTeam2, String ivTeam1, String ivTeam2, String tvDate, String tvRateTeam, String tvRate1, String tvRate2, Long tvTime, String tvStadium) {
        this.itemType = itemType;
        this.tvTeam1 = tvTeam1;
        this.tvTeam2 = tvTeam2;
        this.ivTeam1 = ivTeam1;
        this.ivTeam2 = ivTeam2;
        this.tvDate = tvDate;
        this.tvRateTeam = tvRateTeam;
        this.tvRate1 = tvRate1;
        this.tvRate2 = tvRate2;
        this.tvTime = tvTime;
        this.tvStadium = tvStadium;
    }

    public upcomingMatchModel(int itemType, String tvTeam1, String tvTeam2, String ivTeam1, String ivTeam2, String tvDate, Long tvTime, String tvStadium) {
        this.itemType = itemType;
        this.tvTeam1 = tvTeam1;
        this.tvTeam2 = tvTeam2;
        this.ivTeam1 = ivTeam1;
        this.ivTeam2 = ivTeam2;
        this.tvDate = tvDate;
        this.tvTime = tvTime;
        this.tvStadium = tvStadium;
    }

    public upcomingMatchModel(int itemType,String team1, String team2, String teamFlag1, String teamFlag2, String score1, String score2, String overs1, String overs2, String winner, String matchNo, String result, String date, Long time) {
        this.itemType = itemType;
        this.tvTeam1 = team1;
        this.tvTeam2 = team2;
        this.ivTeam1 = teamFlag1;
        this.ivTeam2 = teamFlag2;
        this.tvScore1 = score1;
        this.tvScore2 = score2;
        this.tvOvers1 = overs1;
        this.tvOvers2 = overs2;
        this.tvWinner = winner;
        this.tvStadium = matchNo;
        this.tvResult = result;
        this.tvDate = date;
        this.tvTime = time;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTvTeam1() {
        return tvTeam1;
    }

    public void setTvTeam1(String tvTeam1) {
        this.tvTeam1 = tvTeam1;
    }

    public String getTvTeam2() {
        return tvTeam2;
    }

    public void setTvTeam2(String tvTeam2) {
        this.tvTeam2 = tvTeam2;
    }

    public String getIvTeam1() {
        return ivTeam1;
    }

    public void setIvTeam1(String ivTeam1) {
        this.ivTeam1 = ivTeam1;
    }

    public String getIvTeam2() {
        return ivTeam2;
    }

    public void setIvTeam2(String ivTeam2) {
        this.ivTeam2 = ivTeam2;
    }

    public String getTvDate() {
        return tvDate;
    }

    public void setTvDate(String tvDate) {
        this.tvDate = tvDate;
    }

    public String getTvRateTeam() {
        return tvRateTeam;
    }

    public void setTvRateTeam(String tvRateTeam) {
        this.tvRateTeam = tvRateTeam;
    }

    public String getTvRate1() {
        return tvRate1;
    }

    public void setTvRate1(String tvRate1) {
        this.tvRate1 = tvRate1;
    }

    public String getTvRate2() {
        return tvRate2;
    }

    public void setTvRate2(String tvRate2) {
        this.tvRate2 = tvRate2;
    }

    public Long getTvTime() {
        return tvTime;
    }

    public void setTvTime(Long tvTime) {
        this.tvTime = tvTime;
    }

    public String getTvStadium() {
        return tvStadium;
    }

    public void setTvStadium(String tvStadium) {
        this.tvStadium = tvStadium;
    }

    public String getTvScore1() {
        return tvScore1;
    }

    public void setTvScore1(String tvScore1) {
        this.tvScore1 = tvScore1;
    }

    public String getTvScore2() {
        return tvScore2;
    }

    public void setTvScore2(String tvScore2) {
        this.tvScore2 = tvScore2;
    }

    public String getTvOvers1() {
        return tvOvers1;
    }

    public void setTvOvers1(String tvOvers1) {
        this.tvOvers1 = tvOvers1;
    }

    public String getTvOvers2() {
        return tvOvers2;
    }

    public void setTvOvers2(String tvOvers2) {
        this.tvOvers2 = tvOvers2;
    }

    public String getTvWinner() {
        return tvWinner;
    }

    public void setTvWinner(String tvWinner) {
        this.tvWinner = tvWinner;
    }

    public String getTvResult() {
        return tvResult;
    }

    public void setTvResult(String tvResult) {
        this.tvResult = tvResult;
    }
}