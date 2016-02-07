package de.dagfro.technologyintro;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by dagfs on 07-Feb-16.
 */
public class Task4Bar {
    private int length= 100;
    private int width = 15;
    private Paint color = new Paint(Color.BLACK);
    private Point point;

    public Task4Bar(Point point){
        this.point = point;
    }
}
