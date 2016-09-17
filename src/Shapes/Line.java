package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Martin on 2016-09-17.
 */
public class Line extends Shape {

    private double x2,y2;

    public Line(){
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
    public void paint(GraphicsContext gc){
        gc.setStroke(this.getColor());
        gc.strokeLine(getX(),getY(), getX2(), getY2());

    }

    @Override
    public void bounce(Shape[] shapes){
        return;
    }

    @Override
    public void constrain(
            double boxX, double boxY,
            double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
        if (getX() < boxX || getX2() < boxX)
            setVelocity(Math.abs(getDx()), getDy());
        else if (getX() > boxWidth  || getX2() > boxWidth)  //Compensate for the fact that getX is on left side of shape
            setVelocity(-Math.abs(getDx()), getDy());

        if (getY() < boxY || getY2() < boxY)
            setVelocity(getDx(), Math.abs(getDy()));
        else if (getY() > boxHeight || getY2() > boxHeight) //Compensate for the fact that getY is on top side of shape
            setVelocity(getDx(), -Math.abs(getDy()));

    }
}
