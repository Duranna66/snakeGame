package com.javarush.games.snake;
import com.javarush.engine.cell.*;

public class SnakeGame extends Game{
    private int score;
    private Apple apple;
    private Lemon lemon;
    private Snake snake;
    private int turnDelay;
    public static final int HEIGHT = 15;
    public static final int WIDTH = 15;
    private boolean isGameStopped;
    private static final int GOAL = 28;
    public void initialize()
    {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
    private void drawScene(){
        for (int x = 0; x < WIDTH; x++)
            for(int y = 0; y < HEIGHT;y++)
            {
                setCellValueEx(x, y, Color.LIGHTPINK, "");
            }
        snake.draw(this);
        apple.draw(this);
    }
    private void createGame(){
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();
        createNewLemon();
        isGameStopped = false;
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        score = 0;
        setScore(score);
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if(apple.isAlive == false) {
            createNewApple();
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        if (snake.isAlive == false)
            gameOver();
        if (snake.getLength() > 28)
            win();
        drawScene();
    }
    public void onKeyPress(Key key)
    {
        if(key == Key.LEFT)
        {
            snake.setDirection(Direction.LEFT);
        }
        else if(key == Key.RIGHT)
        {
            snake.setDirection(Direction.RIGHT);
        }
        else if(key == Key.UP)
        {
            snake.setDirection(Direction.UP);
        }
        else if(key == Key.DOWN)
        {
            snake.setDirection(Direction.DOWN);
        }
        else if(key == Key.SPACE && isGameStopped)
        {
            createGame();
        }
    }
    private void createNewApple()
    {
        Apple apple1;
        do {
             apple1 = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        }
        while (snake.checkCollision(apple1));
        this.apple = apple1;

    }
    private void gameOver()
    {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.WHITE,"Happy New Year!!!", Color.AQUAMARINE, 70);
    }
    private void win()
    {
        stopTurnTimer();
        isGameStopped= true;
        showMessageDialog(Color.WHITE, "YOU WIN", Color.GREEN, 70);
    }
    private void createNewLemon()
    {
        Lemon lemon1;
        do {
            lemon1 = new Lemon(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        }
        while (snake.checkCollision(lemon1));
        this.lemon = lemon1;

    }

}