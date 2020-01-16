package com.example.zleeper;

import java.util.ArrayList;

public class BasGrupp {


    public ArrayList<String> BasGrupp (ArrayList<String> arrayList) {

        ArrayList<String> info = new ArrayList<String> ();
        ArrayList<String> EndInfo = new ArrayList<String> ();
        info = arrayList;
        String basgrupp ="";
        String EndString = "";


        basgrupp = info.get(3);
       // info.set(4,basgrupp);

        if(basgrupp.equals("Grupp 2")||basgrupp.equals("Grupp 3")||basgrupp.equals("Grupp 1")) {

            EndString="Ja det har du, du är ledig imorgon!";
            EndInfo.add("SOVA");
            EndInfo.add("HEMMA");
            EndInfo.add("TDDC50VA");
            for(int i=0; i<4; i++) {

                EndInfo.add("-");
            }

            EndInfo.add(EndString);
            EndInfo.add(" ");

            return EndInfo;
        }else {



            return arrayList;

        }
    }

}
