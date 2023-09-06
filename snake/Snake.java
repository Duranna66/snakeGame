package com.javarush.games.snake;
import com.javarush.engine.cell.*;
import java.util.ArrayList;
import java.util.List;
public class Snake extends GameObject {
    private Direction direction = Direction.LEFT;
    public boolean isAlive = true;
    private static final String HEAD_SIGN = "\ud83c\udf85";
    private static final String BODY_SIGN = "\ud83c\udf86";
    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake(int x, int y) {
        super(x, y);
        GameObject gameObject1 = new GameObject(x, y);
        GameObject gameObject2 = new GameObject(x + 1, y);
        GameObject gameObject3 = new GameObject(x + 2, y);
        snakeParts.add(gameObject1);
        snakeParts.add(gameObject2);
        snakeParts.add(gameObject3);
    }

    public void setDirection(Direction direction1) {
        if(((direction == Direction.RIGHT) || (direction == Direction.LEFT)) && (snakeParts.get(0).x == snakeParts.get(1).x))
            return;
        if (((direction == Direction.UP) || (direction == Direction.DOWN)) && (snakeParts.get(0).y == snakeParts.get(1).y))
            return;
        if ((direction1 == Direction.RIGHT) && (direction != Direction.LEFT)) {
            this.direction = direction1;
        } else if ((direction1 == Direction.LEFT) && (direction != Direction.RIGHT))
            this.direction = direction1;
        else if ((direction1 == Direction.UP) && (direction != Direction.DOWN))
            this.direction = direction1;
        else if ((direction1 == Direction.DOWN) && (direction != Direction.UP))
            this.direction = direction1;
    }

    public void draw(Game game) {
        for (GameObject s : snakeParts) {
            if (snakeParts.indexOf(s) == 0 && isAlive)
                game.setCellValueEx(s.x, s.y, Color.NONE, HEAD_SIGN, Color.BLUE, 85);
            else if (snakeParts.indexOf(s) == 0 && !isAlive)
                game.setCellValueEx(s.x, s.y, Color.NONE, HEAD_SIGN, Color.RED, 80);

            else if (snakeParts.indexOf(s) > 0 && isAlive) {
                game.setCellValueEx(s.x, s.y, Color.NONE, BODY_SIGN, Color.WHITE, 50);

            } else
                game.setCellValueEx(s.x, s.y, Color.NONE, BODY_SIGN, Color.RED, 80);
        }
    }

    public void move(Apple apple) {
        GameObject gameObject = createNewHead();
        if (gameObject.x >= SnakeGame.WIDTH || gameObject.x < 0 || gameObject.y >= SnakeGame.HEIGHT || gameObject.y < 0) {
            isAlive = false;
            return;
        }
        if(checkCollision(gameObject)) {
            isAlive = false;
            return;
        }
            snakeParts.add(0, gameObject);
        if ((gameObject.x == apple.x) && (gameObject.y == apple.y))
            apple.isAlive = false;
        else
            removeTail();
    }

    public GameObject createNewHead() {
        GameObject gameObject = null;
        int headX = snakeParts.get(0).getX();
        int headY = snakeParts.get(0).getY();
        if (direction == Direction.LEFT) {
            gameObject = new GameObject(headX - 1, headY);
        } else if (direction == Direction.DOWN) {
            gameObject = new GameObject(headX, headY + 1);
        } else if (direction == Direction.UP) {
            gameObject = new GameObject(headX, headY - 1);
        } else if (direction == Direction.RIGHT) {
            gameObject = new GameObject(headX + 1, headY);
        }
        return gameObject;
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject){
        for(GameObject s : snakeParts)
        {
            if((s.x == gameObject.x) && (s.y == gameObject.y))
            {
            return true;
            }
        }
        return false;
    }
    public int getLength()
    {
        return snakeParts.size();
    }
    public void moveL(Lemon lemon) {
        GameObject gameObject = createNewHead();
        if (gameObject.x >= SnakeGame.WIDTH || gameObject.x < 0 || gameObject.y >= SnakeGame.HEIGHT || gameObject.y < 0) {
            isAlive = false;
            return;
        }
        if(checkCollision(gameObject)) {
            isAlive = false;
            return;
        }
        snakeParts.add(0, gameObject);
        if ((gameObject.x == lemon.x) && (gameObject.y == lemon.y))
            lemon.isAlive = false;
        else
            removeTail();
    }

}
