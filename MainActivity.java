package com.example.zleeper;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Display display = new Display(this);
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
        display.addView(pic);
        display.addView(text);
        display.addView(info);
        display.addView(info1);
        display.addView(knapp);



        final OkHttpClient client = new OkHttpClient();
        String URL = "https://cloud.timeedit.net/liu/web/schema/ri1Y7X8QQ6fZ66Qv7Q09o785yYY05ZQcZ9f57.html";
        final Request request = new Request.Builder()
                .url(URL)
                .build();

        setContentView(display);
        knapp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);

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
                                    String temp="";

                                    Log.e("SIZE B4","THIS IS THE SIZE : "+length);
                                    Log.e("CONTENT: ",""+strings);

                                        if(sizeCheck(length)>0) {
                                            StringBuilder sb= new StringBuilder();
                                            for(int i = 0; i <=sizeCheck(length); i++){

                                                temp = strings.get(i);

                                                    sb.append(temp).append(",");



                                            }
                                         //   Log.e("SIZE","SIZE  "+length+"   String temp: "+sb);
                                            for(int r = 0; r < sizeCheck(length); r++){

                                                strings.remove(r);


                                            }
                                            temp = sb.toString();
                                            strings.set(0,temp);

                                            length = strings.size();

                                        }


                                    strings = check.BasGrupp(strings);

                                    Log.e("INFO","INFO  "+strings);
                                text.setText(strings.get(length-1-1)+ System.lineSeparator()+ System.lineSeparator() +"Du börjar: "+strings.get(length-1)+ System.lineSeparator());
                                text.setTextSize(40);
                                info.setText("Information: ");
                                info.setTextSize(30);
                                info1.setText("Kurs:    "+strings.get(0)+ System.lineSeparator() +"Plats:   "+strings.get(1)+ System.lineSeparator() +"Vad:     "+strings.get(2));
                                info1.setTextSize(20);





                                }

                            });
                        }
                    }

                });
            }
        });



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
