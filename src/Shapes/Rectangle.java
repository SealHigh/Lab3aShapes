package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Line2D;

/**
 * Created by timothy on 16/09/16.
 */
public class Rectangle extends FillableShape {

    private double width, height;
    private Shape latestBounce;

    public Rectangle(double width, double height) {
        super();

        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void bounce(Shape[] shapes){
        for (Shape shape : shapes) {

            if(shape instanceof Line){
                Line2D line = new Line2D.Double(shape.getX(),shape.getY(),((Line) shape).getX2(),((Line) shape).getY2());
                if ((line.ptSegDist(getX(),getY())<3 || line.ptSegDist(getX()+width,getY()+height)<3) && shape != latestBounce) {
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
    public void paint(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        if(!isFilled())
            gc.strokeRect(getX(), getY(), width, height);
        else
            gc.fillRect(getX(), getY(), width, height);

    }

    @Override
    public void constrain(double boxX, double boxY, double boxWidth, double boxHeight) {

        double tmpGetDx = getDx();
        double tmpGetDy = getDy();

        if(getX() < boxX)
            setVelocity(Math.abs(getDx()), getDy());
        else if(getX() > boxWidth - width)
            setVelocity(-Math.abs(getDx()), getDy());

        if(getY() < boxY)
            setVelocity(getDx(), Math.abs(getDy()));
        else if(getY() > boxHeight - height)
            setVelocity(getDx(), -Math.abs(getDy()));

        if(tmpGetDx != getDx() || tmpGetDy != getDy())
            latestBounce = null;
    }
}
