package com.example.zleeper;

import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
public class Calculator {


    public ArrayList<String> Calculate (String str){
        String temp;
        temp = str;


        String one = "10:00</td>";
        String two = "12:00</td>";
        String three = "15:00</td>";
        String foure = "17:00</td>";
        ArrayList<String> strings = new ArrayList<String>(); //Arraylist of all the ending segments to cut too. Afterwards its set with the cutted segment.
        strings.add(one);
        strings.add(two);
        strings.add(three);
        strings.add(foure);
        ArrayList<Integer> numbs = new ArrayList<Integer>(); // Arraylist of the lengths of all the differnet "cuts"
        numbs.add(0);
        numbs.add(0);
        numbs.add(0);
        numbs.add(0);


        for(int i = 0; i < strings.size(); i++) {
            try{

                String requiredString = temp.substring(temp.indexOf(Time(1)) + 1, temp.indexOf(strings.get(i)));
                int temp1;
                temp1 = requiredString.length();
                numbs.set(i,temp1);
                strings.set(i,requiredString);
            }catch (Exception e) {
                numbs.set(i,999999999);
            }
        }
        int l1 = numbs.get(0);
        int l2 = numbs.get(1);
        int l3 = numbs.get(2);
        int l4 = numbs.get(3);

        Collections.sort(numbs);

        String EndString = "";
        ArrayList<String> EndInfo = new ArrayList<String>();
        Info info = new Info();
        SizeFix Size = new SizeFix();

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
           EndInfo.set(EndInfo.size(),"7");

           Log.e("EndINFO ","LOOK  "+EndInfo);

        }else if (numbs.get(0) == l2){
            EndString="Ja du har sovmorgon!";

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
            EndInfo.set(EndInfo.size(),"12");

        }else if(numbs.get(0) == l4) {
            EndString="Ja du har sovmorgon!";

            EndInfo = info.Info(temp,">15:15 - 17:00</td>","        </tr>");
            EndInfo.add(EndString);
            EndInfo.add("15:15");

            EndInfo = Size.SizeFix(EndInfo);
            EndInfo.set(EndInfo.size(),"14");



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
}
