package de.dagfro.technologyintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Task4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Task4View(this));
    }
}
