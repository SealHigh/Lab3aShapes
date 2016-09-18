package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Line2D;
import java.util.List;

/**
 * Created by Martin on 2016-09-16.
 */
public class Circle extends FillableShape {

    private double diameter;
    private Shape latestBounce;
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

    private Double[] getNormal(Double aX,Double aY,Double bX,Double bY){
        Double[] result = new Double[2];
        result[1] = bX-aX;
        result[0] = bY-aY;
        return result;
    }

    private Double dotProduct(Double aX,Double aY,Double bX,Double bY){
            Double result = aX * bX + aY*bY;
            return result;
    }

    @Override
    public void bounce(Shape[] shapes){
        for (Shape shape : shapes) {

            if(shape instanceof Line){
                Line2D line = new Line2D.Double(shape.getX(),shape.getY(),((Line) shape).getX2(),((Line) shape).getY2());
                if ((line.ptSegDist(getX(),getY())<3 || line.ptSegDist(getX()+diameter,getY()+diameter)<3) && shape != latestBounce) {
                    latestBounce = shape;
                    Double[] normal;

                    normal = getNormal(shape.getX(),shape.getY(),((Line) shape).getX2(),((Line) shape).getY2());
                    Double dotProduct = dotProduct(getDx(),getDy(),normal[0],normal[1]);
                    Double newDX = getDx()-(2*(dotProduct))/(Math.pow(normal[0],2)+(Math.pow(normal[1],2)))*normal[0];
                    Double newDY = getDy()-(2*(dotProduct))/(Math.pow(normal[0],2)+(Math.pow(normal[1],2)))*normal[1];

                    setVelocity(newDX, newDY);
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
            latestBounce = null;
            setVelocity(Math.abs(getDx()), getDy());
        } else if (getX() > boxWidth-diameter)  //Compensate for the fact that getX is on left side of shape
        {
            latestBounce = null;
            setVelocity(-Math.abs(getDx()), getDy());
        }
        if (getY() < boxY) {
            latestBounce = null;
            setVelocity(getDx(), Math.abs(getDy()));
        } else if (getY() > boxHeight-diameter) //Compensate for the fact that getY is on top side of shape
        {
            latestBounce = null;
            setVelocity(getDx(), -Math.abs(getDy()));
        }
    }
}
