package de.dagfro.technologyintro;

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

    public Task4Ball(Point point, int radius){

        this.point = point;
        this.radius = radius;
    }
}
