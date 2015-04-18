package com.argumedo.kevin.pig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Player2 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private TextView p1, p2, temp2, rd;
    int p1score = 0, p2score = 0, tempsum = 0, round = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);

        Intent intent = getIntent();
        p1score = intent.getIntExtra("p1score", 0);
        p2score = intent.getIntExtra("p2score", 0);
        rd = (TextView) findViewById(R.id.round);
        p1 = (TextView) findViewById(R.id.p1);
        p2 = (TextView) findViewById(R.id.p2);
        p2.setText("P2 : " + p2score);
        p1.setText("P1 : " + p1score);
        round = intent.getIntExtra("round", 0);
        rd.setText("Round: " + round);

        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();

            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p2 = (TextView) findViewById(R.id.p1);
                p2score += tempsum;
                p2.setText("P2: " + p2score);
                tempsum = 0;
                if(p2score >= 100)
                {
                    winner();
                }
                else
                {
                    turnover();
                }
            }
        });

        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        if(p2score >= 100)
        {
            winner();
        }
        else {
            int val1 = 1 + (int) (6 * Math.random());
            int val2 = 1 + (int) (6 * Math.random());

            setDie(val1, die1);
            setDie(val2, die2);

            //if either die gets one, turn ends, otherwise adjusts score

            if (val1 != 1 && val2 != 1) {
                temp2 = (TextView) findViewById(R.id.temp2);
                tempsum += val1 + val2;
                if (tempsum < 10) {
                    temp2.setText("Temporary Score : 0" + tempsum);
                } else {
                    temp2.setText("Temporary Score : " + tempsum);
                }
            } else {
                one();
            }
        }

    }
    //End turn and allow next player to play
    public void turnover()
    {
        Intent intent = new Intent(Player2.this,MainActivity.class);
        intent.putExtra("p1score", p1score);
        intent.putExtra("p2score", p2score);
        intent.putExtra("round", round+1);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    //Rolled a one, ends turn
    public void one()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
        alertDialog.setTitle("Your Turn Has Ended");
        alertDialog.setMessage("You have rolled a 1 on one or two die. Your turn is over.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        turnover();
                    }
                });
        alertDialog.show();
        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {turnover();}
        });
        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {turnover();}
        });
    }

    //WINNER
    public void winner()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
        alertDialog.setTitle("You Won!");
        alertDialog.setMessage("Congratulations!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
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
