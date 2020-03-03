package com.example.zleeper;

import android.util.Log;

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
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

        int timeCutter; //This is the int that says at witch day we should look at.
        int time;
        int dayNumber = 0;
        String dayOfweek = "";




        //Följande tar reda på vilken dag det är så att html koden kan kortas ner enklare utan att man fastnar.

        if (res.equals("Sun")){
            weeknumb ++;

            dayNumber = 7;


        }else if(res.equals("Mon")){

            dayNumber = 1;

        }
        else if(res.equals("Tue")){

            dayNumber = 2;

        }else if(res.equals("Wed")){

            dayNumber = 3;

        }else if(res.equals("Thu")){

            dayNumber = 4;

        }else if(res.equals("Fri")){

            dayNumber = 5;

        }else if(res.equals("Sat")){

            dayNumber = 6;

        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH");
        time = Integer.parseInt(date.format(currentLocalTime));
        //Här bestämms från viken tid man vill börja kolla på, på  nästa dag. just nu så börjar appen kolla på nästa dag vid klockan 15.
        if(time-15>=0) {

            timeCutter =1;

            dayNumber = dayNumber +1;

            if(dayNumber==8) {

                dayNumber = 1;

            }
            Log.e("Time check","Looking at next day");

        }else {

            timeCutter=0;
            Log.e("Time check","Looking at the same day");


        }

                switch (dayNumber) {

                    case 1:
                        dayOfweek = "Må ";
                        break;
                    case 2:
                        dayOfweek = "Ti ";
                        break;
                    case 3:
                        dayOfweek = "On ";
                        break;
                    case 4:
                        dayOfweek = "To ";
                        break;
                    case 5:
                        dayOfweek = "Fr ";
                        break;
                    case 6:
                        dayOfweek = "Lö ";
                        break;
                    case 7:
                        dayOfweek = "Sö ";
                        break;


                }

                Log.e("Dagen att kolla på är: ",dayOfweek);



        for(int i = 1; i <=3; i ++) {  //  If there is something in the schedule but nothing the next day or the day after, we need to try to catch the closest one. Therefore the loop.
            try {
                //+ Integer.toString(weeknumb)
                Log.e("THE TIME IS ", "" + Time(timeCutter));
                Log.e("THE TIME IS TO CUT AT IS ", "" + Time(timeCutter+1));
                //Log.e("INFO IS ",text);
                String week = text.substring(text.indexOf(dayOfweek + Time(timeCutter)), text.indexOf("Fr " + Time(timeCutter + i)));
                //Log.e("THE TEXT IS ", "" + week);
                String day = week.substring(week.indexOf(start) + 1, week.indexOf(end));
                String[] lines = day.split("\\r");


                StringBuilder sb = new StringBuilder();

                for (int x = 0; x < 4; i++) {

                    sb.append(lines[i]);

                }
                sb.append(lines[6]);

                // Log.e("JAAAAA!!!!","    "+day);

                Matcher matcher = pattern2.matcher(sb.toString());
                while (matcher.find()) {
                    Log.e("KOLLA!!", "   " + matcher.group(1));

                    compleat.add(matcher.group(1));
                }

                break;

            } catch (Exception e) {

                //for (int z = 0; z < 7; i++) {

                 //   compleat.add("ERROR");
               // }


                Log.e("FUNKADE EJ", "</3");

            }

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
