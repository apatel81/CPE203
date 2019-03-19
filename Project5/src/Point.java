import processing.core.PImage;

import java.util.List;
import java.util.Optional;

final class Point
{
   public final int x;
   public final int y;

   public int infinity = 912455232;
   private int g = infinity;
   private int h;
   private int f = infinity;


   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public String toString()
   {
      return "(" + x + "," + y + ")";
   }

   public boolean equals(Object other)
   {
      return other instanceof Point &&
         ((Point)other).x == this.x &&
         ((Point)other).y == this.y;
   }

   public boolean adjacent(Point p2)
   {
      return (this.x == p2.x && Math.abs(this.y - p2.y) == 1) ||
              (this.y == p2.y && Math.abs(this.x - p2.x) == 1);
   }

   public int distanceSquared(Point p1, Point p2)
   {
      int deltaX = p1.x - p2.x;
      int deltaY = p1.y - p2.y;

      return deltaX * deltaX + deltaY * deltaY;
   }

   public Optional<Entity> nearestEntity(List<Entity> entities)
   {
      if (entities.isEmpty())
      {
         return Optional.empty();
      }
      else
      {
         Entity nearest = entities.get(0);
         int nearestDistance = distanceSquared(nearest.getPosition(), this);

         for (Entity other : entities)
         {
            int otherDistance = distanceSquared(other.getPosition(), this);

            if (otherDistance < nearestDistance)
            {
               nearest = other;
               nearestDistance = otherDistance;
            }
         }

         return Optional.of(nearest);
      }
   }

   public int hashCode()
   {
      int result = 17;
      result = result * 31 + x;
      result = result * 31 + y;
      return result;
   }


   public int gValue()
   {
      return g;
   }

   public void set_gValue(int new_g)
   {
      this.g = new_g;
   }

   public int hValue()
   {
      return h;
   }

   public void set_hValue(int new_h)
   {
      this.h = new_h;
   }

   public int fValue()
   {
      return f;
   }

   public void set_fValue(int new_f)
   {
      this.f = new_f;
   }

   public int HeuristicDistance(Point point)
   {
      return Math.abs(point.y - this.y) + Math.abs(point.x - this.x);
   }


}
