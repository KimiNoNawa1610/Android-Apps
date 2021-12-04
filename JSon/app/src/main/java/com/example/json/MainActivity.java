package com.example.json;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    public static final char DEGREE = '\u00B0';
    public static final String STARTING_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static final String KEY_NAME = "APPID";
    // private String city = "New York, NY";
    private String city = "Chicago,US";
    private String key = "8d17e513af05aa2d9ae69646f4499d5a";
    private EditText cityText ;
    private EditText countryText;
    String json;
    //Do background here
    String baseUrl = STARTING_URL, cityString = city, keyName = KEY_NAME;
    //Create an object RemoteDataReader
    RemoteDataReader rdr = new RemoteDataReader( baseUrl, cityString, keyName, key );
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Get the JSON string
                json=rdr.getData();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TemperatureParser parser = new TemperatureParser( json );
                        Log.w("MainActivity",rdr.getData());
                        Log.w("MainActivity", String.valueOf(parser.getTemperatureK( ))+ DEGREE+"K");
                    }
                });
            }
        });

    }

    public void checkWeather(View v){
        cityText =(EditText) findViewById(R.id.cityinput);
        countryText =(EditText) findViewById(R.id.countryinput);
        String tempcity=cityText.getText().toString();
        String tempcountry=countryText.getText().toString();
        this.city=tempcity+","+tempcountry;
        TextView weathernow=findViewById(R.id.weatherout);
        rdr = new RemoteDataReader( baseUrl, this.city, keyName, key );
        String newjson=rdr.getData();
        TemperatureParser parser = new TemperatureParser( newjson );
        weathernow.setText(this.city+" "+String.valueOf(parser.getTemperatureF())+DEGREE+"F");
    }

}