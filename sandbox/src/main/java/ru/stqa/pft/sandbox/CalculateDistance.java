package ru.stqa.pft.sandbox;

/**
 * Created by Doroh on 19.11.2019.
 */
public class CalculateDistance {
    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        System.out.println("Расстояние между точками равно: " + distance(p1, p2));
        System.out.println("Расстояние между точками равно: " + p1.distance(p2));

    }
    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }
}
