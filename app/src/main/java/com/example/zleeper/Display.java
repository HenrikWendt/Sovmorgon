package com.example.zleeper;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Display extends LinearLayout{



    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);



    public Display(Context context) {
        super(context);
        init();

    }

    public void init() {

        setOrientation(VERTICAL);
        setLayoutParams(params);
        setBackgroundColor(Color.parseColor("#2196F3"));
    }
}

