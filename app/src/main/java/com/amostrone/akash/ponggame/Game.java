package com.amostrone.akash.ponggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Game extends View {

    Paint paint_block;
    Paint paint_score;

    public Game(Context context) {
        super(context);
        paint_block = new Paint();
        paint_score = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint_block.setColor(Color.GRAY);
        paint_block.setStrokeWidth(3);
        int middle=getWidth()/2;
        int top=getHeight();

        canvas.drawRect(0,100,middle*2,150,paint_block);
        canvas.drawRect(middle-150,top-125,middle+150,top-75,paint_block);

        paint_score.setColor(Color.BLACK);
        paint_score.setStyle(Paint.Style.FILL);
        //canvas.drawPaint(paint_score);

        paint_score.setColor(Color.GRAY);
        paint_score.setTextSize(50);

        canvas.drawText("Score : ",middle-100,50,paint_score);
    }
}
