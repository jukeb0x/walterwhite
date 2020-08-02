package fr.mm.walterwhite.utils;

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

        String chosenDate=day + "/" + month + "/" + year;
        return chosenDate;
    }
}
