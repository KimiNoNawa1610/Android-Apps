package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
public class MainActivity extends AppCompatActivity {
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance( );
            SAXParser saxParser = factory.newSAXParser( );
            SAXHandler handler = new SAXHandler( );
            InputStream is = getResources( ).openRawResource( R.raw.test );
            saxParser.parse( is, handler );
            ArrayList<Item> items = handler.getItems();
            for( Item item:items )
                Log.w( "MainActivity", item.toString() );
        }
        catch ( Exception e ) {
            Log.w( "MainActivity", "e = " + e.toString( ) );
        }
    }
}
