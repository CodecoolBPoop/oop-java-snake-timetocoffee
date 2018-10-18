package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.sqrt;
import static sun.audio.AudioPlayer.player;

// a simple enemy TODO make better ones.
public class ChasingEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    double direction;
    int speed = 1;

    public ChasingEnemy(Pane pane) {
        super(pane);

        setImage(Globals.chasingEnemy);

        Random rnd = new Random();
        pane.getChildren().add(this);
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            direction += 180;
            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        }
        try {
            GameEntity sneakhead = Globals.getSnakeHead();
            double length = sqrt((getX() * getX()) + getY() * getY()) / 2;
            Double headingSnakeHeadX = (sneakhead.getX() - getX()) / length;
            Double headingSnakeHeadY = (sneakhead.getY() - getY()) / length;
            setX(getX() + headingSnakeHeadX);
            setY(getY() + headingSnakeHeadY);
        } catch (NullPointerException e) {
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        }
    }


    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
