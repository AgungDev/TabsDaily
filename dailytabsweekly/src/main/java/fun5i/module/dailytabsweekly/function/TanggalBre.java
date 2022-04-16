package fun5i.module.dailytabsweekly.function;

import android.content.Context;
import android.icu.util.LocaleData;
import android.os.Build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class TanggalBre {

    public static final int SATU_DETIK = 1000;

    private ZoneId defaultZoneId;
    private Date date;
    private LocaleData localeData;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;

    public String thisDay;

    public static final String[] HARI_DALAM_MINGGU = {
            "Minggu",
            "Senin",
            "Selasa",
            "Rabu",
            "Kamis",
            "Jumaat",
            "Sabtu"
    };




    public TanggalBre(){}
    public TanggalBre(Context c){
        calendar = Calendar.getInstance();

        // SetCurrentDayInActivity
        SimpleDateFormat formatter= new SimpleDateFormat("dd");
        Date datecr = new Date(System.currentTimeMillis());
        this.thisDay = formatter.format(datecr);
    }

    public String getHHMMSS(){
        calendar.setTimeInMillis(System.currentTimeMillis());
        Date dates = calendar.getTime();
        int mHour = dates.getHours();
        int mMinute = dates.getMinutes();
        int mSecond = dates.getSeconds();

        return String.format("%02d:%02d:%02d", mHour, mMinute, mSecond);
    }



    public int getCurrentYear(){
        Date d = new Date();
        calendar.setTime(d);
        int y = calendar.get(Calendar.YEAR);
        return y;
    }

    public int getCurrentYear(Date d){
        calendar.setTime(d);
        int y = calendar.get(Calendar.YEAR);
        return y;
    }

    public int getCurrentMonth(){
        Date d = new Date();
        calendar.setTime(d);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }

    public int getCurrentMonth(Date d){
        calendar.setTime(d);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }


    public int getTanggalFromDate(Date d){
        calendar.setTime(d);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public int getTanggalFromDate(){
        Date d = new Date();
        calendar.setTime(d);
        int tgl = calendar.get(Calendar.DAY_OF_MONTH);
        return tgl;
    }


    public Date[] getWeek(){
        calendar = Calendar.getInstance();
        Date[] week = new Date[7];
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//start date
        for (int i=0; i < 7; i++) {
            week[i] = calendar.getTime(); //set start Sunday
            calendar.add(Calendar.DATE, 1);//incremnt
        }
        return week;
    }

    public Date backwardDate(){
        return date;
    }

}

