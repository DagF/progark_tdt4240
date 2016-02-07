package de.dagfro.technologyintro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
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
    private int currentFrame = 0;

    public Task3Heli(Context context,int x,int y){
        this.x = x;
        this.y=y;
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.heili2);
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

    public int width(){
        return bmp.getWidth();
    }

    public int height(){
        return bmp.getHeight();
    }

    public Point point(){
        return new Point(x,y);
    }

    public void draw(Canvas canvas){
        if(canvas != null){
            if( !init ){
                init = true;
                Random generator = new Random();
                xSpeed = generator.nextInt(10)+5;
                ySpeed = generator.nextInt(10)+5;
                bmp = flip(bmp);
            }
            if (x >= canvas.getWidth() - bmp.getWidth()/4) {
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

            int srcX = (currentFrame % 4) * (bmp.getWidth()/4);
            int srcY = 0;
            Rect src = new Rect(srcX, srcY, (int)srcX + bmp.getWidth()/4, srcY + bmp.getHeight());
            Rect dst = new Rect(x, y, (int)x + bmp.getWidth()/4, y + bmp.getHeight());
            canvas.drawBitmap(bmp, src, dst, null);
            currentFrame++;
        }
    }

    public void changeXDirection(){
        xdir *= -1;
        bmp = flip(bmp);
    }

    public void changeYDirection(){
        ydir *= -1;
    }

    public void collidesWidth(Task3Heli chopper){
        //x aksen

        int t = y;
        int b = y + this.height();
        int l = x;
        int r = x + this.width();
        Rect re = new Rect(l, t,r, b);


        int ct = chopper.point().y;
        int cb = chopper.point().y + chopper.height();
        int cl = chopper.point().x;
        int cr = chopper.point().x + chopper.width();
        Rect c = new Rect(cl,ct,cr,cb);


        if (c.intersect(re))
                 {
                     if(y < chopper.point().y){
                         if(ydir != -1){
                             this.changeYDirection();
                         }
                     }
                     else{
                         this.changeYDirection();
                         this.changeXDirection();

                     }
        }



    }
}
