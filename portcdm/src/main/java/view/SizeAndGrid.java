package view;

/**
 * Created by Oskar on 2017-05-03.
 */
public class SizeAndGrid {
    private static int sceneWidth = 1200;
    private static int sceneHeight = 768;
    private static int sceneTitleColumn = 30;
    private static int sceneTitleRow = 20;
    private static int choiceBoxColumn = 30;
    private static int choiceBoxRow = 30;
    private static int hBoxButtonColumn = 30;
    private static int hBoxButtonRow = 35;
    private static int backButtonColumn = 1;
    private static int backButtonRow = 3;


    public static int getSceneHeight() {
        return sceneHeight;
    }

    public static int getSceneWidth() {
        return sceneWidth;
    }

    public static int getSceneTitleColumn(){
        return sceneTitleColumn;
    }

    public static int getSceneTitleRow(){
        return sceneTitleRow;
    }

    public static int gethBoxButtonColumn(){
        return hBoxButtonColumn;
    }

    public static int gethBoxButtonRow(){
        return hBoxButtonRow;
    }

    public static int getChoiceBoxColumn(){
        return choiceBoxColumn;
    }

    public static int getChoiceBoxRow(){
        return choiceBoxRow;
    }

    public static int getBackButtonColumn(){
        return backButtonColumn;
    }

    public static int getBackButtonRow(){
        return backButtonRow;
    }
}
