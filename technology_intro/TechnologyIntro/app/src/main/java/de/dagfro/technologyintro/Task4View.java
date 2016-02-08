package de.dagfro.technologyintro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dagfs on 03-Feb-16.
 */
public class Task4View extends SurfaceView {
    private SurfaceHolder holder;
    private Task4Thread task4Thread;
    private Task4Game task4Game;

    public Task4View(Context context) {
        super(context);
        task4Game = new Task4Game(context);
        task4Thread = new Task4Thread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                task4Thread.setRunning(false);
                while (retry) {
                    try {
                        task4Thread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                task4Thread.setRunning(true);
                task4Thread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

        task4Thread.run();
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

    @Override
    protected void onDraw(Canvas canvas) {
        if(canvas != null){
            task4Game.draw(canvas);
        }

    }
}
