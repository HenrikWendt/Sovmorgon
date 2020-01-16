package com.example.zleeper;


import android.content.Intent;
import android.graphics.Color;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button knapp;
    TextView text;
    TextView info;
    TextView info1;
    ImageView pic;
    Button alarm;
    Display display;
    int test;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        test = 0;
        display = new Display(this);
        pic = new ImageView(this);
        pic.setImageResource(R.drawable.klar);

        knapp = new Button(this);
        knapp.setText("Se svar!");
        knapp.setBackgroundColor(Color.parseColor("#2196F3"));
        knapp.setTextSize(40);
        knapp.setTextColor(Color.WHITE);

        text = new TextView(this);
        text.setTextColor(Color.WHITE);
        info1 = new TextView(this);
        info1.setTextColor(Color.WHITE);
        info = new TextView(this);
        info.setTextColor(Color.WHITE);

        alarm = new Button(this);


        display.addView(pic);
        display.addView(text);
        display.addView(info);
        display.addView(info1);
        display.addView(knapp);




        final OkHttpClient client = new OkHttpClient();
        String URL = "https://cloud.timeedit.net/liu/web/schema/ri1m7XYQ50ZZ5YQvQQ077876y6Y9957.html";
        //  https://cloud.timeedit.net/liu/web/schema/ri1m7XYQ50ZZ8YQvQc07f866y6Y9957Zo7QQ.html
        //https://cloud.timeedit.net/liu/web/schema/ri1m7XYQ50ZZ8YQvQc07f866y6Y9957Zo7QQ.html  ,https://cloud.timeedit.net/liu/web/schema/ri1Y7X8QQ6fZ66Qv7Q09o785yYY05ZQcZ9f57.html

        final Request request = new Request.Builder()
                .url(URL)
                .build();

        setContentView(display);
        knapp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadeout);

                knapp.startAnimation(animation);

                client.newCall(request).enqueue(new Callback() {


                    String myResponse = null;
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()) {

                            final String myResponse = response.body().string();

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    ArrayList<String> strings = new ArrayList<String>();
                                    Calculator calc = new Calculator();
                                    BasGrupp check = new BasGrupp();

                                strings = calc.Calculate(myResponse);


                                    int length = strings.size(); //vill hämta det som är på plats length-1 och length-1-1 ur Arrayen


                                    Log.e("BASGRUPP KOLL ",""+strings.get(4));

                                    strings = check.BasGrupp(strings);

                                    Log.e("INFO","INFO  "+strings);

                                    test = Integer.parseInt(strings.get(strings.size()-1));

                                    setAlarm(test);

                                    Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);

                                text.setText(strings.get(length-1-1-1)+ System.lineSeparator()+ System.lineSeparator() +"Du börjar: "+strings.get(length-2)+ System.lineSeparator());
                                text.setTextSize(40);
                                info.setText("Information: ");
                                info.setTextSize(30);
                                info1.setText("Kurs:    "+strings.get(0)+ System.lineSeparator() +"Plats:   "+strings.get(1)+ System.lineSeparator() +"Vad:     "+strings.get(2));
                                info1.setTextSize(20);
                                text.startAnimation(animation1);
                                info.startAnimation(animation1);
                                info1.startAnimation(animation1);

                                display.removeView(knapp);



                                }

                            });
                        }
                    }

                });
            }
        });



    }
    public void setAlarm (int i) {

        int time;

        time = i;

        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);
        if(i>0) {


            alarm.setBackgroundColor(Color.parseColor("#2196F3"));
            alarm.setTextSize(40);
            alarm.setTextColor(Color.WHITE);
            alarm.setText("Sätt alarm till imorgon");
            display.addView(alarm);
            alarm.startAnimation(animation1);

            alarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation animation2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
                    alarm.startAnimation(animation2);
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, test);
                    intent.putExtra(AlarmClock.EXTRA_MINUTES,0);
                    startActivity(intent);

                }
            });

        }


    }

}
