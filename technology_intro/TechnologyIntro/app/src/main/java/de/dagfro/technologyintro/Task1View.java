package de.dagfro.technologyintro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

/**
 * Created by dagfs on 03-Feb-16.
 */
public class Task1View extends View {

    Bitmap b;

    public Task1View(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        b = BitmapFactory.decodeResource(getResources(), R.drawable.heli);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(b, 10, 10, null);

    }
}
