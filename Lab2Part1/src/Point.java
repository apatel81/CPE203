import java.lang.Math;


public class Point
{

   private double x1;
   private double y1;
   
   public Point(double x, double y)
   {
     x1 = x;
     y1 = y;
   }

   public double getX()
   {
     return x1;
   }

   public double getY()
   {
     return y1;
   }

   public double getRadius()
   {
     // c = sqrt(a**2 + b**2)
     return Math.sqrt(x1*x1 + y1*y1);
   }

   public double getAngle()
   {
     // tangent(opposite=y / adjacent=x)
     return Math.atan2(y1, x1);
   }

   public Point rotate90()
   {
     // (x,y) --> (-y,x)
     double y2 = (-1 * getY());
     Point newpoint = new Point(y2, getX());
     return newpoint;
   }


}
     
