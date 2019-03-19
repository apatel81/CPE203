
public class Bigger
{
   public static double whichIsBigger(Circle c, Rectangle r, Polygon p)
   {
       double circle_perimeter = Util.perimeter(c);
       double rectangle_perimeter = Util.perimeter(r);
       double polygon_perimeter = Util.perimeter(p);

       if (circle_perimeter > rectangle_perimeter && circle_perimeter > polygon_perimeter)
       {
           return circle_perimeter;
       }
       else if (rectangle_perimeter > circle_perimeter && rectangle_perimeter > polygon_perimeter)
       {
           return rectangle_perimeter;
       }
       else if (polygon_perimeter > circle_perimeter && polygon_perimeter > rectangle_perimeter)
       {
           return polygon_perimeter;
       }
   return 0.0;
   }
   
}
