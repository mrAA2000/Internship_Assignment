package com.example.parthinterntask.Common;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.parthinterntask.Model.finishedMatchModel;
import com.example.parthinterntask.Model.upcomingMatchModel;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class Helper {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formatDateForCard3(String date) {
        Log.d("Dater", date);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate currentDate = LocalDate.parse(date, df);
        int day = currentDate.getDayOfMonth();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        Month month = currentDate.getMonth();
        String formatDate = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        formatDate += ", ";
        formatDate += Integer.toString(day);
        formatDate += " ";
        formatDate += month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return formatDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formatDateForCard1(String date) {
        Log.d("Dater", date);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate currentDate = LocalDate.parse(date, df);
        int day = currentDate.getDayOfMonth();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        Month month = currentDate.getMonth();
        String formatDate = Integer.toString(day);
        formatDate += " ";
        formatDate += month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        return formatDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formatDateInMilliToTime(Long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        System.out.println(formatter.format(calendar.getTimeInMillis()));
        String formatTime = formatter.format(calendar.getTimeInMillis());
        return formatTime;
    }

    public static String formatScore(String score) {
        String formatScore = score.substring(0, score.indexOf("/"));
        formatScore += "-";
        formatScore += score.substring(score.indexOf("/") + 1, score.length());
        return formatScore;
    }

    public static String extractTeam(String result) {
        String subString = "by";
        String winTeam = result.substring(0, result.indexOf(subString));
        return winTeam;
    }

    public static String extractRun(String result) {
        String subString = "by";
        String run = result.substring(result.indexOf(subString), result.length());
        return run;
    }

    public static List<upcomingMatchModel> sortOnBasisOfTime(List<upcomingMatchModel> upcomingMatchModelList) {
        TreeMap<Long, upcomingMatchModel> timeList = new TreeMap<>();
        for (int i = 0; i < upcomingMatchModelList.size(); i++) {
            timeList.put(upcomingMatchModelList.get(i).getTvTime(), upcomingMatchModelList.get(i));
        }
        List<upcomingMatchModel> updatedUpcomingMatchModel = new ArrayList<>();
        Date date = new Date();
        long timeMilli = date.getTime();
        for (Map.Entry<Long, upcomingMatchModel> entry : timeList.entrySet()) {
            if (timeMilli < entry.getKey())
                updatedUpcomingMatchModel.add(entry.getValue());
        }
        return updatedUpcomingMatchModel;
    }

    public static List<finishedMatchModel> sortOnBasisOfTime1(List<finishedMatchModel> finishedMatchModelList) {
        TreeMap<Long, finishedMatchModel> timeList = new TreeMap<>();
        for (int i = 0; i < finishedMatchModelList.size(); i++) {
            timeList.put(finishedMatchModelList.get(i).getTime(), finishedMatchModelList.get(i));
        }
        List<finishedMatchModel> updatedFinishedMatchModel = new ArrayList<>();
        Date date = new Date();
        long timeMilli = date.getTime();
        for (Map.Entry<Long, finishedMatchModel> entry : timeList.entrySet()) {
            if (timeMilli > entry.getKey())
                updatedFinishedMatchModel.add(entry.getValue());
        }
        return updatedFinishedMatchModel;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean compareTime(long tvTime) {
        long timeCurrent = Instant.now().toEpochMilli();
        long timeThreeHrsEarlier = Instant.now().plus(3, ChronoUnit.HOURS).toEpochMilli();
        if ((tvTime <= timeThreeHrsEarlier) && (tvTime >= timeCurrent))
            return true;
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static long getDifference(long tvTime) {
        long timeCurrent = Instant.now().toEpochMilli();
        return tvTime - timeCurrent;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getTime(Long tvTime) {
        long timeCurrent = Instant.now().toEpochMilli();
        long diff = tvTime - timeCurrent;
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) - hours * 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff) - hours * 60 * 60 - minutes * 60;
        String formattedTime = String.format("%02dh : %02dm : %02ds", hours, minutes, seconds);
        return formattedTime;
    }

//    TASK BY MAYANK SIR
//    public static void ChangeTime() throws ParseException {
//        String time="2022-06-07T13:20:43.000Z";
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
//        Date gmt = formatter.parse(time);
//        long millisecondsSinceEpoch0 = gmt.getTime();
//        String asString = formatter.format(gmt);
//        Log.i("Epooch Time : ", String.valueOf(millisecondsSinceEpoch0));
//    }

}
