package de.dagfro.technologyintro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by dagfs on 05-Feb-16.
 */
public class Task2View extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private Task2Thread task2Thread;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 10;
    private Point finger;
    private int ySpeed = 10;
    private float mLastTouchX;
    private float mLastTouchY;
    private float lastAngle = 90;
    private int direction = -1;

    public Task2View(Context context) {
        super(context);
        task2Thread = new Task2Thread(this);
        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                task2Thread.setRunning(false);
                while (retry) {
                    try {
                        task2Thread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                task2Thread.setRunning(true);
                task2Thread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.heli);

        task2Thread.run();
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

    public void setFinger(Point finger){
        finger = finger;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final float x = ev.getX();
                final float y = ev.getY();

                // Remember where we started
                mLastTouchX = x;
                mLastTouchY = y;
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getX();
                final float y = ev.getY();

                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                // Move the object
                float mPosX = dx;
                float mPosY = dy;

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;
                finger = new Point((int)mLastTouchX,(int)mLastTouchY);

                // Invalidate to request a redraw
                invalidate();
                break;
            }
        }

        return true;
    }

    public static float angleBetweenTwoPointsWithFixedPoint(Point point1, Point point2) {

        float angle1 = (float) Math.atan2(point1.y - 0, point1.x - 0);
        float angle2 = (float) Math.atan2(point2.y - 0, point2.x - 0);

        return angle1 - angle2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(canvas != null){
            if(finger == null){
                finger = new Point(canvas.getWidth() / 2, canvas.getHeight()/2);
                x = finger.x;
                y = finger.y;
            }

            Point heli = new Point(x,y);



            if ((x >= getWidth() - bmp.getWidth() && x <= finger.x) || (x <= 0 && x >= finger.x) || (finger.x - 5.0 < x && x < finger.x + 5.0)) {
                xSpeed = 0;
            }
            else{
                if( x < finger.x){
                    xSpeed = 10;
                }
                else if( x > finger.x){
                    xSpeed = -10;
                }
                else{
                    xSpeed = 0;
                }
            }
            if ((y >= getHeight() - bmp.getHeight() && y <= finger.y)|| (y <= 0 && y >= finger.y) || (finger.y - 5.0 < y && y < finger.y + 5.0)) {
                ySpeed = 0;
            }
            else{
                if( y < finger.y){
                    ySpeed = 10;
                }
                else if(y > finger.y) {
                    ySpeed = -10;
                }
                else{
                    ySpeed = 0;
                }
            }
            x = x + xSpeed;
            y = y + ySpeed;

            if(x < finger.x && direction == -1){
                direction = 1;
                bmp = flip(bmp);
            }
            if(x > finger.x && direction == 1){
                direction = -1;
                bmp = flip(bmp);
            }


            Matrix m = new Matrix();

            canvas.drawColor(Color.MAGENTA);
            canvas.drawBitmap(bmp, x, y, null);

            Paint paint = new Paint();

            paint.setColor(Color.WHITE);
            paint.setTextSize(20);
            canvas.drawText("x: " + x + ", y: " + y, 10, 25, paint);
        }
    }
}
