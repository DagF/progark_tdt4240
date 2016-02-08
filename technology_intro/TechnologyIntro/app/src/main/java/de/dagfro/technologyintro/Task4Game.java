package de.dagfro.technologyintro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

import java.util.Objects;

/**
 * Created by dagfs on 08-Feb-16.
 */
public class Task4Game extends View{

    private Task4Bar leftBar;
    private Task4Bar rightBar;
    private Task4Ball ball;
    private int leftScore = 0;
    private int rightScore = 0;
    private Context context;

    public Task4Game(Context context){
        super(context);
        this.context= context;
        leftBar = new Task4Bar(context, new Point(50, 100), false, false);
        rightBar = new Task4Bar(context, new Point(50, 100), true, true);
        ball = new Task4Ball(context, null, 20);
    }

    @Override
    public void onDraw(Canvas canvas){
        if(canvas != null){
            clearCanvas(canvas);
            leftBar.onDraw(canvas);
            rightBar.onDraw(canvas);
            ball.onDraw(canvas);

            if( ball.getRext().intersect(leftBar.getRect())
                    ||ball.getRext().intersect(rightBar.getRect())){
                ball.changeDirection();
            }

            else if(ball.getPoint().x < 50){
                rightScore += 1;
                ball = new Task4Ball(context, null, 20);
            }


            else if(ball.getPoint().x>canvas.getWidth()-50){
                leftScore += 1;
                ball = new Task4Ball(context, null, 20);
            }


            drawLayout(canvas);
            drawScore(canvas, "left", leftScore);
            drawScore(canvas, "right", rightScore);

        }
    }

    private void drawScore(Canvas canvas, String position, int score) {
        float y = 60;
        float x;
        if(position.equals("right")){
            x = (canvas.getWidth() / 4) * 3;
        }
        else{
            x = (canvas.getWidth() / 4);
        }
        Paint paint = new Paint();

        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
        canvas.drawText("" + score, x, y, paint);
    }

    private void drawLayout(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        canvas.drawLine(20, 100, canvas.getWidth() - 20, 100, paint);
        canvas.drawLine(canvas.getWidth()/2, 100, canvas.getWidth()/2, canvas.getHeight()-100, paint);
        canvas.drawLine(20, canvas.getHeight()-100, canvas.getWidth()-20 , canvas.getHeight() - 100, paint);
    }

    private void clearCanvas(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }
}
