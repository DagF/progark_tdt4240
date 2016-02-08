package de.dagfro.technologyintro;

import android.graphics.Canvas;

/**
 * Created by dagfs on 03-Feb-16.
 */
public class Task4Thread extends Thread{
    static final long FPS = 24;
    private Task4View view;
    private boolean running = false;

    public Task4Thread(Task4View view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = view.getHolder().lockCanvas();
                if( c != null){
                    synchronized (view.getHolder()) {
                        view.onDraw(c);
                    }
                }
                else{
                    this.setRunning(false);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }
}
