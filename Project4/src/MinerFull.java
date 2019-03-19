import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;

import com.sun.deploy.util.BlackList;
import processing.core.PImage;
import processing.core.PApplet;


final class MinerFull extends Miner
{

    public MinerFull(String id, List<PImage> images, Point position, int actionPeriod,
                     int animationPeriod, int resourceLimit, int resourceCount)
    {
        super(id, images, position, actionPeriod, animationPeriod, resourceLimit, resourceCount);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Blacksmith blacksmith = new Blacksmith(id, images, position);

        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), blacksmith);

        if (fullTarget.isPresent() && moveTo(this, world, fullTarget.get(), scheduler))
        {
            transformFull(world, scheduler, imageStore);
        }
        else
        {
            Activity activity = new Activity(this, world, imageStore, 0);

            scheduler.scheduleEvent(this,
                 activity, actionPeriod);
        }
    }

    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        MinerNotFull miner = new MinerNotFull(id, images, position, actionPeriod,
                this.getAnimationPeriod(), resourceLimit,resourceCount);

        world.removeEntity( this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        miner.scheduleActions(scheduler, world, imageStore);
    }

    public boolean moveTo(Entity miner, WorldModel world, Entity target, EventScheduler scheduler)
   {
      if (this.getPosition().adjacent(target.getPosition()))
      {
         return true;
      }
      else
      {
         Point nextPos = nextPositionMiner(world, target.getPosition());

         if (!this.getPosition().equals(nextPos))
         {
            Optional<Entity> occupant = world.getOccupant(nextPos);
            if (occupant.isPresent())
            {
               scheduler.unscheduleAllEvents(occupant.get());
            }

            world.moveEntity(miner, nextPos);
         }
         return false;
      }
   }




}