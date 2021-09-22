package com.example.tictactoev2;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ButtonGridAndTextView extends GridLayout {
    private int side;
    private Button[][] buttons;
    private TextView status;

    public ButtonGridAndTextView(Context context, int width, int newSide,
                                 OnClickListener listener) {
        super(context);
        side = newSide;
        // Set # of rows and columns of this GridLayout
        setColumnCount(side);
        setRowCount(side + 1);

        // Create the buttons and add them to this GridLayout
        buttons = new Button[newSide][newSide];
        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                buttons[row][col] = new Button(context);
                buttons[row][col].setTextSize((float) (width* 0.2));
                buttons[row][col].setOnClickListener(listener);
                addView(buttons[row][col], width, width);
            }
        }


        // set up layout parameters of 4th row of gridLayout
        //YOUR CODE
        GridLayout.Spec rowSpec = GridLayout.spec( 3, 1 );
        GridLayout.Spec columnSpec = GridLayout.spec( 0, 3 );
        GridLayout.LayoutParams lp = new GridLayout.LayoutParams(rowSpec, columnSpec );
        status=new TextView(context);
        status.setLayoutParams( lp );
        status.setBackgroundColor( Color.GREEN );
        status.setHeight(width);
        status.setWidth(width*100);
        status.setGravity(Gravity.CENTER);
        status.setTextSize((float) (width * 0.15));
        addView(status);
    }


    public void setStatusText (String text){
        status.setText(text);
    }

    public void setStatusBackgroundColor ( int color ){
        status.setBackgroundColor(color);
    }

    public void setButtonText ( int row, int column, String text){
        buttons[row][column].setText(text);
    }

    public boolean isButton (Button b,int row, int column ){
        return (b == buttons[row][column]);
    }

    public void resetButtons () {
        for (int row = 0; row < side; row++)
            for (int col = 0; col < side; col++)
                buttons[row][col].setText("");
    }

    public void enableButtons ( boolean enabled ){
        for (int row = 0; row < side; row++)
            for (int col = 0; col < side; col++)
                buttons[row][col].setEnabled(enabled);
    }
}

