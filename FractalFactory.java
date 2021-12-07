import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;

/**
* @author CS 1331 Instructors
* @version 42.0
*
* Static helpers which draw a Fractal Creation inside a Pane
*/
public class FractalFactory {

    /**
    * Method takes in a pane object and draws a square fractal that is centerd
    * on the pane's preffered width and height. The base of the fractal will
    * have a size between 30 and 150 and a reduction factor between .05 and .55.
    *
    * The maximum possible size of a Fractal is 500x500 pixels
    *
    * @param pane Pane object to which the fractal will be draw
    * @return integer with the number of rectangles in the fractal
    */
    public static int drawFractal(Pane pane) {
        int size = (int) ((Math.random() * 120) + 30);
        return drawFractal(0,
            size,
            (int) ((pane.getPrefWidth() / 2) - (size / 2)),
            (int) ((pane.getPrefHeight() / 2) - (size / 2)),
            0.5 * Math.random() + 0.05, pane);
    }


    /**
    * Helper function which utilizes recursion to draw a fractal.
    * Each recursive call creates a subsection of the fractal at a
    * different corner of the current rectangle with 95% probability.
    *
    * @param corner index representing the attatched corner of the previous square
    * @param size size of the current rectangle
    * @param x x-coord of the current rectangle
    * @param y y-coord of the current ractangle
    * @param factor rate at which the fractal shrinks
    * @param pane pane Object to which the fractal will be draw
    * @return integer with the number of rectangles in the fractal subsection
    */
    private static int drawFractal(int corner, int size, int x, int y, double factor, Pane pane) {
        if (size < 1) {
            return 0;
        } else {
            Rectangle rectangle = new Rectangle(x, y, size, size);
            pane.getChildren().addAll(rectangle);
            int newSize = (int) (size * factor);
            return 1
                + ((corner != -1 && Math.random() < .95)
                    ? drawFractal(1, newSize, x - newSize, y - newSize, factor, pane)
                    : 0)   // drawing top-left
                + ((corner != 2 && Math.random() < .95)
                    ? drawFractal(-2, newSize, x - newSize, y + size, factor, pane)
                    : 0)   // drawing bottom-left
                + ((corner != -2 && Math.random() < .95)
                    ? drawFractal(2, newSize, x + size, y - newSize, factor, pane)
                    : 0)   // drawing top-right
                + ((corner != 1 && Math.random() < .95)
                    ? drawFractal(-1, newSize, x + size, y + size, factor, pane)
                    : 0);  // drawing bottom-right
        }
    }
}
