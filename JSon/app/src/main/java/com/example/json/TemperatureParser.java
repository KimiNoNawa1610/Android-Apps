package com.example.json;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class TemperatureParser {
    public static final double ZERO_K = -273.15;
    private final String MAIN_KEY = "main";
    private final String TEMPERATURE_KEY = "temp";
    private JSONObject jsonObject;
    public TemperatureParser( String json ) {
        try {
            jsonObject = new JSONObject(json);
        } catch( JSONException jsonException ) {
        }
    }
    public double getTemperatureK( ) {
        try {
            JSONObject jsonMain = jsonObject.getJSONObject("main" );
            return jsonMain.getDouble( "temp" );
        } catch( Exception jsonException ) {
            return 25 - ZERO_K;
        }
    }
    public int getTemperatureC( ) {
        return ( int ) ( getTemperatureK( ) + ZERO_K + 0.5 );
    }
    public int getTemperatureF( ) {
        //Log.w("MainActivity",String.valueOf(getTemperatureK( )));
        return ( int ) (( ( getTemperatureK() -273.15) * 9/5) + 32 +0.5);
    }
}
