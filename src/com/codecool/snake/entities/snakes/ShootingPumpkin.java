package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.ChasingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
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
    private Integer pumkinId = numberOfPumpkin++;
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

        if (isShotPumpkin) {
            speed= 5;
            Point2D heading = Utils.directionToVector(getRotate(), speed);
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());

            if (isOutOfBounds()) {
                this.destroy();
            }
        }
        try {
            GameEntity snakehead = Globals.getSnakeHead();
            if (isDraggedPumpkin && !isShotPumpkin) {
                setX(snakehead.getX());
                setY(snakehead.getY());
                setRotate(snakehead.getRotate());
            } else if (isShotPumpkin) {
                enemyDestroy();
            }
        } catch (Exception e) {
            speed = 0;
        }
    }

    public ShootingPumpkin(Pane pane) {
        super(pane);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        setImage(Globals.shootingPumpkin);
        pane.getChildren().add(this);
    }

    public void handleShoot(GameEntity enemy) {
        enemy.destroy();
        destroy();
    }

    public void enemyDestroy() {
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof ChasingEnemy || entity instanceof SimpleEnemy) {
                    handleShoot(entity);
                }
            }
        }
    }
}
