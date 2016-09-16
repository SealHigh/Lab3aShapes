package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Martin on 2016-09-16.
 */
public class Circle extends FillableShape {

    private double diameter;

    public Circle(double diameter){
        super();
        this.diameter = diameter;
    }


    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public void paint(GraphicsContext gc){
        gc.setFill(Color.RED);
        gc.setStroke(Color.GREEN);
        if(!isFilled())
            gc.strokeOval(getX(),getY(), diameter,diameter);
        else
            gc.fillOval(getX(),getY(), diameter,diameter);


    }

    @Override
    public void constrain(
            double boxX, double boxY,
            double boxWidth, double boxHeight) {
        // If outside the box - calculate new dx and dy
        if (getX() < boxX) {
            setVelocity(Math.abs(getDx()), getDy());
        } else if (getX() > boxWidth-diameter)  //Compensate for the fact that getX is on left side of shape
        {
            setVelocity(-Math.abs(getDx()), getDy());
        }
        if (getY() < boxY) {
            setVelocity(getDx(), Math.abs(getDy()));
        } else if (getY() > boxHeight-diameter) //Compensate for the fact that getY is on top side of shape
        {
            setVelocity(getDx(), -Math.abs(getDy()));
        }
    }
}
