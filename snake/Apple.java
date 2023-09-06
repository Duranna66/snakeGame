package com.javarush.games.snake;
import com.javarush.engine.cell.*;

public class Apple extends GameObject{
    public boolean isAlive = true;
    private static final String APPLE_SIGN = "\ud83c\udf81";
    public Apple(int x, int y)
    {
     super(x,y);   
    }
    public void draw(Game game)
    {
        game.setCellValueEx(x, y, Color.NONE, APPLE_SIGN, Color.BLUE, 85);
    }
}