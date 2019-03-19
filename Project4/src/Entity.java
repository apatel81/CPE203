import java.util.List;
import java.util.Optional;
import java.util.*;
import processing.core.PImage;

public abstract class Entity
{

   protected String id;
   protected List<PImage> images;
   protected Point position;

   public Entity(String id, List<PImage> images, Point position)
   {
      this.id = id;
      this.images = images;
      this.position = position;
   }


   protected PImage getCurrentImage()
   {
      return this.images.get(0);
   }

   protected void setPosition(Point p)
   {
      this.position = p;
   }

   protected Point getPosition()
   {
      return this.position;
   }


}

