package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Hangman game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (game == null) {
            game = new Hangman(Hangman.DEFAULT_GUESSES);
        }
        setContentView(R.layout.activity_main);
        TextView status = (TextView) findViewById(R.id.status);
        status.setText("" + game.getGuessesLeft());
        if (getSupportFragmentManager().findFragmentById(R.id.game_state) == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.game_state, new GameStateFragment(), null)
                    .commit();
        }

        if (getSupportFragmentManager().findFragmentById(R.id.game_result) == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.game_result, new GameResultFragment())
                    .commit();

        }

        if( getSupportFragmentManager().findFragmentByTag( "background" ) == null ) {
            BackgroundFragment fragment = new BackgroundFragment( );
            getSupportFragmentManager().beginTransaction( )
                    .setReorderingAllowed(true)
                    .add( fragment, "background" )
                    .commit();
        }

        if( game.getGuessesLeft( ) == 1 ) {
            BackgroundFragment background = ( BackgroundFragment ) getSupportFragmentManager().findFragmentByTag( "background" );
            GameResultFragment grFragment = ( GameResultFragment ) getSupportFragmentManager().findFragmentById( R.id.game_result );
            grFragment.setResult( background.warning( ) );
        }
    }

    public Hangman getGame ()
    {
        return game;
    }

    public void play(View view) {
        EditText input = (EditText) findViewById(R.id.letter);
        Editable userText = input.getText();
        if (userText != null && userText.length() > 0) {
            // update number of guesses left
            char letter = userText.charAt(0);
            game.guess(letter);
            TextView status = (TextView) findViewById(R.id.status);
            status.setText("" + game.getGuessesLeft( ));
            // update incomplete word
            GameStateFragment gsFragment = (GameStateFragment) getSupportFragmentManager().findFragmentById(R.id.game_state);
            View gsFragmentView = gsFragment.getView( );
            TextView gameStateTV = (TextView) gsFragmentView.findViewById(R.id.state_of_game);
            gameStateTV.setText(game.currentIncompleteWord( ));
            // clear EditText
            input.setText("");
            // check if there is only one guess left
            if( game.getGuessesLeft( ) == 1 ) {
                BackgroundFragment background = ( BackgroundFragment ) getSupportFragmentManager().findFragmentByTag("background");
                GameResultFragment grFragment = ( GameResultFragment ) getSupportFragmentManager().findFragmentById( R.id.game_result );
                // retrieve warning and display it

                grFragment.setResult( background.warning( )  );
            }
            int result = game.gameOver();
            if (result != 0) /* game is over */ {
                GameResultFragment grFragment = (GameResultFragment) getSupportFragmentManager().findFragmentById(R.id.game_result );
                // update TextView in result fragment
                if (result == 1)
                    grFragment.setResult("YOU WON");
                else if (result == -1)
                    grFragment.setResult("YOU LOST");
                // delete hint in EditText
                input.setHint("");
            }
        }
    }


}