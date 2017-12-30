package com.simplegame;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anand.verma on 30/12/17.
 */

public class SceneManager
{
    private List<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager()
    {
        ACTIVE_SCENE = 0;
        scenes.add(new GameplayScene());
    }

    public void update()
    {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas)
    {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

    public void receiveTouch(MotionEvent motionEvent){
        scenes.get(ACTIVE_SCENE).receiveTouch(motionEvent);
    }
}
