package Shapes;

/**
 * Created by Martin on 2016-09-16.
 */
abstract public class FillableShape extends Shape {

    private boolean filled;

    protected FillableShape(){
        super();
        filled = true;
    }


    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
