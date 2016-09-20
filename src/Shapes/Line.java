package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Martin on 2016-09-17.
 */
public class Line extends Shape {

    private double x2, y2;

    public Line() {
        super();
        this.x2 = 0;
        this.y2 = 0;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    @Override
    public void paint(GraphicsContext gc) {
        gc.setStroke(this.getColor());
        gc.strokeLine(getX(), getY(), getX2(), getY2());
    }

    @Override
    public void move(long elapsedTimeNs) {
        setX(getX() + getDx() * elapsedTimeNs / BILLION);
        setY(getY() + getDy() * elapsedTimeNs / BILLION);
        setX2(getX2() + getDx() * elapsedTimeNs / BILLION);
        setY2(getY2() + getDy() * elapsedTimeNs / BILLION);
    }

    @Override
    public void constrain(double boxX, double boxY, double boxWidth, double boxHeight) {
        double width = Math.abs(getX2() - getX());
        double height = Math.abs(getY2() - getY());
        double centerX = getX2()/2 + getX()/2;
        double centerY = getY2()/2 + getY()/2;
        Rectangle rect = new Rectangle(width, height);
        rect.setX(centerX - width/2); rect.setY(centerY - height/2);
        rect.setVelocity(getDx(), getDy());
        rect.constrain(boxX, boxY, boxWidth, boxHeight);
        setVelocity(rect.getDx(), rect.getDy());
    }
}