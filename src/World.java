
import Shapes.*;
import Shapes.Rectangle;
import Shapes.Shape;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Random;

/**
 * A representation of a world containing a set of moving shapes. NB! The worlds
 * y-axis points downward.
 *
 * @author Anders Lindström, anderslm@kth.se 2015-09-16
 */
public class World {

    private double width, height; // this worlds width and height

    private final Shape[] shapes; // an array of references to the shapes

    /**
     * Creates a new world, containing a pad and a set of balls. NB! The worlds
     * y-axis points downward.
     *
     * @param width  the width of this world
     * @param height the height of this worl
     */
    public World(double width, double height) {
        this.width = width;
        this.height = height;

        shapes = new Shape[14]; // an array of references

        // Create the actual Shapes.Shape objects (sub types)
        for (int i = 0; i < 6; i++) {
            Circle circle = new Circle();
            circle.setDiameter(10 + i * 2);
            circle.setFilled(true);
            circle.setVelocity(50 + 50 * i, -50 - 25 * i);
            circle.setColor(Color.CYAN);

            shapes[i] = circle;
        }

        Line line = new Line();
        line.setX2(50);
        line.setX(0);
        line.setY(0);
        line.setY2(50);
        line.setVelocity(100, 50);
        shapes[6] = line;

        //Used to not work
        Line line2 = new Line();
        line2.setX2(0);
        line2.setX(50);
        line2.setY(50);
        line2.setY2(0);
        line2.setVelocity(200, 100);
        shapes[7] = line2;


        for (int i = 8; i < 14; i++) {
            Rectangle rect = new Rectangle(10 + i * 2, 15 + i * 2);
            rect.setFilled(true);
            rect.setVelocity(new Random().nextInt(100)+10, new Random().nextInt(100)+10);
            rect.setColor(Color.GREEN);
            shapes[i] = rect;
        }
    }

    /**
     * Sets the new dimensions, in pixels, for this world. The method could be
     * used for example when the canvas is reshaped.
     *
     * @param newWidth
     * @param newHeight
     */
    public void setDimensions(double newWidth, double newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    /**
     * Move the world one step, based on the time elapsed since last move.
     *
     * @param elapsedTimeNs the elpsed time in nanoseconds
     */
    public void move(long elapsedTimeNs) {
        for (Shape shape : shapes) {
            shape.move(elapsedTimeNs);
            shape.constrain(0, 0, width, height);

            /*if (shape instanceof FillableShape)
                ((FillableShape) shape).setFilled(!((FillableShape) shape).isFilled());*/
        }
    }

    /**
     * Returns a copy of the list of ball references.
     * Due to the implementation of clone, a shallow copy is returned.
     *
     * @return a copy of the list of balls
     */
    public Shape[] getShapes() {
        return (Shape[]) shapes.clone();
    }
}
