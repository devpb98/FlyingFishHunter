package com.example.flyingfishhunter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.zip.Inflater;

import static android.widget.Toast.LENGTH_SHORT;

public class HuntingActivity extends AppCompatActivity {

    DatabaseHighScore dbFish;
    private Button btnStart;
    private ImageButton btnSettings;
    private ImageButton btnFishColor;
    private ImageButton btnRules;
    private View fishView;
    private View settingView;
    private View ruleView;
    private boolean fishDesign[] = new boolean[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunting);
        dbFish = new DatabaseHighScore(HuntingActivity.this);


        btnStart= (Button) findViewById(R.id.btnStart);
        btnFishColor = (ImageButton) findViewById(R.id.btnFishColor);
        btnSettings = (ImageButton) findViewById(R.id.btnSetting);
        btnRules = (ImageButton) findViewById(R.id.btnRule);

        fishDesign[0] = false;
        fishDesign[1] = false;
        fishDesign[2] = false;
        fishDesign[3] = false;
        fishDesign[4] = false;
        fishDesign[5] = false;
        fishDesign[6] = false;

        dbFish.insertFishDesignData(1,String.valueOf(fishDesign[0]));
        dbFish.insertFishDesignData(2,String.valueOf(fishDesign[1]));
        dbFish.insertFishDesignData(3,String.valueOf(fishDesign[2]));
        dbFish.insertFishDesignData(4,String.valueOf(fishDesign[3]));
        dbFish.insertFishDesignData(5,String.valueOf(fishDesign[4]));
        dbFish.insertFishDesignData(6,String.valueOf(fishDesign[5]));
        dbFish.insertFishDesignData(7,String.valueOf(fishDesign[6]));

        Cursor res[] = new Cursor[7];
        res[0] = dbFish.getFishDesignData("1");
        res[1] = dbFish.getFishDesignData("2");
        res[2] = dbFish.getFishDesignData("3");
        res[3] = dbFish.getFishDesignData("4");
        res[4] = dbFish.getFishDesignData("5");
        res[5] = dbFish.getFishDesignData("6");
        res[6] = dbFish.getFishDesignData("7");

        if (res[0].moveToFirst())
            fishDesign[0] = Boolean.valueOf(res[0].getString(1));
        if (res[1].moveToFirst())
            fishDesign[1] = Boolean.valueOf(res[1].getString(1));
        if (res[2].moveToFirst())
            fishDesign[2] = Boolean.valueOf(res[2].getString(1));
        if (res[3].moveToFirst())
            fishDesign[3] = Boolean.valueOf(res[3].getString(1));
        if (res[4].moveToFirst())
            fishDesign[4] = Boolean.valueOf(res[4].getString(1));
        if (res[5].moveToFirst())
            fishDesign[5] = Boolean.valueOf(res[5].getString(1));
        if (res[6].moveToFirst())
            fishDesign[6] = Boolean.valueOf(res[6].getString(1));



        for (int i=0; i<7; i++)
        {
            if (fishDesign[i] == true)
            {

            }
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HuntingActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder settingBuilder = new AlertDialog.Builder(HuntingActivity.this);
                settingView = getLayoutInflater().inflate(R.layout.settingdialog, null);
                settingBuilder.setView(settingView);
                settingBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog1 = settingBuilder.create();
                dialog1.show();
            }
        });

        btnRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder ruleBuilder = new AlertDialog.Builder(HuntingActivity.this);
                ruleView = getLayoutInflater().inflate(R.layout.ruledialog, null);
                ruleBuilder.setView(ruleView);
                ruleBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog2 = ruleBuilder.create();
                dialog2.show();

            }
        });



        btnFishColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder fBuilder = new AlertDialog.Builder(HuntingActivity.this);
                fishView = getLayoutInflater().inflate(R.layout.fishdialog, null);
                fBuilder.setView(fishView);
                fBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ImageButton img1btn = (ImageButton) fishView.findViewById(R.id.imageButton);
                ImageButton img2btn = (ImageButton) fishView.findViewById(R.id.imageButton2);
                ImageButton img3btn = (ImageButton) fishView.findViewById(R.id.imageButton3);
                ImageButton img4btn = (ImageButton) fishView.findViewById(R.id.imageButton4);
                ImageButton img5btn = (ImageButton) fishView.findViewById(R.id.imageButton5);
                ImageButton img6btn = (ImageButton) fishView.findViewById(R.id.imageButton6);

                img1btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fishDesign[0] = true;
                        for (int i=1; i<6; i++)
                            fishDesign[i] = false;
                        dbFish.updateFishDesignData("1",String.valueOf(fishDesign[0]));
                        dbFish.updateFishDesignData("2",String.valueOf(fishDesign[1]));
                        dbFish.updateFishDesignData("3",String.valueOf(fishDesign[2]));
                        dbFish.updateFishDesignData("4",String.valueOf(fishDesign[3]));
                        dbFish.updateFishDesignData("5",String.valueOf(fishDesign[4]));
                        dbFish.updateFishDesignData("6",String.valueOf(fishDesign[5]));
                    }
                });

                img2btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fishDesign[1] = true;
                        for (int i=2; i<6; i++)
                            fishDesign[i] = false;
                        dbFish.updateFishDesignData("2",String.valueOf(fishDesign[1]));
                        dbFish.updateFishDesignData("3",String.valueOf(fishDesign[2]));
                        dbFish.updateFishDesignData("4",String.valueOf(fishDesign[3]));
                        dbFish.updateFishDesignData("5",String.valueOf(fishDesign[4]));
                        dbFish.updateFishDesignData("6",String.valueOf(fishDesign[5]));
                    }
                });

                img3btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fishDesign[2] = true;
                        for (int i=3; i<6; i++)
                            fishDesign[i] = false;
                        dbFish.updateFishDesignData("3",String.valueOf(fishDesign[2]));
                        dbFish.updateFishDesignData("4",String.valueOf(fishDesign[3]));
                        dbFish.updateFishDesignData("5",String.valueOf(fishDesign[4]));
                        dbFish.updateFishDesignData("6",String.valueOf(fishDesign[5]));
                    }
                });

                img4btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fishDesign[3] = true;
                        for (int i=4; i<6; i++)
                            fishDesign[i] = false;
                        dbFish.updateFishDesignData("4",String.valueOf(fishDesign[3]));
                        dbFish.updateFishDesignData("5",String.valueOf(fishDesign[4]));
                        dbFish.updateFishDesignData("6",String.valueOf(fishDesign[5]));
                    }
                });

                img5btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fishDesign[4] = true;
                        for (int i=5; i<6; i++)
                            fishDesign[i] = false;
                        dbFish.updateFishDesignData("5",String.valueOf(fishDesign[4]));
                        dbFish.updateFishDesignData("6",String.valueOf(fishDesign[5]));
                    }
                });

                img6btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fishDesign[5] = true;
                        for (int i=6; i<6; i++)
                            fishDesign[i] = false;
                        dbFish.updateFishDesignData("6",String.valueOf(fishDesign[5]));
                    }
                });
                AlertDialog dialog3 = fBuilder.create();
                dialog3.show();
            }
        });


    }


}