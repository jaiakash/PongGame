package com.amostrone.akash.ponggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Game extends View {

    Rect block;
    Rect player;
    Rect upbar;

    Paint paint_block;
    Paint paint_player;
    Paint paint_score;
    Paint paint_upbar;

    static int player_pos=-1;
    static int block_X=-1;
    static int block_Y=-1;

    static int score_val=0;

    public Game(Context context) {
        super(context);
        player = new Rect();
        block = new Rect();
        upbar = new Rect();
        paint_block = new Paint();
        paint_player = new Paint();
        paint_score = new Paint();
        paint_upbar = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int middle=getWidth()/2;
        int top=getHeight();

        if(player_pos==-1)player_pos=middle;

        if(block_X==-1)block_X=middle;
        if(block_Y==-1)block_Y=top-300;

        paint_block.setColor(Color.RED);
        block.bottom=block_Y+25;
        block.top=block_Y-25;
        block.left=block_X-25;
        block.right=block_X+25;
        canvas.drawRect(block,paint_block);

        paint_player.setColor(Color.GREEN);
        player.bottom=top-25;
        player.top=top-75;
        player.left=player_pos-150;
        player.right=player_pos+150;
        canvas.drawRect(player,paint_player);

        paint_upbar.setColor(Color.GRAY);
        upbar.bottom=150;
        upbar.top=100;
        upbar.left=0;
        upbar.right=middle*2;
        canvas.drawRect(upbar,paint_upbar);

        paint_score.setColor(Color.GRAY);
        paint_score.setTextSize(50);
        canvas.drawText("Score : "+score_val,middle-100,75,paint_score);

        if(Rect.intersects(block,player)) {
            score_val++;
            Toast.makeText(getContext(), "Collision", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();

        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);

        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN:
                player_pos=(int) event.getX();
                block_Y+=50;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                //Toast.makeText(getContext(), "Touch 2", Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
        }
        invalidate();

        return true;
    }

}
