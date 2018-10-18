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

    private Integer pumpkinId = 0;
    private List<Integer> listPumpkinDraged = new ArrayList<>();
    private int speed;


    public int getPumpkinId() {
        return pumpkinId;
    }

    public ShootingPumpkin(Pane pane) {
        super(pane);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        setImage(Globals.shootingPumpkin);
        pane.getChildren().add(this);
        pumpkinId++;
    }



    @Override
    public void step() {
        GameEntity snakehead = Globals.getSnakeHead();

        if (getBoundsInParent().intersects(snakehead.getBoundsInParent()) && !snakehead.isDoDraggedPumpkin()) {
            listPumpkinDraged.add(pumpkinId);
            setX(snakehead.getX());
            setY(snakehead.getY());
            if (Globals.spaceKeyDown) {
                snakehead.setDoDraggedPumpkin(true);
       //        listPumpkinDraged.remove(pumpkinId);

               flyingPumpkin(snakehead);
            }
        }
    }

    public void flyingPumpkin( GameEntity snakehead) {

        speed = 1;
        do {
            System.out.println("yeee");

            Point2D heading = Utils.directionToVector(snakehead.getRotate(), speed);
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
        } while (!this.isOutOfBounds());
    }

}
