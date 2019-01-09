import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Warning: Not for beginners !

/**
 * Canvas is a class to allow for simple graphical drawing on a canvas.
 * This is a modification of the general purpose Canvas, specially made for
 * the BlueJ "shapes" example. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version 2006.03.30, MOD:DB 2010.02.02
 */
public class Canvas
{
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static Canvas canvasSingleton;
    private static final int DIM = 300;
    private static final Rectangle2D R2D = new Rectangle( 0, 0, DIM, DIM );

    /**
     * Factory method to get the canvas singleton object.
     */
    public static Canvas getCanvas()
    {
        if(canvasSingleton == null) {
            canvasSingleton= new Canvas("BlueJ Shapes Demo", DIM, DIM, Color.white);
        }
        else {}
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    //  ----- instance part -----

    private JFrame       frame;
    private CanvasPane   canvas;
    private Graphics2D   graphic;
    private Color        backgroundColor;
    private Image        canvasImage;
    private List<Object> objects;
    private HashMap<Object, ShapeDescription> shapes;
    
    /**
     * Create a Canvas.
     * @param title    title to appear in Canvas Frame
     * @param width    the desired width for the canvas
     * @param height   the desired height for the canvas
     * @param bgColor the desired background color of the canvas
     */
    private Canvas(final String title, final Integer width, final Integer height, final Color bgColor)
    {
        frame=  new JFrame();
        canvas= new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColor= bgColor;
        frame.pack();
        objects= new ArrayList<Object>();
        shapes=  new HashMap<Object, ShapeDescription>();
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(final boolean visible)
    {
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background color
            Dimension size =canvas.getSize();
            canvasImage= canvas.createImage(size.width, size.height);
            graphic= (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        else {}
        frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  referenceObject  an object to define identity for this shape
     * @param  color            the color of the shape
     * @param  shape            the shape object to be drawn on the canvas
     */
     // Note: this is a slightly backwards way of maintaining the shape
     // objects. It is carefully designed to keep the visible shape interfaces
     // in this project clean and simple for educational purposes.
    public void draw(final Object referenceObject, final String color, final Shape shape)
    {
        objects.remove(referenceObject);   // just in case it was already there
        objects.add(referenceObject);      // add at the end
        shapes.put(referenceObject, new ShapeDescription(shape, color));
        redraw();
    }
 
    /**
     * Erase a given shape's from the screen.
     * @param  referenceObject  the shape object to be erased 
     */
    public void erase(final Object referenceObject)
    {
        objects.remove(referenceObject);   // just in case it was already there
        shapes.remove(referenceObject);
        redraw();
    }

    /**
     * Set the foreground color of the Canvas.
     * @param  newColor   the new color for the foreground of the Canvas 
     */
    public void setForegroundColor(final String colorString)
    {
        if(colorString.equals("red")) {
            graphic.setColor(Color.red);
        }
        else if(colorString.equals("black")) {
            graphic.setColor(Color.black);
        }
        else if(colorString.equals("blue")) {
            graphic.setColor(Color.blue);
        }
        else if(colorString.equals("yellow")) {
            graphic.setColor(Color.yellow);
        }
        else if(colorString.equals("green")) {
            graphic.setColor(Color.green);
        }
        else if(colorString.equals("magenta")) {
            graphic.setColor(Color.magenta);
        }
        else if(colorString.equals("white")) {
            graphic.setColor(Color.white);
        }
        else {
            graphic.setColor(Color.black);
        }
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(final Integer milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e)
        {
            // ignoring exception at the moment
        }
    }
    public void waitALittle()
    {
        try
        {
            this.wait(10);
        } 
        catch (Exception e)
        {
            // ignoring exception at the moment
        }
    }
    
    /**
     * Redraw ell shapes currently on the Canvas. MOD:DB
     */
    private void redraw()
    {
        erase();
        for(Object shape : objects) {
            ShapeDescription vSD = shapes.get(shape);
            vSD.draw(graphic);
            if ( ! vSD.intersects( R2D ) )  System.out.println( "objet hors cadre !" );
        }
        canvas.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    private void erase()
    {
        Color original= graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size= canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
    }


    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel
    {
        public void paint(final Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class ShapeDescription
    {
        private Shape shape;
        private String colorString;

        public ShapeDescription(final Shape shape, final String color)
        {
            this.shape=  shape;
            colorString= color;
        }

        public void draw(final Graphics2D graphic)
        {
            setForegroundColor(colorString);
            graphic.fill(shape);
        }
        
        public boolean intersects( final Rectangle2D pR )
        {
            return shape.intersects( pR );
        }
    }

}
