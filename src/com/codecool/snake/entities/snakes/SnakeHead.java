package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private int snakeId = Globals.numberOfSnakes+1;


    private static int numberOfDeadSnakes = 0;
    private int lengthOfTail = 0;

    public boolean isSnakeAlive = true;

    private boolean doDraggedPumpkin = false;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        Globals.numberOfSnakes ++;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }

    @Override
    public void setDoDraggedPumpkin(boolean doDraggedPumpkin) {
        this.doDraggedPumpkin = doDraggedPumpkin;
    }
    @Override
    public boolean isDoDraggedPumpkin() {
        return doDraggedPumpkin;
    }

    @Override
    public void step() {
        double dir = getRotate();
        if (snakeId == 1) {
            if (Globals.leftKeyDown) {
                dir = dir - turnRate;
            }
            if (Globals.rightKeyDown) {
                dir = dir + turnRate;
            }
        } else {
            if (Globals.aKeyDown) {
                dir = dir - turnRate;
            }
            if (Globals.sKeyDown) {
                dir = dir + turnRate;
            }
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            if (numberOfDeadSnakes == 1) {
                Globals.winnerByLifeLength = this.snakeId == 1 ? "Witch": "Wizard";
            }
            if (this.snakeId == 1) {
                Globals.lengthOfWitchSnakeId1 =this.lengthOfTail;
            } else {
                Globals.lengthOfWizardSnakeId2 =this.lengthOfTail;
            }
            this.destroy();
            for (SnakeBody body : SnakeBody.snakeBodies) {
                if (body.snakeId == this.snakeId) {
                    body.destroy();
                }
            }



            numberOfDeadSnakes ++;
            if (numberOfDeadSnakes == 2) {
                Globals.isGameover = true;


            }
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            newPart.snakeId = this.snakeId;
            SnakeBody.snakeBodies.add(newPart);
            tail = newPart;
            this.lengthOfTail++;

        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }



    public static void setNumberOfDeadSnakes(int numberOfDeadSnakes) {
        SnakeHead.numberOfDeadSnakes = numberOfDeadSnakes;
    }

//    public void addLengthOfTail(int lengthToAdd) {
//        lengthOfTail = lengthOfTail + lengthToAdd;
//    }


}
