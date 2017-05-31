package model;

import javafx.scene.paint.Color;

/**
 * Stores the different Status enums and their color codes.
 */
public enum Status {
    OK (Color.LAWNGREEN), WARNING (Color.RED), ACTUAL (Color.BLUE), NONE (Color.GRAY);
    private final Color color;
    Status(Color color){
        this.color = color;
    }

    /**
     * Gets the color for the Status
     *
     * @return a javaFX Color object
     */
    public Color getColor() { return color; }
}
