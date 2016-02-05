package de.dagfro.technologyintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
* TASK 2 – Input and text
Reuse the helicopter sprite from task 1.

a) Draw the sprite on the screen, but instead of having it go around on its own,
make it so that you can use touch function to control the movement.
The sprite must not be allowed to exit the screen.

b) Print the position of the sprite (in screen coordinates). The text should be drawn in the upper-left corner of the screen.
*/

public class Task2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Task2View(this));
    }
}
