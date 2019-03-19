import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


final class Vein extends ActiveEntity
{

    public static final String ORE_ID_PREFIX = "ore -- ";
    public static final int ORE_CORRUPT_MIN = 20000;
    public static final int ORE_CORRUPT_MAX = 30000;


    public Vein(String id, List<PImage> images, Point position, int actionPeriod)
    {
        super(id, images, position, actionPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
      Optional<Point> openPt = world.findOpenAround(this.getPosition());

      if (openPt.isPresent())
      {
         Ore ore = new Ore(ORE_ID_PREFIX + id, imageStore.getImageList(world.ORE_KEY), openPt.get(),
                 ORE_CORRUPT_MIN + ImageStore.rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN)
                 );

         world.addEntity(ore);
         ore.scheduleActions(scheduler, world, imageStore);

      }

      Activity activity = new Activity(this, world, imageStore, 0);
      scheduler.scheduleEvent(this, activity, actionPeriod);
    }

}


