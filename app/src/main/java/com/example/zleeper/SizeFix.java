package com.example.zleeper;

import android.util.Log;

import java.util.ArrayList;

public class SizeFix {


    public ArrayList<String> SizeFix (ArrayList<String> text) {

        ArrayList<String> strings = new ArrayList<String>();

        strings = text;

        int length = strings.size(); //vill hämta det som är på plats length-1 och length-1-1 ur Arrayen

        String temp;

        Log.e("(SizeFix) SIZE B4", "THIS IS THE SIZE : " + length);
        Log.e("(SizeFix) CONTENT: ", "" + strings);

        if (sizeCheck(length) > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= sizeCheck(length); i++) {

                temp = strings.get(i);

                sb.append(temp).append(",");


            }
            //   Log.e("SIZE","SIZE  "+length+"   String temp: "+sb);
            for (int r = 0; r < sizeCheck(length); r++) {

                strings.remove(r);


            }
            temp = sb.toString();
            strings.set(0, temp);

            length = strings.size();



        }

        Log.e("(SizeFix) CONTENT after : ", "" + strings);

        return strings;
    }

    public Integer sizeCheck (int i) {
        int size   = 0;
        size = i;

        if (size >6) {
            int temp;

            temp  = size - 6;

            return temp;

        }

        return 0;

    }
}