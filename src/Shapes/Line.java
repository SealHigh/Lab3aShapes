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
        fixLine();
        setX(getX() + getDx() * elapsedTimeNs / BILLION);
        setY(getY() + getDy() * elapsedTimeNs / BILLION);
        setX2(getX2() + getDx() * elapsedTimeNs / BILLION);
        setY2(getY2() + getDy() * elapsedTimeNs / BILLION);
    }

    /**
     * Ugly hack to make sure constrain works by never
     * allowing X be larger than X2
     */
    private void fixLine(){
        if(getX() > getX2()){
            double tempX = getX();
            setX(getX2());
            setX2(tempX);
        }
        if(getY() > getY2()){
            double tempY = getY();
            setY(getY2());
            setY2(tempY);
        }

    }
    @Override
    public void constrain(double boxX, double boxY, double boxWidth, double boxHeight) {
        super.constrain(boxX, boxY, boxWidth - (getX2()-getX()), boxHeight - (getY2()-getY()));
    }
}
