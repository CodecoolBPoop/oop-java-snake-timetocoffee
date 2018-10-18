package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.ChasingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.DoublePowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.ShootingPumpkin;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Game extends Pane {

    public Game() {
        new SnakeHead(this, 750, 500);
        new SnakeHead(this, 250, 500);

        for (int i = 0; i < 4; i++) {
            new SimpleEnemy(this);
            new SimplePowerup(this);
            new ShootingPumpkin(this);
            new DoublePowerup(this);
        }

        new ChasingEnemy(this);
        addButtons();
        setTableBackground(Globals.imageBackground);

    }

    public static void handleGameover() {
        System.out.println("Game Over");
        Globals.gameLoop.stop();
        showGameoverDialog();
    }

    private static String decideWinner() {
        if (Globals.lengthOfWitchSnakeId1 == Globals.lengthOfWizardSnakeId2) {
            return Globals.winnerByLifeLength;
       }
        return Globals.lengthOfWitchSnakeId1 > Globals.lengthOfWizardSnakeId2 ? "Witch": "Wizard";
    }

    private static void showGameoverDialog() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GameOver");
        alert.setHeaderText("The winner is:" + decideWinner());
        String s = "The length of snakes\nWitch: " + Globals.lengthOfWitchSnakeId1 +"\nWizard: " + Globals.lengthOfWizardSnakeId2;
        alert.setContentText(s);
        alert.show();

    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case X: Globals.spaceKeyDown = true; break;
                case A: Globals.aKeyDown = true; break;
                case S: Globals.sKeyDown = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case X: Globals.spaceKeyDown = false; break;
                case A: Globals.aKeyDown = false; break;
                case S: Globals.sKeyDown = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();


    }

    public void addButtons() {

        Button restartBtn = new Button("Restart");

        restartBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Restarted");
                restartGame();
            }
        });

        this.getChildren().add(restartBtn);
    }

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

    }

    public void restartGame() {


        for (GameEntity gameObject: Globals.gameObjects) {

            gameObject.destroy();
        }
        Globals.numberOfSnakes = 0;
        Globals.isGameover = false;

        SnakeHead.setNumberOfDeadSnakes(0);
        Game game = new Game();
        Globals.stage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        Globals.gameLoop.stop();
        game.start();

    }

}
