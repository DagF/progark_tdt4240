package de.dagfro.technologyintro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by dagfs on 07-Feb-16.
 */
public class Task3Heli {

    private Bitmap bmp;
    private int x;
    private int y;
    int xdir = 1;
    int ydir = 1;
    private Boolean init = false;
    private int xSpeed = 10;
    private int ySpeed = 10;

    public Task3Heli(Context context){
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.heli);
    }

    private Bitmap flip(Bitmap b)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap src = b;

        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return dst;
    }

    public void draw(Canvas canvas){
        if(canvas != null){
            if( !init ){
                init = true;
                Random generator = new Random();
                x = generator.nextInt((int)canvas.getWidth()/bmp.getWidth())*bmp.getWidth();
                y = generator.nextInt((int)canvas.getHeight()/bmp.getHeight())*bmp.getHeight();
                bmp = flip(bmp);
            }
            if (x >= canvas.getWidth() - bmp.getWidth()) {
                if(xdir == 1){
                    xdir = -1;
                    bmp = flip(bmp);
                }
            }
            if (x <= 0) {
                if(xdir == -1){
                    xdir = 1;
                    bmp = flip(bmp);
                }
            }
            x = x + xdir * xSpeed;

            if (y >= canvas.getHeight() - bmp.getHeight()) {
                ydir = -1;
            }
            if (y <= 0) {
                ydir = 1;
            }
            y = y + ydir*ySpeed;
            canvas.drawBitmap(bmp, x, y, null);
        }
    }
}
