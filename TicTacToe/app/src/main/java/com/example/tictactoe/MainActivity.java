package com.example.tictactoe;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TicTacToe tttGame=new TicTacToe();
    private Button [][] buttons;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        // setContentView( R.layout.activity_main );
        buildGuiByCode( );
    }

    public void buildGuiByCode( ) {
        // Get width of the screen
        Point size = new Point( );
        //YOUR CODE – Retrieve the width of the screen
        getWindowManager( ).getDefaultDisplay().getSize( size );
        //YOUR CODE – Assign one third of the width of the screen to a variable w
        int w = size.x / TicTacToe.SIDE;
        // Create the layout manager as a GridLayout
        GridLayout gridLayout = new GridLayout( this );
        gridLayout.setColumnCount( TicTacToe.SIDE );
        gridLayout.setRowCount( TicTacToe.SIDE );
        // Create the buttons and add them to gridLayout
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        //Instantiate a ButtonHandler object
        ButtonHandler bh= new ButtonHandler();
        for( int row = 0; row < TicTacToe.SIDE; row++ ) {
            for( int col = 0; col < TicTacToe.SIDE; col++ ) {
                buttons[row][col] = new Button( this );
                //Set the textsize for each button to w * 0.2
                buttons[row][col].setTextSize((float) (w * 0.2));
                        //Register the event for each button
                buttons[row][col].setOnClickListener( bh);
                gridLayout.addView(buttons[row][col],w,w);
            }
        }
        // Set gridLayout as the View of this Activity
        setContentView(gridLayout);
    }

    public void update( int row, int col ) {

        int play = tttGame.play( row, col );

        if( play == 1 ) {
            buttons[row][col].setText("X");
        }
        else if( play == 2 ) {
            buttons[row][col].setText("O");
        }
        if( tttGame.isGameOver( ) ) { // game over
            enableButtons(false);
        }

    }

    public void enableButtons( boolean enabled ) {
        for( int row = 0; row < TicTacToe.SIDE; row++ )
            for( int col = 0; col < TicTacToe.SIDE; col++ )
                buttons[row][col].setEnabled( enabled );
    }

    //Implement the ButtonHandler event
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            Log.w( "MainActivity", "Inside onClick, v = " + v );
            for( int row = 0; row < TicTacToe.SIDE; row++ ) {
                for (int column = 0; column < TicTacToe.SIDE; column++) {
                    if (v == buttons[row][column]) {
                        update(row, column);
                    }
                }
            }
        }
    }
}



