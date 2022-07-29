package com.example.parthinterntask.Model;

public class finishedMatchModel {
    private String team1,team2,teamFlag1,teamFlag2,score1,score2,overs1,overs2,winner,matchNo,result,date;
    private Long time;
    private int itemType;

    public finishedMatchModel(int itemType,String team1, String team2, String teamFlag1, String teamFlag2, String score1, String score2, String overs1, String overs2, String winner, String matchNo, String result, String date, Long time) {
        this.itemType = itemType;
        this.team1 = team1;
        this.team2 = team2;
        this.teamFlag1 = teamFlag1;
        this.teamFlag2 = teamFlag2;
        this.score1 = score1;
        this.score2 = score2;
        this.overs1 = overs1;
        this.overs2 = overs2;
        this.winner = winner;
        this.matchNo = matchNo;
        this.result = result;
        this.date = date;
        this.time = time;
    }

    public finishedMatchModel(int itemType,String date) {
        this.date = date;
        this.itemType = itemType;
    }

    public finishedMatchModel(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeamFlag1() {
        return teamFlag1;
    }

    public void setTeamFlag1(String teamFlag1) {
        this.teamFlag1 = teamFlag1;
    }

    public String getTeamFlag2() {
        return teamFlag2;
    }

    public void setTeamFlag2(String teamFlag2) {
        this.teamFlag2 = teamFlag2;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getOvers1() {
        return overs1;
    }

    public void setOvers1(String overs1) {
        this.overs1 = overs1;
    }

    public String getOvers2() {
        return overs2;
    }

    public void setOvers2(String overs2) {
        this.overs2 = overs2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(String matchNo) {
        this.matchNo = matchNo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
