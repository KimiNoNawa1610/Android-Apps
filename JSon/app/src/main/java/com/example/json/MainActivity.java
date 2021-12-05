package com.example.json;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

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
    private String json;
    //Do background here
    String baseUrl = STARTING_URL, cityString = city, keyName = KEY_NAME;
    private RemoteDataReader rdr;
    //Create an object RemoteDataReader
    //RemoteDataReader rdr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        EditText Editcity=(EditText) findViewById(R.id.cityinput);
        EditText Editcountry=(EditText) findViewById(R.id.countryinput);
        TextView weather= findViewById(R.id.weatherout);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Get the JSON string
                rdr = new RemoteDataReader(baseUrl, city, keyName, key);
                json = rdr.getData();
                handler.post(new Runnable() {
                    TemperatureParser parser = new TemperatureParser(json);
                    @Override
                    public void run() {
                        Log.w("MainActivity", rdr.getData());
                        Log.w("MainActivity", String.valueOf(parser.getTemperatureK()) + DEGREE + "K");
                    }
                });
            }
        });
        Editcountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String newcity=Editcity.getText().toString();
                        String newcountry=Editcountry.getText().toString();
                        city=newcity+","+newcountry;
                        rdr = new RemoteDataReader(baseUrl, city, keyName, key);
                        json = rdr.getData();
                        TemperatureParser parser = new TemperatureParser(json);
                        weather.setText(city+" is: "+String.valueOf(parser.getTemperatureF())+DEGREE+"F");
                    }
                });
            }
        });


    }

}