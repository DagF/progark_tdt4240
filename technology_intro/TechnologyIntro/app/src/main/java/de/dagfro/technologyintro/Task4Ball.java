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
public class Task4Ball extends View{

    private Point point;
    private int radius;
    private int ySpeed = 5;
    private int xSpeed = 10;
    private int xDirection = 1;
    private int yDirection = -1;

    public Point getPoint(){
        return point;
    }

    @Override
    public void onDraw(Canvas canvas){
        if(point == null){
            point = new Point((int)canvas.getWidth()/2, (int)canvas.getHeight()/2);
        }
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        updatePosition(100, canvas.getHeight()-100);
        canvas.drawCircle(point.x, point.y, radius, paint);
    }

    private void updatePosition(int min, int max){
        point.x += xSpeed*xDirection;

        if(point.y + ySpeed*yDirection - radius < min){
            point.y = min + radius;
            yDirection *= -1;
        }
        else if(point.y + ySpeed*yDirection + radius > max){
            point.y = max - radius;
            yDirection *= -1;
        }
        else{
            point.y += ySpeed*yDirection;
        }
    }

    public Rect getRext(){
        return new Rect(
                (int)point.x - radius,
                (int)point.y - radius,
                (int)point.x + radius,
                (int)point.y + radius);
    }

    public void changeDirection(){
        xDirection *= -1;
    }

    public Task4Ball(Context context, Point point, int radius){
        super(context);

        this.point = point;
        this.radius = radius;
    }
}
