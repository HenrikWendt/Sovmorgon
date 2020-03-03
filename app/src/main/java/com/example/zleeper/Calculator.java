package com.example.zleeper;

import android.util.Log;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class Calculator {


    public ArrayList<String> Calculate (String str){
        String temp;
        temp = str;



        String EndString;
        ArrayList<String> EndInfo = new ArrayList<String>();

        Info info = new Info();
        SizeFix Size = new SizeFix();

        int l1;
        int l2;
        int l3;
        int l4;

        //Detta är ingen toppenlösning egentligen... Om det kommer något i schemat som inte är tiderna under så kommer den att säga fel...
        String one = "10:00</td>";
        String two = "12:00</td>";
        String three = "15:00</td>";
        String four = "17:00</td>";
        ArrayList<String> strings = new ArrayList<String>(); //Arraylist of all the ending segments to cut too. Afterwards its set with the cutted segment.
        strings.add(one);
        strings.add(two);
        strings.add(three);
        strings.add(four);
        ArrayList<Integer> numbs = new ArrayList<Integer>(); // Arraylist of the lengths of all the differnet "cuts"
        numbs.add(0);
        numbs.add(0);
        numbs.add(0);
        numbs.add(0);





        int timeCutter; //This is the int that says at witch day we should look at.
        int time;


        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH");
        time = Integer.parseInt(date.format(currentLocalTime));
       if(time-15>=0) {

            timeCutter =1;


        }else {

            timeCutter=0;

        }


        Log.e("DAGEN SOM DEN KOLLAR PÅ ÄR.........",Day() + "Timecutter is: "+timeCutter);


        if(temp.contains(Day()+(Time(1)))) {


            for (int i = 0; i < strings.size(); i++) {
                try {



                    String requiredString = temp.substring(temp.indexOf(Time(timeCutter)) + 1, temp.indexOf(strings.get(i)));
                    int temp1;
                    temp1 = requiredString.length();
                    numbs.set(i, temp1);
                    strings.set(i, requiredString);
                } catch (Exception e) {
                    numbs.set(i, 999999999);
                }
            }
                l1 = numbs.get(0);
                l2 = numbs.get(1);
                l3 = numbs.get(2);
                l4 = numbs.get(3);

            Collections.sort(numbs);

             EndString = "";

        }else {

            EndString = "";

            l1=999999999;
            l2=999999999;
            l3=999999999;
            l4=999999999;

            numbs.set(0,999999999);


            Log.e("Schemat är tomt för imorogn","Ska inte göra något mer");

        }

        if(numbs.get(0)==999999999){

           // for (int i = 0; i < 7; i++) {

             //   EndInfo.add("ERROR");
           // }

           // EndInfo.add("Något gick fel");
            //EndInfo.add("ERROR");
            EndString = "Ja det har du, du är ledig imorgon!";
            EndInfo.add("SOVA");
            EndInfo.add("HEMMA");
            EndInfo.add("TDDC50VA");
            EndInfo.add(EndString);
            EndInfo.add("");
            EndInfo.add("0");




        }else if(numbs.get(0)>120000) {

            EndString = "Ja det har du, du är ledig imorgon!"; 
            EndInfo.add("SOVA");
            EndInfo.add("HEMMA");
            EndInfo.add("TDDC50VA");
            EndInfo.add(EndString);
            EndInfo.add("");
            EndInfo.add("0");

        }else if(numbs.get(0) == l1) {
            EndString="Du har ingen sovmorgon...";

           // Log.e("INFO","LOOK HERE "+strings.get(1));

           EndInfo = info.Info(temp,">08:15 - 10:00</td>","        </tr>");
           EndInfo.add(EndString);
           EndInfo.add("08:15");

           EndInfo = Size.SizeFix(EndInfo);
           EndInfo.add(EndInfo.size(),"7");

           Log.e("EndINFO ","LOOK  "+EndInfo);

        }else if (numbs.get(0) == l2){
            EndString="Ja du har sovmorgon!";


            //Log.e("Current stirng is ",temp);

            EndInfo = info.Info(temp,">10:15 - 12:00</td>","        </tr>");
            EndInfo.add(EndString);
            EndInfo.add("10:15");

           EndInfo = Size.SizeFix(EndInfo);
            EndInfo.add(EndInfo.size(),"9");


        }else if(numbs.get(0) == l3){
            EndString="Ja du har sovmorgon!";

            EndInfo = info.Info(temp,">13:15 - 15:00</td>","        </tr>");
            EndInfo.add(EndString);
            EndInfo.add("13:15");

            EndInfo = Size.SizeFix(EndInfo);
            EndInfo.add(EndInfo.size(),"12");

        }else if(numbs.get(0) == l4) {
            EndString="Ja du har sovmorgon!";

            EndInfo = info.Info(temp,">15:15 - 17:00</td>","        </tr>");
            EndInfo.add(EndString);
            EndInfo.add("15:15");

            EndInfo = Size.SizeFix(EndInfo);
            //ändrade från set till add på alla dessa kan göra kaoz
            EndInfo.add(EndInfo.size(),"14");



        }

        Log.e("TESTAR","TESTAR "+numbs.get(0)+"----"+numbs.get(1)+"----"+numbs.get(2)+"----"+numbs.get(3)+"----");
        //Log.e("INFO","LOOK HERE "+strings.get(0));




        return EndInfo;
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

    public String Day() {

        Format dateFormat = new SimpleDateFormat("EEE");
        String res = dateFormat.format(new Date());
        int dayNumber = 0;
        String dayOfweek = "";



        if (res.equals("Sun")){

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
        //This is the int that says at witch day we should look at.
        int time;

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH");
        time = Integer.parseInt(date.format(currentLocalTime));
        //Här bestämms från viken tid man vill börja kolla på, på  nästa dag. just nu så börjar appen kolla på nästa dag vid klockan 15.

        if(time-15>=0) {



            dayNumber = dayNumber +1;

            if(dayNumber==8) {

                dayNumber = 1;

            }
            Log.e("Time check","Looking at next day");

        }else {


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



        return dayOfweek;
    }


}
