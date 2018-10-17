package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected boolean doDraggedPumpkin;
    protected boolean isOutOfBounds;
    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH || getX() < 0 ||
            getY() > Globals.WINDOW_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    public void setDoDraggedPumpkin(boolean doDraggedPumpkin) {
        this.doDraggedPumpkin = doDraggedPumpkin;
    }

    public boolean isDoDraggedPumpkin() {
        return doDraggedPumpkin;
    }
}
