package de.dagfro.technologyintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class Task4Activity extends AppCompatActivity {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        if(task4Game != null){
            task4Game.touch(ev);
        }
        return true;
    }

    Task4Game task4Game;

    public void setTask4Game(Task4Game game){
        this.task4Game = game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Task4View(this, this));
    }
}
