import java.awt.*;
//import java.util.List;
import java.lang.Math;

public class Circle implements Shapes {

    private double radius;
    private Point center;
    private Color color;

    public Circle (double radius, Point center, Color color)
    {
        this.radius = radius;
        this.center = center;
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }

    public void setColor(Color c)
    {
        this.color = c;
    }

    public double getArea()
    {
        return Math.PI * (this.radius * this.radius);
    }

    public double getPerimeter()
    {
        return 2 * Math.PI * this.radius;
    }

    public void translate(Point p)
    {
        this.center.translate(p.x, p.y);
    }




    public double getRadius()
    {
        return this.radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public Point getCenter()
    {
        return this.center;
    }

    public boolean equals(Object other)
    {
        if (other == null)
            return false;

        if (getClass() != other.getClass())
            return false;

        Circle c = (Circle) other;

        return this.radius == c.radius && this.center.equals(c.center) && this.color.equals(c.color);
    }

}
