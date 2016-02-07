package de.dagfro.technologyintro;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by dagfs on 07-Feb-16.
 */
public class Task4Ball {

    private Point point;
    private int radius;
    private Paint color = new Paint(Color.BLACK);


    public void draw(Canvas canvas){
        canvas.drawCircle(point.x, point.y, radius, color);
    }

    public Task4Ball(Point point, int radius){

        this.point = point;
        this.radius = radius;
    }
}
