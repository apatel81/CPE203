import java.lang.Math;


public class Util
{

    public static double perimeter(Circle circle){
        double answer = (2*Math.PI*circle.getRadius());
        return answer;
    }

    public static double perimeter(Rectangle rectangle){
        double xdist = Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()) * 2;
        double ydist = Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()) * 2;
        double perimeter = xdist + ydist;
        return perimeter;
    }

    public static double perimeter(Polygon polygon){
        double distance = 0.0;
        double xdist = 0.0;
        double ydist = 0.0;
        for (int i=0 ; i < polygon.getPoints().size()-1 ; i++) {
            Point point1 = polygon.getPoints().get(i);
            Point point2 = polygon.getPoints().get(i+1);
            xdist = (point1.getX() - point2.getX())*(point1.getX() - point2.getX());
            ydist = (point1.getY() - point2.getY())*(point1.getY() - point2.getY());
            distance = distance + Math.sqrt(xdist+ydist);
        }

        Point lastpoint = polygon.getPoints().get(polygon.getPoints().size()-1);
        Point firstpoint = polygon.getPoints().get(0);
        double finalxdist = (lastpoint.getX() - firstpoint.getX())*(lastpoint.getX() - firstpoint.getX());
        double finalydist = (lastpoint.getY() - firstpoint.getY())*(lastpoint.getY() - firstpoint.getY());
        distance = distance + Math.sqrt(finalxdist+finalydist);

        return distance;

    }

}
