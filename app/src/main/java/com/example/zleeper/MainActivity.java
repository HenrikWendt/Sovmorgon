package com.example.zleeper;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM;
import static android.widget.RelativeLayout.CENTER_VERTICAL;

public class MainActivity extends AppCompatActivity {

    Button knapp;
    TextView text1;
    TextView text2;
    TextView info;
    TextView info1;
    TextView madeBy;
    TextView madeBy1;
    ImageView pic;
    Button alarm;
    Display display;
    Button mapButton;
    WebView website;
    String sal;
    WebSettings webSettings;
    String URL;
    TextView currentLink;
    ToggleButton toggle1;
    ToggleButton toggle2;
    LinearLayout.LayoutParams params;
    EditText httpString;
    Button save;

    int test;

    private static final String FILE_NAME = "httpString.txt";


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





        mapButton = new Button(this);
        mapButton.setText("Hitta hit!");
        mapButton.setBackgroundColor(Color.parseColor("#2196F3"));
        mapButton.setTextSize(40);
        mapButton.setTextColor(Color.WHITE);

        text1 = new TextView(this);
        text1.setTextColor(Color.WHITE);
        text2 = new TextView(this);
        text2.setTextColor(Color.WHITE);

        info1 = new TextView(this);
        info1.setTextColor(Color.WHITE);
        info = new TextView(this);
        info.setTextColor(Color.WHITE);

        alarm = new Button(this);

        website = new WebView(this);
        webSettings=website.getSettings();
        webSettings.setJavaScriptEnabled(true);


        madeBy = new TextView(this);
        madeBy.setTextColor(Color.WHITE);
        madeBy.setTextSize(20);
        madeBy.setTypeface(null, Typeface.BOLD_ITALIC);
        madeBy.setText("This app was made by:"+"\n"+"Henrik Wendt");
        madeBy1 = new TextView(this);
        madeBy1.setTextSize(127);
        madeBy1.setText("");
        madeBy.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        httpString = new EditText(this);
        httpString.setTextSize(20);
        httpString.setTextColor(Color.WHITE);
        httpString.setHint("klistra in länken till ditt TimeEdit schema här");
        httpString.setVisibility(View.INVISIBLE);


        currentLink = new TextView (this);
        currentLink.setTextSize(20);
        currentLink.setTextColor(Color.WHITE);
        currentLink.setVisibility(View.INVISIBLE);




        save = new Button(this);
        save.setTextSize(20);
        save.setBackgroundColor(Color.parseColor("#2196F3"));
        save.setText("Spara schemat");
        save.setTextColor(Color.WHITE);
        save.setVisibility(View.INVISIBLE);


        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        toggle1 = new ToggleButton(this);
        toggle1.setText("Visa nuvarande schema");
        toggle1.setTextOn("Dölj nuvarande schema");
        toggle1.setTextOff("Visa nuvarande schema");
        toggle1.setTextSize(20);
        toggle1.setBackgroundColor(Color.parseColor("#2196F3"));
        toggle1.setTextColor(Color.WHITE);
        //toggle1.setLayoutParams(params);


        toggle2 = new ToggleButton(this);
        toggle2.setText("Vill du lägga till ett nytt schema?");
        toggle2.setTextOn("Minnimera");
        toggle2.setTextOff("Vill du lägga till ett nytt schema?");
        toggle2.setTextSize(20);
        toggle2.setBackgroundColor(Color.parseColor("#2196F3"));
        toggle2.setTextColor(Color.WHITE);




           //Loading the current link to user
          load();
        

        display.addView(pic);
        display.addView(text1);
         display.addView(text2);
        display.addView(info);
        display.addView(info1);
        display.addView(knapp);
        display.addView(toggle1);
        display.addView(currentLink);
        display.addView(toggle2);
        display.addView(httpString);
        display.addView(save);

        display.addView(madeBy1);
        display.addView(madeBy);




        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {

                    currentLink.setVisibility(View.VISIBLE);

                }else{

                    currentLink.setVisibility(View.INVISIBLE);

                }



            }
        });


        toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {

                    httpString.setVisibility(View.VISIBLE);
                    save.setVisibility(View.VISIBLE);

                }else{

                    httpString.setVisibility(View.INVISIBLE);
                    save.setVisibility(View.INVISIBLE);

                }


            }
        });





        //https://cloud.timeedit.net/liu/web/schema/ri1m7XYQ50ZZ8YQvQc07f866y6Y9957Zo7QQ.html
        //https://cloud.timeedit.net/liu/web/schema/ri1m7XYQ50ZZ8YQvQc07f866y6Y9957Zo7QQ.html  ,https://cloud.timeedit.net/liu/web/schema/ri1Y7X8QQ6fZ66Qv7Q09o785yYY05ZQcZ9f57.html


        setContentView(display);
        knapp.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {


                load();


                try {
                         final OkHttpClient client = new OkHttpClient();
                         final Request request = new Request.Builder()
                            .url(URL)
                            .build();


                Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadeout);

                knapp.startAnimation(animation);
                madeBy.startAnimation(animation);
                //httpString.startAnimation(animation);
                //save.startAnimation(animation);
                //currentLink.startAnimation(animation);
                toggle1.startAnimation(animation);
                toggle2.startAnimation(animation);

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


                                    //Log.e("BASGRUPP KOLL ",""+strings.get(4));

                                   // strings = check.BasGrupp(strings);

                                    Log.e("INFO","INFO  "+strings);

                                    test = Integer.parseInt(strings.get(strings.size()-1));

                                    setAlarm(test);
                                    sal = strings.get(1);
                                    Log.e("SALEN ÄR FÖLJANDE: ", sal);
                                     website.loadUrl("https://old.liu.se/karta?l=sv&px_location="+sal);  
                                    Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);

                                text1.setText(strings.get(length-1-1-1)+ System.lineSeparator());
                                text2.setText("Du börjar: "+strings.get(length-2)+ System.lineSeparator());
                                text1.setTextSize(40);
                                text2.setTextSize(40);
                                info.setText("Information: ");
                                info.setTextSize(30);
                                info1.setText("Undervisningstyp: "+strings.get(2) +System.lineSeparator()+ "Kurs: "+strings.get(0)+ System.lineSeparator() +"Plats: "+strings.get(1)+ System.lineSeparator());
                                info1.setTextSize(20);
                                text1.startAnimation(animation1);
                                text2.startAnimation(animation1);
                                info.startAnimation(animation1);
                                info1.startAnimation(animation1);

                                display.removeView(knapp);
                                display.removeView(madeBy);
                                display.removeView(madeBy1);
                                display.removeView(save);
                                display.removeView(httpString);
                                display.removeView(currentLink);
                                display.removeView(toggle1);
                                display.removeView(toggle2);






                                }

                            });
                        }
                    }

                });


                }catch (IllegalArgumentException i) {
                            Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
                            httpString.setAnimation(animation);
                            knapp.setAnimation(animation);
                            save.setAnimation(animation);
                    Toast.makeText(MainActivity.this, "Felaktig länk, testa igen.", Toast.LENGTH_LONG).show();

                  
                  
                  

                }                                       


            }



        });

      mapButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {




              Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
           // mapButton.startAnimation(animation);

            display.removeView(text1);
            display.removeView(mapButton);
           display.addView(website);

          }
      });


      save.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              save();

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
            alarm.setText("Sätt alarm");
            display.addView(alarm);
            display.addView(mapButton);
            alarm.startAnimation(animation1);
            mapButton.startAnimation(animation1);

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

    public void save() {

        String text = httpString.getText().toString();
        FileOutputStream fos = null;


        try {
            fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
            fos.write(text.getBytes());

            httpString.getText().clear();
           load();
            Toast.makeText(this,"Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos !=null) {

                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load()  {

        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {

                sb.append(text).append("\n");
                

            }




                 URL =(sb.toString());
                 currentLink.setText("Ditt nuvarande schema är: "+sb.toString());



            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {

                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


    }

}
