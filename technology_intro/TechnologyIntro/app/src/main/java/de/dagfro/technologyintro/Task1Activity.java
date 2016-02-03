package de.dagfro.technologyintro;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by dagfs on 03-Feb-16.
 *
 * TASK 1 – Sprites
 For this task, you should draw graphics on the screen (You can use Sprite class from Sheep).
 You can use the helicopter texture or your own texture for the sprite.

 Draw the helicopter on the screen and make it move around on its own. If the sprite is about to
 leave the screen, it should “bounce” off the edges and head in the opposite direction.
 Also, make sure the helicopter faces the direction it is traveling.
 */
public class Task1Activity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Task1View(this));
    }
}
