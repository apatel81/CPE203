import java.awt.*;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Rectangle implements Shapes {

    private double width;
    private double height;
    private Point topLeft;
    private Color color;

    public Rectangle(double width, double height, Point topLeft, Color color)
    {
        this.width = width;
        this.height = height;
        this.topLeft = topLeft;
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public double getArea()
    {
        return this.width * this.height;
    }

    public double getPerimeter()
    {
        return ((2 * this.width) + (2 * this.width));
    }

    public void translate(Point p)
    {
        this.topLeft.translate(p.x, p.y);
    }

    public double getWidth()
    {
        return this.width;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public double getHeight()
    {
        return this.height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public Point getTopLeft()
    {
        return this.topLeft;
    }

    public boolean equals(Object other)
    {
        if (other == null)
            return false;

        if (getClass() != other.getClass())
            return false;

        Rectangle r = (Rectangle) other;

        return this.width == r.width && this.height == r.height && this.topLeft.equals(r.topLeft)
                && this.color.equals(r.color);
    }

}
