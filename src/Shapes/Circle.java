package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Martin on 2016-09-16.
 */
public class Circle extends FillableShape {

    private double diameter;

    public Circle() {
        super();
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public void paint(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.setStroke(Color.BLACK);
        if (!isFilled())
            gc.strokeOval(getX(), getY(), diameter, diameter);
        else
            gc.fillOval(getX(), getY(), diameter, diameter);
    }

    @Override
    public void constrain(double boxX, double boxY, double boxWidth, double boxHeight) {
        super.constrain(boxX, boxY, boxWidth - diameter, boxHeight - diameter);
    }
}
