import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class MinerNotFull implements Miner
{
    private String id;
    private int imageIndex;
    private List<PImage> images;
    private Point position;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;


    public MinerNotFull(String id, List<PImage> images, Point position, int resourceLimit, int resourceCount,
                     int actionPeriod, int animationPeriod)
    {
        this.id = id;
        this.imageIndex = 0;
        this.images = images;
        this.position = position;
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;

    }

    public void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    public PImage getCurrentImage()
    {
        return this.images.get(this.imageIndex);
    }

    public List<PImage> getImages()
    {
        return this.images;
    }

    public void setImages(List<PImage> i)
    {
        this.images = i;
    }

    public void setPosition(Point p)
    {
        this.position = p;
    }

    public Point getPosition()
    {
        return this.position;
    }

    public void setResourceLimit(int rL) {
        this.resourceLimit = rL;
    }

    public int getResourceLimit() {
        return this.resourceLimit;
    }

    public void setResourceCount(int rC) {
        this.resourceCount = rC;
    }

    public int getResourceCount() {
        return this.resourceCount;
    }

    public void setActionPeriod(int acP) {
        this.actionPeriod = acP;
    }

    public int getActionPeriod() {
        return this.actionPeriod;
    }

    public void setAnimationPeriod(int anP) {
        this.animationPeriod = anP;
    }

    public int getAnimationPeriod() {
        return this.animationPeriod;
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        Activity activity = new Activity(this, world, imageStore, 0);
        Animation animation = new Animation(this, world, imageStore, 0);

        scheduler.scheduleEvent((Entity)this, activity.createActivityAction(world, imageStore), this.actionPeriod);

        scheduler.scheduleEvent((Entity)this, animation.createAnimationAction(0), this.getAnimationPeriod());
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> notFullTarget = world.findNearest(this.getPosition(),
                  new Ore(this.id, this.images, this.position, 0, 0, this.actionPeriod, 0));

        if (!notFullTarget.isPresent() ||
              !moveTo(world, notFullTarget.get(), scheduler) ||
              !transformNotFull(world, scheduler, imageStore))
        {
             Activity activity = new Activity(this, world, imageStore, 0);
             scheduler.scheduleEvent((Entity)this,
                     activity.createActivityAction(world, imageStore),
                     this.actionPeriod);
         }
    }

    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        if (this.getResourceCount() >= this.getResourceLimit())
        {
            Entity miner =  (this.getPosition()).createMinerFull(this.id, this.resourceLimit,
                 this.actionPeriod, this.animationPeriod, this.images);

            world.removeEntity((Entity)this);
            scheduler.unscheduleAllEvents((Entity)this);

            world.addEntity((Entity)miner);
            ActiveEntity activeEntity = (ActiveEntity)miner;
            activeEntity.scheduleActions(scheduler, world, imageStore);

             return true;
        }

        return false;
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
      if (position.adjacent(target.getPosition()))
      {
         this.setResourceCount(this.getResourceCount()+ 1);
         world.removeEntity(target);
         scheduler.unscheduleAllEvents(target);

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

            world.moveEntity((Entity)this, nextPos);
         }
         return false;
      }
    }

   public Point nextPositionMiner(WorldModel world, Point destPos)
   {
      int horiz = Integer.signum(destPos.x - this.getPosition().x);
      Point newPos = new Point(this.getPosition().x + horiz,
              this.getPosition().y);

      if (horiz == 0 || world.isOccupied(newPos))
      {
         int vert = Integer.signum(destPos.y - this.getPosition().y);
         newPos = new Point(this.getPosition().x,
                 this.getPosition().y + vert);

         if (vert == 0 || world.isOccupied(newPos))
         {
            newPos = this.position;
         }
      }

      return newPos;
   }

}