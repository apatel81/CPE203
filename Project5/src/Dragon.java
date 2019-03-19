import java.util.*;
import processing.core.PImage;

public class Dragon extends AnimatedEntity
{

    public Dragon(String id, List<PImage> images, Point position, int actionPeriod, int animationPeriod)
    {
        super(id, images, position, actionPeriod, animationPeriod);
    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Point pos = position;

        Optional<Entity> dragonTarget = world.findNearest(position,
                new MinerNotFull(id, images, this.getPosition(), actionPeriod, animationPeriod ,0, 0));

        long nextPeriod = actionPeriod;

        if (dragonTarget.isPresent())
        {
            Point tgtPos = dragonTarget.get().getPosition();

            if (moveTo(world, dragonTarget.get(), scheduler))
            {
                Knight knight =  new Knight(Knight.KNIGHT_ID + Knight.KNIGHT_ID_SUFFIX,
                        imageStore.getImageList(Knight.KNIGHT_KEY), pos,
                        actionPeriod / Knight.KNIGHT_PERIOD_SCALE, Knight.KNIGHT_ANIMATION_MIN +
                        ImageStore.rand.nextInt(Knight.KNIGHT_ANIMATION_MAX - Knight.KNIGHT_ANIMATION_MIN));

                world.addEntity(knight);
                nextPeriod += actionPeriod;
                knight.scheduleActions(scheduler, world, imageStore);

            }
        }

        Activity activity = new Activity(this, world, imageStore, 0);
        scheduler.scheduleEvent(this, activity, nextPeriod);

    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (this.getPosition().adjacent(target.getPosition()))
        {
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
