package models;

public class Coordinates {
    private double x;
    private int y;

    public Coordinates(double x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
