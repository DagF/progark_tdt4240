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
public class Task1View extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private Task1Thread task1Thread;
    private int x = 0;
    private int xSpeed = 10;

    public Task1View(Context context) {
        super(context);
        task1Thread = new Task1Thread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                task1Thread.setRunning(false);
                while (retry) {
                    try {
                        task1Thread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                task1Thread.setRunning(true);
                task1Thread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.heli);

        task1Thread.run();
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
        if (x >= getWidth() - bmp.getWidth()) {
            xSpeed = -10;
            bmp = flip(bmp);
        }
        if (x <= 0) {
            xSpeed = 10;
            bmp = flip(bmp);
        }
        x = x + xSpeed;
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bmp, x, 10, null);
    }
}
