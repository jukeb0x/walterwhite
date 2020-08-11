package fr.mm.walterwhite.utils;

import java.time.DayOfWeek;

public class DateUtils {

    public static String formateDate(int year, int monthOfYear, int dayOfMonth){
        String day="";
        if(dayOfMonth<10){
            day+="0"+dayOfMonth;
        }else {
            day+=dayOfMonth;
        }
        String month="";
        int correctMonth=monthOfYear+1;
        if(correctMonth<10){
            month+="0"+correctMonth;
        }else {
            month+=correctMonth;
        }
        return day + "/" + month + "/" + year;
    }

    public static DayOfWeek getDayOfWeekCst(int day){
        switch (day){
            default : return DayOfWeek.MONDAY;
            case 1: return DayOfWeek.MONDAY;
            case 2: return DayOfWeek.TUESDAY;
            case 3: return DayOfWeek.WEDNESDAY;
            case 4: return DayOfWeek.THURSDAY;
            case 5: return DayOfWeek.FRIDAY;
            case 6: return DayOfWeek.SATURDAY;
            case 7: return DayOfWeek.SUNDAY;
        }
    }
}
