package com.simplegame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by sunny.s on 23/12/17.
 */

public class Obstacle implements GameObject
{
    private Rect rectangle;
    private Rect rectangle2;
    private int rectHeight;
    private int color;


    public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap)
    {
        this.color = color;
        this.rectangle = new Rect(0, startY, startX, startY + rectHeight);
        this.rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
    }

    public Rect getRectangle()
    {
        return rectangle;
    }

    public boolean playerCollide(RectPlayer player)
    {
        return Rect.intersects(rectangle, player.getRectangle()) || Rect.intersects(rectangle2,
                player.getRectangle());
    }

    public void incrementY(float increment){
        rectangle.top += increment;
        rectangle.bottom += increment;
        rectangle2.top += increment;
        rectangle2.bottom += increment;
    }

    @Override
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2, paint);
    }

    @Override
    public void update()
    {

    }
}
