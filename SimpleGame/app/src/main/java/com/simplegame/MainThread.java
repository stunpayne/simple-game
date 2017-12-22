package com.simplegame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by anand.verma on 23/12/17.
 */

public class MainThread extends Thread
{
    public static final int MAX_FPS = 30;
    public static final int ONE_MILLION = 1000000;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    @Override
    public void run()
    {
        long startTime;
        long timeMillis = 1000 / MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while (running)
        {
            startTime = System.nanoTime();
            canvas = null;
            try
            {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (canvas != null)
                {
                    try
                    {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / ONE_MILLION;
            waitTime = targetTime - timeMillis;
            try
            {
                if (waitTime > 0)
                {
                    System.out.println("Frame CPU Idle Percentage Time: " + String.valueOf
                            (100 * waitTime / targetTime));
                    sleep(waitTime);
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == MAX_FPS)
            {
                averageFPS = 1000 / ((totalTime / frameCount) / ONE_MILLION);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }
}
