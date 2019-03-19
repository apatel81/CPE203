import java.awt.*;
import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;


public class WorkSpace {

    private List<Shapes> shapes;

    public WorkSpace()
    {
        this.shapes = new ArrayList<Shapes>();

    }

    public void add(Shapes shape)
    {
        this.shapes.add(shape);
    }

    public Shapes get(int index)
    {
        return this.shapes.get(index);

    }

    public int size()
    {
        return this.shapes.size();
    }

    public List<Circle> getCircles()
    {
        List<Circle> circles = new ArrayList<>();

        for (Shapes a_shape : this.shapes)
        {
            if (a_shape instanceof Circle)
            {
                circles.add((Circle)a_shape);
            }
        }
        return circles;
    }

    public List<Rectangle> getRectangles()
    {
        List<Rectangle> rectangles = new ArrayList<>();

        for (Shapes a_shape : this.shapes)
        {
            if (a_shape instanceof Rectangle)
            {
                rectangles.add((Rectangle)a_shape);
            }
        }
        return rectangles;
    }

    public List<Triangle> getTriangles()
    {
        List<Triangle> triangles = new ArrayList<>();

        for (Shapes a_shape : this.shapes)
        {
            if (a_shape instanceof Triangle)
            {
                triangles.add((Triangle)a_shape);
            }
        }
        return triangles;
    }

    public List<Shapes> getShapesByColor(Color color)
    {
        List<Shapes> colors_list = new ArrayList<>();

        for (Shapes a_shape : this.shapes)
        {
            if (a_shape.getColor() == color)
            {
                colors_list.add((Shapes)a_shape);
            }
        }
        return colors_list;
    }

    public double getAreaOfAllShapes()
    {
        double sum = 0.0;

        for (Shapes a_shape : this.shapes)
        {
            sum += a_shape.getArea();
        }

        return sum;
    }

    public double getPerimeterOfAllShapes()
    {
        double sum = 0.0;

        for (Shapes a_shape : this.shapes)
        {
            sum += a_shape.getPerimeter();
        }

        return sum;
    }

}
