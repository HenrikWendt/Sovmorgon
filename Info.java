package com.example.zleeper;

import android.util.Log;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Info {


    public ArrayList<String> Info (String cont, String st, String en ){

        String text = cont;
        String  start = st;
        String end = en;
        ArrayList<String> compleat = new ArrayList<String>();
        Pattern pattern1 = Pattern.compile("((?:.*\\n){4})");
        Pattern pattern2 = Pattern.compile("(?<=\\>)(.*?)(?=\\<)");        //(?<=">)(.*?)(?=\<),(?<=\>)(.*?)(?=\<) // regex för att lösa problemet med flera lektioner samtidigt.
        String cut ="<td class=\"changeDateLink weekColumn  t\">v ";


        Calendar calendar = Calendar.getInstance();
        int weeknumb = calendar.get(Calendar.WEEK_OF_YEAR);
       // weeknumb ++;
        //Log.e("TEXT IS ",""+text);
        Format dateFormat = new SimpleDateFormat("EEE");
        String res = dateFormat.format(new Date());


        if (res.equals("Sun")){
            weeknumb ++;


        }
        Log.e("STRING TEST ",""+cut+Integer.toString(weeknumb));

        try{
            //+ Integer.toString(weeknumb)
            Log.e("THE TIME IS ",""+Time(1));
            String week = text.substring(text.indexOf(Time(1)), text.indexOf(Time(2)));
            Log.e("THE TEXT IS ",""+week);
            String day = week.substring(week.indexOf(start)+1, week.indexOf(end));
           String[] lines = day.split("\\r");



            StringBuilder sb= new StringBuilder();

            for(int i = 0; i <4; i++) {

                sb.append(lines[i]);

            }
                sb.append(lines[6]);

          // Log.e("JAAAAA!!!!","    "+day);

            Matcher matcher = pattern2.matcher(sb.toString());
            while (matcher.find()) {
                Log.e("KOLLA!!","   "+matcher.group(1));

                compleat.add(matcher.group(1));
            }



        }catch (Exception e) {

            for(int i=0; i<7; i++) {

                compleat.add("ERROR");
            }



            Log.e("FUNKADE EJ","</3");

        }



        Log.e("KOLLA!!","   "+compleat);
        return compleat;
    }


    public String Time(int i) {


        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, i);
        Date tomorrow = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        final String tomorrowAsString = dateFormat.format(tomorrow);

        return tomorrowAsString;
    }


}
