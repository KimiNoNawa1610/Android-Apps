package com.example.json;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
public class RemoteDataReader {
    private String urlString;

    public RemoteDataReader( String baseUrl, String cityString, String keyName, String key ) {
         urlString = baseUrl+cityString+"&"+keyName+"="+key;
    }
    public String getData( ) {
        try {
            // Establish the connection
            URL url = new URL( urlString );
            Log.w("MainActivity", urlString);
            HttpURLConnection con = ( HttpURLConnection ) url.openConnection();
            Log.w("MainActivity", "P2");
            con.connect( );
            //con.getResponseCode();
            Log.w("MainActivity", "P3");
            int code = con.getResponseCode();
            if (code != 200) {
                Log.w("MainActivity", String.valueOf(code));
                throw new IOException("Invalid response from server: " + code);
            }

            // Get the input stream and prepare to read
            InputStream is = con.getInputStream( );;
            BufferedReader br = new BufferedReader( new InputStreamReader( is ) );
            //Log.w("MainActivity", "P4");

            // Read the data
            String dataRead = new String( );
            String line = br.readLine( );
            while ( line != null ) {
                dataRead += line;
                line = br.readLine( );
            }

            is.close( );
            con.disconnect( );
            //Log.w("MainActivity",dataRead);
            return dataRead;
        } catch( Exception e ) {
            return "";
        }
    }
}

