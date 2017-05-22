package model;

import javafx.scene.paint.Color;

/**
 * Created by arono on 2017-05-21.
 */
public enum Status {
    OK (Color.LAWNGREEN), WARNING (Color.RED), ACTUAL (Color.BLUE), NONE (Color.GRAY);
    private final Color color;
    Status(Color color){
        this.color = color;
    }
    public Color getColor() { return color; }
}
