
import Shapes.Circle;
import Shapes.FillableShape;
import Shapes.Line;
import Shapes.Shape;

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
     * @param width the width of this world
     * @param height the height of this worl
     */
    public World(double width, double height) {
        this.width = width;
        this.height = height;

        shapes = new Shape[4]; // an array of references

        // Create the actual Shapes.Shape objects (sub types)
        Circle circle = new Circle();
        circle.setDiameter(25);
        circle.setFilled(true);
        circle.setVelocity(400,-100);
        shapes[0] = circle;

        Circle circle2 = new Circle();
        circle2.setDiameter(15);
        circle2.setFilled(false);
        circle2.setVelocity(400,-300);
        shapes[1] = circle2;

        Line line = new Line();
        line.setX2(200);
        line.setX(200);
        line.setVelocity(0,100);
        shapes[2] = line;

        Line line2 = new Line();
        line2.setX2(100);
        line2.setX(100);
        line2.setVelocity(0,200);
        shapes[3] = line2;
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
        // alterantive loop: for(Shapes.Shape s : shapes) { ...
        for (int i = 0; i < shapes.length; i++) {
            shapes[i].move(elapsedTimeNs);
            shapes[i].constrain(0, 0, width, height);
            if(shapes[i] instanceof Circle)
              shapes[i].bounce(shapes);
        }
        System.out.println(width + ", " + height);
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
