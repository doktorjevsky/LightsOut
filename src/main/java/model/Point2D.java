package model;

public class Point2D {

    private int x;
    private int y;


    public Point2D(){
        x = 0;
        y = 0;
    }

    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Point2D add(Point2D p1, Point2D p2){
        return new Point2D(p1.x + p2.x, p1.y + p2.y);
    }

    public static Point2D sub(Point2D p1, Point2D p2){
        return new Point2D(p1.x - p2.x, p1.y - p2.y);
    }

    public static boolean equals(Point2D p1, Point2D p2){
        return p1.x == p2.x && p1.y == p2.y;
    }
}
