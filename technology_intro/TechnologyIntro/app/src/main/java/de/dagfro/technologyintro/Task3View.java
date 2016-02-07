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

import java.util.ArrayList;

/**
 * Created by dagfs on 03-Feb-16.
 */
public class Task3View extends SurfaceView {

    private SurfaceHolder holder;
    private Task3Thread task3Thread;
    private ArrayList<Task3Heli> choppers = new ArrayList<Task3Heli>();

    public Task3View(Context context) {
        super(context);
        task3Thread = new Task3Thread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                task3Thread.setRunning(false);
                while (retry) {
                    try {
                        task3Thread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                task3Thread.setRunning(true);
                task3Thread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

        for(int i = 0; i < 3; i++){
            choppers.add(new Task3Heli(context));
        }

        task3Thread.run();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        for(int i = 0; i < choppers.size(); i++){
            choppers.get(i).draw(canvas);
        }

    }
}
