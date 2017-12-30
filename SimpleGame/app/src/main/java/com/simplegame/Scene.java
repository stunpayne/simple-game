package com.simplegame;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by anand.verma on 30/12/17.
 */

public interface Scene
{
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent motionEvent);
}
