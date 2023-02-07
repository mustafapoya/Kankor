package net.golbarg.kankor.model;


import javafx.stage.Screen;

public class Position {
    public enum CORNER {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    private CORNER corner;
    private double stageWidth;
    private double stageHeight;
    private Screen screen;

    /* These Properties will store the Full Screen Width and Height: 1920.0*1080.0 */
    private double screenWidth;
    private double screenHeight;

    /* These Properties will store the Usable Size (Visual Bounds) of Screen width and height: 1920.0*1032.0,
     *  For more info: the space where taskbar is using is not usable and its subtracted from 1080 */
    private double screenVbWidth;
    private double screenVbHeight;

    public Position(CORNER corner, double stageWidth, double stageHeight) {
        this.corner = corner;
        this.stageWidth = stageWidth;
        this.stageHeight = stageHeight;

        screen = Screen.getPrimary();

        screenWidth = screen.getBounds() .getWidth();
        screenHeight = screen.getBounds() .getHeight();

        screenVbWidth = screen.getVisualBounds().getWidth();
        screenVbHeight = screen.getVisualBounds().getHeight();
    }

    public double getTaskbarHeight() {
        return screenHeight - screenVbHeight;
    }

    public double getXLocation() {
        System.out.println("getXLocation: " + (screenVbWidth - stageWidth));
        return screenVbWidth - stageWidth;
    }

    public double getYLocation() {
        System.out.println("getYLocation: " + (screenVbHeight - stageHeight));
        return screenVbHeight - stageHeight;
    }

    public static double getTaskbarHeight2() {
        final var screen = Screen.getPrimary();

        final var screenVbWidth = screen.getVisualBounds().getWidth();
        final var screenVbHeight = screen.getVisualBounds().getHeight();

        final var screenWidth = screen.getBounds() .getWidth();
        final var screenHeight = screen.getBounds() .getHeight();

        System.out.println("Screen Visual Bounds......... w*h.: " + screenVbWidth + '*' + screenVbHeight);
        System.out.println("Screen Bounds................ w*h.: " + screenWidth + '*' + screenHeight);
        return (screenHeight - screenVbHeight);
    }
}

