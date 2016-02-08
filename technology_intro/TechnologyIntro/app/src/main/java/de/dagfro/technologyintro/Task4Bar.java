package de.dagfro.technologyintro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by dagfs on 07-Feb-16.
 */
public class Task4Bar extends View{
    private int height= 200;
    private int width = 30;
    private Point point;
    private int direction = 1;
    private int ySpeed = 10 ;
    private boolean auto;
    boolean right;

    @Override
    public void onDraw(Canvas canvas){
        if( right){
            point.x = canvas.getWidth()-50;
        }
        if( canvas != null && point != null){
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            updatePosition(100, canvas.getHeight() - 100);
            canvas.drawRect(point.x, point.y, point.x + width, point.y + height,  paint);

        }
    }

    private void updatePosition(float min, float max) {
        if( auto ){
            if( point.y + direction*ySpeed + height > max){
                point.y = (int)max - height;
                direction *= -1;
            }
            else if(point.y + direction*ySpeed < min){
                point.y = (int)min;
                direction *= -1;
            }
            else{
                point.y = point.y + direction * ySpeed;

            }
        }
    }

    public Rect getRect(){
        return new Rect(
                (int)point.x,
                (int)point.y,
                (int)point.x + width,
                (int)point.y + height
        );
    }

    public Task4Bar(Context context, Point point, boolean auto, boolean right){
        super(context);
        this.point = point;
        this.auto = auto;
        this.right = right;
    }
}
