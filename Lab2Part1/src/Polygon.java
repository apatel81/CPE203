import java.lang.Math;
import java.util.List;

public class Polygon
{
    private final List <Point> vertices;

    public Polygon(List<Point> vertices){
        this.vertices = vertices;
    }

    public List<Point> getPoints() {return this.vertices;}

}
