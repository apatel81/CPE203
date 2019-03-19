import java.lang.Math;
import java.util.List;

public class Polygon
{
    private final List <Point> vertices;

    public Polygon(List<Point> vertices){
        this.vertices = vertices;
    }

    public List<Point> getPoints() {return this.vertices;}

    public double perimeter( ){
        double distance = 0.0;
        double xdist = 0.0;
        double ydist = 0.0;
        for (int i=0 ; i < this.vertices.size()-1 ; i++) {
            Point point1 = this.vertices.get(i);
            Point point2 = this.vertices.get(i+1);
            xdist = (point1.getX() - point2.getX()) * (point1.getX() - point2.getX());
            ydist = (point1.getY() - point2.getY()) * (point1.getY() - point2.getY());
            distance = distance + Math.sqrt(xdist+ydist);
        }

        Point lastpoint = this.vertices.get(this.vertices.size()-1);
        Point firstpoint = this.vertices.get(0);
        double finalxdist = (lastpoint.getX() - firstpoint.getX()) * (lastpoint.getX() - firstpoint.getX());
        double finalydist = (lastpoint.getY() - firstpoint.getY()) * (lastpoint.getY() - firstpoint.getY());
        distance = distance + Math.sqrt(finalxdist+finalydist);

        return distance;
    }
}
