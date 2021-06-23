package com.amostrone.akash.ponggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
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

    int block_move_y=-5;
    int block_move_x=5;
    int dirX=(int)Math.signum((Math.random()-0.5));
    int dirY=1;

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

        //When block and player collide, invert x direction motion
        //Increase Score
        //Increase speed
        if(Rect.intersects(block,player)) {
            score_val+=2;
            dirY*=-1;

            //Increase Speed
            block_move_x++;
            block_move_y++;
        }

        //When block and upbar collide, invert y direction motion
        if(Rect.intersects(block,upbar)) {
            dirY*=-1;
        }

        block_movement();

        postInvalidate();
    }

    void block_movement(){
        //Up and down movement
        block_Y-=block_move_y*dirY;
        if(block_Y>=getHeight()){
            Toast.makeText(getContext(), "Game Ended, Your Score is "+score_val, Toast.LENGTH_SHORT).show();

            //Reset Speed
            block_move_x=5;
            block_move_y=5;
            //Reset Score
            score_val=0;

            dirY*=-1;
        }

        //Log.i("X Direction",block_X+"");

        //Right and Left Movement
        if(block_X<=0 || block_X>=getWidth()) {
            dirX*=-1;
        }
        block_X-=block_move_x*dirX;
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
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                //Toast.makeText(getContext(), "Touch 2", Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
        }
        postInvalidate();

        return true;
    }

}
