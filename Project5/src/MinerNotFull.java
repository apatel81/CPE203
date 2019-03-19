import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class MinerNotFull extends Miner
{
    public MinerNotFull(String id, List<PImage> images, Point position, int actionPeriod,
                        int animationPeriod, int resourceLimit, int resourceCount)
    {
        super(id, images, position, actionPeriod, animationPeriod, resourceLimit, resourceCount);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> notFullTarget = world.findNearest(this.getPosition(),
                  new Ore(id, images, this.getPosition(), actionPeriod));

        if (!notFullTarget.isPresent() ||
              !moveTo(world, notFullTarget.get(), scheduler) ||
              !transformNotFull(world, scheduler, imageStore))
        {
             Activity activity = new Activity(this, world, imageStore, 0);

             scheduler.scheduleEvent(this, activity, actionPeriod);
         }
    }

    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        if (resourceCount >= resourceLimit)
        {
            MinerFull miner =  new MinerFull(id, images, position,
                 actionPeriod, this.getAnimationPeriod(), resourceLimit ,resourceCount);

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(miner);
            miner.scheduleActions(scheduler, world, imageStore);

             return true;
        }

        return false;
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
      if (getPosition().adjacent(target.getPosition()))
      {
         resourceCount += 1;
         world.removeEntity(target);
         scheduler.unscheduleAllEvents(target);

         return true;
      }
      else
      {
         Point nextPos = nextPosition(world, target.getPosition());

         if (!this.getPosition().equals(nextPos))
         {
            Optional<Entity> occupant = world.getOccupant(nextPos);
            if (occupant.isPresent())
            {
               scheduler.unscheduleAllEvents(occupant.get());
            }

            world.moveEntity(this, nextPos);
         }
         return false;
      }
    }


}