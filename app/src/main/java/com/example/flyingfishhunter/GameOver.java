package com.example.flyingfishhunter;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    DatabaseHighScore myDb;
    private Button btnPlayAgain;
    private TextView scoreText;
    private LinearLayout linearLayout;
    private String Score;
    private TextView hScore[] = new TextView[3];
    private int HScore[ ] = new int[3] , NScore;
    private String Hsname[] = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        myDb = new DatabaseHighScore(GameOver.this);

        linearLayout = (LinearLayout) findViewById(R.id.llId);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgainId);
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newGameIntent = new Intent(GameOver.this,MainActivity.class);
                startActivity(newGameIntent);
            }
        });

        hScore[0] = (TextView) findViewById(R.id.hs1Id);
        hScore[1] = (TextView) findViewById(R.id.hs2Id);
        hScore[2] = (TextView) findViewById(R.id.hs3Id);

        scoreText = (TextView) findViewById(R.id.scoreId);
        Score = getIntent().getExtras().get("score").toString();
        scoreText.setText("Score : " + Score);

        myDb.insertData(1 , hScore[0].getText().toString());
        myDb.insertData(2 , hScore[1].getText().toString());
        myDb.insertData(3 , hScore[2].getText().toString());

        Cursor res[] = new Cursor[3];
        res[0] = myDb.getData("1");
        res[1] = myDb.getData("2");
        res[2] = myDb.getData("3");
        if (res[0].moveToFirst())
            HScore[0] = Integer.valueOf(res[0].getString(1));
        hScore[0].setText( "1: "+ HScore[0]);
        if (res[1].moveToFirst())
            HScore[1] = Integer.valueOf(res[1].getString(1));
        hScore[1].setText("2: " + HScore[1]);
        if (res[2].moveToFirst())
            HScore[2] = Integer.valueOf(res[2].getString(1));
        hScore[2].setText("3: " + HScore[2]);

        NScore = Integer.parseInt(Score);
        if (HScore[0] <= NScore)
            {
                hScore[0].setText( "1: "+ NScore);
                myDb.updateData("1",String.valueOf(NScore));
                HScore[2] = HScore[1];
                HScore[1] = HScore[0];
                hScore[1].setText("2: " + HScore[1]);
                myDb.updateData("2",String.valueOf(HScore[0]));
                //HScore[2] = HScore[1];
                hScore[2].setText("3: " + HScore[2]);
                myDb.updateData("3",String.valueOf(HScore[1]));
            }
            else
            {

                if (HScore[1] <= NScore) {
                    hScore[1].setText("2: " + NScore);
                    myDb.updateData("2",String.valueOf(NScore));
                    HScore[2] = HScore[1];
                    hScore[2].setText("3: " + HScore[2]);
                    myDb.updateData("3",String.valueOf(HScore[1]));
                }
                else
                {
                    if (HScore[2] <= NScore) {
                        hScore[2].setText( "3: " + NScore);
                        myDb.updateData("3",String.valueOf(NScore));
                    }
                }
            }

    }
}
