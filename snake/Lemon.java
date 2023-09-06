package com.javarush.games.snake;
import com.javarush.engine.cell.*;

public class Lemon extends GameObject{
    String lemonSign = "\ud83c\udf82";
    boolean isAlive = true;
    public Lemon(int x, int y)
    {
        super(x,y);
    }
}
