package com.rig.connect3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    int activePlayer = 0;
    boolean gameIsActive = true;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winingPositions[][] = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,4,8}, {2,4,6}, {0,3,6}, {1,4,7}, {2,5,8}};

    public void dropChip(View view){

        //0 = red, 1 = yellow

        ImageView counter = (ImageView) view;
        int tappedChip = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedChip] == 2 && gameIsActive) {

            gameState[tappedChip] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                gameState[tappedChip] = activePlayer;
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.yellow);
                gameState[tappedChip] = 1;
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for(int[] winningPositions : winingPositions){
                if(gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                        gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]] !=  2){

                    gameIsActive = false;

                    String winner = "Red";
                    if(gameState[winningPositions[0]] == 1){

                        winner = "Yellow";
                    }

                    //player has won
                    TextView winnerTxt = (TextView) findViewById(R.id.winnerMsg);
                    winnerTxt.setText(winner + " has won");
                    linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);


                }
                else{
                    boolean gameIsOver = true;
                    //check if there are still moves to make. Moves != 9
                    for(int counterState : gameState){

                        if(counterState == 2) gameIsOver = false;

                    }
                    if(gameIsOver) {
                        TextView winnerTxt = (TextView) findViewById(R.id.winnerMsg);
                        winnerTxt.setText("Draw Game");
                        linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }

    }

    public void resetBoard(View view){

        gameIsActive = true;
        linearLayout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++){

            //getting the image views from the grid layout
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
