import java.awt.*;
import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.lang.Math;

public class Triangle implements Shapes {

    private Point a;
    private Point b;
    private Point c;
    private Color color;

    public Triangle(Point a, Point b, Point c, Color color)
    {
        this.a = a;
        this.b = b;
        this.c = c;
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
        return .5 * (Math.abs(a.x * (b.y - c.y) + b.x * (a.y - c.y) + c.x * (a.y - b.y)));
    }

    public double getPerimeter()
    {
        return  Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2)) +
                Math.sqrt(Math.pow((b.x - c.x), 2) + Math.pow((b.y - c.y), 2)) +
                Math.sqrt(Math.pow((c.x - a.x), 2) + Math.pow((c.y - a.y), 2));
    }

    public void translate(Point p)
    {
        this.a.translate(p.x, p.y);
        this.b.translate(p.x, p.y);
        this.c.translate(p.x, p.y);
    }

    public Point getVertexA() {
        return this.a;
    }

    public Point getVertexB() {
        return this.b;
    }

    public Point getVertexC() {
        return this.c;
    }

    public boolean equals(Object other) {
        if (other == null)
            return false;

        if (getClass() != other.getClass())
            return false;

        Triangle t = (Triangle) other;

        return this.a.equals(t.a) && this.b.equals(t.b) && this.c.equals(t.c) && this.color.equals(t.color);
    }
}







