package com.simplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by anand.verma on 23/12/17.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{

    private MainThread thread;

    public GamePanel(Context context)
    {
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder)
    {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder)
    {
        boolean retry = true;
        while (retry)
        {
            try
            {
                thread.setRunning(false);
                thread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }

    public void update()
    {

    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
    }
}
