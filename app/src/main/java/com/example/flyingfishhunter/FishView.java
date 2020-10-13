package com.example.flyingfishhunter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FishView extends View
{

    DatabaseHighScore dbFetchFishDesign;
    private int fishX = 10;
    private int fishY ;
    private int fishSpeed;

    private Boolean touch = false;

    private int yellowX, yellowY, yellowSpeed= 16;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 20;
    private Paint greenPaint = new Paint();

    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();

    private int score = 0,lifeCountingFish = 3;
    private int canvasWidth, canvasHeight;
    private Bitmap fish [] = new Bitmap[3];
    private Bitmap gameBg;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    private Boolean fishDesign1[] = new Boolean[7];
    private Boolean lifeBonus = true;

    public FishView(Context context)
    {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_fish_100);
/*        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_fish_100__1_);
        fish[2] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_fish_100__2_);*/
        gameBg = BitmapFactory.decodeResource(getResources(), R.drawable.bg1);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_fish_food_24);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_no_fish_24);

        fishY = 550;


        fishDesign1[0] = false;
        fishDesign1[1] = false;
        fishDesign1[2] = false;
        fishDesign1[3] = false;
        fishDesign1[4] = false;
        fishDesign1[5] = false;
        fishDesign1[6] = false;

        dbFetchFishDesign = new DatabaseHighScore(getContext());


        Cursor res[] = new Cursor[7];
        res[0] = dbFetchFishDesign.getFishDesignData("1");
        res[1] = dbFetchFishDesign.getFishDesignData("2");
        res[2] = dbFetchFishDesign.getFishDesignData("3");
        res[3] = dbFetchFishDesign.getFishDesignData("4");
        res[4] = dbFetchFishDesign.getFishDesignData("5");
        res[5] = dbFetchFishDesign.getFishDesignData("6");
        res[6] = dbFetchFishDesign.getFishDesignData("7");

        if (res[0].moveToFirst())
            fishDesign1[0] = Boolean.valueOf(res[0].getString(1));
        if (res[1].moveToFirst())
            fishDesign1[1] = Boolean.valueOf(res[1].getString(1));
        if (res[2].moveToFirst())
            fishDesign1[2] = Boolean.valueOf(res[2].getString(1));
        if (res[3].moveToFirst())
            fishDesign1[3] = Boolean.valueOf(res[3].getString(1));
        if (res[4].moveToFirst())
            fishDesign1[4] = Boolean.valueOf(res[4].getString(1));
        if (res[5].moveToFirst())
            fishDesign1[5] = Boolean.valueOf(res[5].getString(1));
        if (res[6].moveToFirst())
            fishDesign1[6] = Boolean.valueOf(res[6].getString(1));


        if (fishDesign1[0] == true) {
            fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_fish_100__2_);
        }
        if (fishDesign1[1] == true) {
            fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_fish_100__3_);
        }
        if (fishDesign1[2] == true) {
            fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_fish_100__4_);
        }
        if (fishDesign1[3] == true) {
            fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_fish_100__5_);
        }
        if (fishDesign1[4] == true) {
            fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_flounder_fish_100);
        }
        if (fishDesign1[5] == true) {
            fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.tropical_fish_icon);
        }
        if (fishDesign1[6] == true) {
            fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.icons8_fish_100);
        }

    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(gameBg,0, 0, null);
        canvas.drawText("Score : " + score, 20,60, scorePaint);

        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;
        if(fishY < minFishY)
        {
            fishY = minFishY;
        }
        if(fishY > maxFishY)
        {
            fishY = maxFishY;
        }
        fishSpeed = fishSpeed + 2;

        if (touch)
        {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
            touch = false;
        }
        else
        {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }
        if(ballStriker(yellowX,yellowY))
        {
            score = score + 5;
            yellowX = 0;
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }

        if(ballStriker(redX,redY))
        {
            redX = 0;
            lifeCountingFish--;
            if (lifeCountingFish == 0)
            {
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT).show();
                Intent overIntent = new Intent(getContext(),GameOver.class);
                overIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                overIntent.putExtra("score", score);
                getContext().startActivity(overIntent);
            }
        }


        if(ballStriker(greenX,greenY))
        {
            score = score + 10;
            greenX = 0;
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }

        yellowX = yellowX - yellowSpeed;
        if (yellowX < 0)
        {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX, yellowY, 30, yellowPaint);

        redX = redX - redSpeed;
        if (redX < 0)
        {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(redX, redY, 25, redPaint);

        greenX = greenX - greenSpeed;
        if (greenX < 0)
        {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX, greenY, 20, greenPaint);

        /*if (lifeBonus == true)
        {
            if (lifeCountingFish < 3)
                lifeCountingFish++;
        }*/

        for (int i=0; i<3; i++)
        {
            int lifeX = (int) (540 + 100 * i);
            int lifeY = 10;
            if (lifeBonus == true)
            {
                if (score > 500)
                {
                    if (lifeCountingFish < 3)
                        lifeCountingFish++;
                    lifeBonus = false;
                }
            }
            if (lifeCountingFish > i)
            {
                canvas.drawBitmap(life[0], lifeX, lifeY, null);
            }
            else
            {
                canvas.drawBitmap(life[1], lifeX, lifeY, null);
            }
        }
    }

    public boolean ballStriker(int x, int y)
    {
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;

            fishSpeed = -22;
        }
        return true;
    }
}