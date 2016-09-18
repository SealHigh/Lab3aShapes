package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Created by Martin on 2016-09-16.
 */
public class Circle extends FillableShape {

    private double diameter;

    public Circle(){
        super();
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
        gc.setStroke(Color.BLACK);
        if(!isFilled())
            gc.strokeOval(getX(),getY(), diameter,diameter);
        else
            gc.fillOval(getX(),getY(), diameter,diameter);
    }

    public boolean contains(double x, double y){
        return (this.getX() < x && this.getY() < y &&
                this.getX() + this.diameter > x  &&
                this.getY() + this.diameter > y);
    }

    @Override
    public void bounce(Shape[] shapes){
        for (Shape shape : shapes) {

            if(shape instanceof Line){
                if (getDx() < 0 && getX() < shape.getX() && getX() > shape.getX()-diameter/2 && getY() < ((Line) shape).getY()) {
                    setVelocity(Math.abs(getDx()), getDy());
                    setFilled(!(this.isFilled()));
                    continue;
                }
                if (getDx() > 0 && getX()+diameter > shape.getX() && getX() < shape.getX()-diameter/2 && getY() < ((Line) shape).getY()) {
                    setVelocity(-Math.abs(getDx()), getDy());
                    setFilled(!(this.isFilled()));
                }
            }
        }
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
