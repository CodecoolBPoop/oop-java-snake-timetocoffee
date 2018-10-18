package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ShootingPumpkin extends GameEntity implements Animatable {

    private static Integer numberOfPumpkin = 0;
    private Integer pumkinId = numberOfPumpkin + 1;
    private int speed;
    private boolean isDraggedPumpkin = false;
    private boolean isShotPumpkin = false;

    public Integer getPumkinId() {
        return pumkinId;
    }

    public boolean isDraggedPumpkin() {
        return isDraggedPumpkin;
    }

    public void setDoDraggedPumpkin(boolean draggedPumpkin) {
        isDraggedPumpkin = draggedPumpkin;
    }

    public boolean isShotPumpkin() {
        return isShotPumpkin;
    }

    public void setShotPumpkin(boolean shotPumpkin) {
        isShotPumpkin = shotPumpkin;
    }

    @Override
    public void step() {
        try {
            GameEntity snakehead = Globals.getSnakeHead();
            if (isDraggedPumpkin) {
                setX(snakehead.getX());
                setY(snakehead.getY());
            } else if (isShotPumpkin) {
                flyingPumpkin(snakehead);
            }
        } catch (Exception e) {
            speed = 0;
        }
    }

    public void flyingPumpkin( GameEntity snakehead) {

        speed = 1;
        do {
            System.out.println("yeee");
            Point2D heading = Utils.directionToVector(snakehead.getRotate(), speed);
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        } while (this.isOutOfBounds());
    }

    public ShootingPumpkin(Pane pane) {
        super(pane);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        setImage(Globals.shootingPumpkin);
        pane.getChildren().add(this);
    }

}
