package com.example.candystore;

import android.content.Context;

public class CandyButton extends androidx.appcompat.widget.AppCompatButton {
    private Candy candy;
    public CandyButton(Context context, Candy newCandy ) {
        super( context );
        candy = newCandy;
    }
    public double getPrice( ) {
        return candy.getPrice( );
    }
}

