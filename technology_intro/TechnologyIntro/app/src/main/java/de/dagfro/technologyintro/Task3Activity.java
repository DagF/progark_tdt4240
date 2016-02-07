package de.dagfro.technologyintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
*
* a) Create the same scenario as in task 1, but apply animation to the sprite.
* Each frame in the animation should display for 100 ms before changing to the
* next frame. Also, the animation should loop, meaning that you start around
* again with the first frame after the last one finished.

b) Throw in a couple more sprites (you can use the same sprite, or create new
ones yourself). Move the sprites around in random directions and with random speed.
If the sprites collide, i.e. their bounding volumes intersect, the sprites should
bounce off each other. */

public class Task3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Task3View(this));
    }
}
