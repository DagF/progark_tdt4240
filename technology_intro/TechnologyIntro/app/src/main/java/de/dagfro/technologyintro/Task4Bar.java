package de.dagfro.technologyintro;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by dagfs on 07-Feb-16.
 */
public class Task4Bar {
    private int height= 100;
    private int width = 15;
    private Paint color = new Paint(Color.BLACK);
    private Point point;
    private int ySpeed;
    private int xSpeed;


    public void draw(Canvas canvas){
        canvas.drawRect(point.x, point.y, point.x + width, point.y + height,color);
    }

    public Task4Bar(Point point){
        this.point = point;
    }
}
