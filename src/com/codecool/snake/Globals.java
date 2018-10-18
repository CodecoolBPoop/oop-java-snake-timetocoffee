package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;
    public static  Stage stage;

    public static Image snakeHead = new Image("Green Witch-48x48 (1).png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("Bat_small.png");
    public static Image chasingEnemy = new Image("ghost_halloween_23227 _128.png");
    public static Image powerupBerry = new Image("32426-candy-icon.png");
    public static Image imageBackground = new Image("Halloween_Haunted_Castle_Background_crop.png");
    public static Image shootingPumpkin = new Image("Evil Pumpkin-24x24.png");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean spaceKeyDown;
    public static boolean aKeyDown;
    public static boolean sKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;

    public static ArrayList<Integer> listPumpkinDraged;
    public static boolean isGameover = false;

    public static int lengthOfWitchSnake;
    public static int getLengthOfWizardSnake;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static GameEntity getSnakeHead() {
        for (GameEntity entity : gameObjects) {
            if (entity instanceof SnakeHead) {
                return entity;
            }
        }
        return null;
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
