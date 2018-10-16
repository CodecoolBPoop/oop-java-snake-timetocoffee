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

import static java.lang.Math.sqrt;

// a simple enemy TODO make better ones.
public class ChasingEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public ChasingEnemy(Pane pane) {
        super(pane);

        setImage(Globals.chasingEnemy);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
//            destroy();

        }

        GameEntity sneakhead = Globals.getSnakeHead();
        double length = sqrt((getX() * getX()) + getY() * getY())/2;
        Double headingSnakeHeadX = (sneakhead.getX()-getX())/ length;
        Double headingSnakeHeadY = (sneakhead.getY()-getY())/ length;
        setX(getX() + headingSnakeHeadX);
        setY(getY() + headingSnakeHeadY);
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
