package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sun.misc.OSEnvironment;

import java.util.ArrayList;

/**
 * Created by timothy on 16/09/16.
 */
public class Rectangle extends FillableShape {

    private double width, height;

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
    public void paint(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.setStroke(Color.BLACK);
        if (!isFilled())
            gc.strokeRect(getX(), getY(), width, height);
        else
            gc.fillRect(getX(), getY(), width, height);
    }

    @Override
    public void constrain(double boxX, double boxY, double boxWidth, double boxHeight) {
        super.constrain(boxX, boxY, boxWidth - width, boxHeight - height);
    }
}
