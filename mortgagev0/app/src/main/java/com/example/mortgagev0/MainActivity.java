package com.example.mortgagev0;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static Mortgage mortgage;
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        mortgage = new Mortgage( );
        setContentView( R.layout.activity_main );
    }

    public void onStart( ) {
        super.onStart( );
        updateView( );
    }

    public void updateView( ) {
        TextView amountTV = ( TextView ) findViewById( R.id.amount );
        amountTV.setText( mortgage.getFormattedAmount( ) );

        TextView yearsTV = ( TextView ) findViewById( R.id.years );
        yearsTV.setText( "" + mortgage.getYears( ) );

        TextView interestTV= ( TextView ) findViewById(R.id.rate);
        interestTV.setText(""+ mortgage.getRate());

        TextView monthlyTV=(TextView) findViewById(R.id.monthly_payment);
        monthlyTV.setText(""+mortgage.monthlyPayment());

        TextView totalTV=(TextView) findViewById(R.id.total_payment);
        totalTV.setText(""+mortgage.totalPayment());

    }

    public void modifyData( View v ) {
        Intent myIntent = new Intent( this, DataActivity.class );
        this.startActivity( myIntent );
    }
}


