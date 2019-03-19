import java.util.List;
import java.util.Optional;
import java.util.*;
import processing.core.PImage;

public interface Entity
{
   public void nextImage();
   public PImage getCurrentImage();
   public List<PImage> getImages();
   public void setImages(List<PImage> Images);

   public void setPosition(Point pos);
   public Point getPosition();

   public void setResourceLimit(int resourceLimit);
   public int getResourceLimit();

   public void setResourceCount(int resourceCount);
   public int getResourceCount();

   public void setActionPeriod(int actionPeriod);
   public int getActionPeriod();

   public void setAnimationPeriod(int animationPeriod);
   public int getAnimationPeriod();
}

