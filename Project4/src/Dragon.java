import java.util.*;
import processing.core.PImage;

public class Dragon extends AnimatedEntity
{
    public static final String DRAGON_ID_SUFFIX = " -- dragon";
    public static final int DRAGON_PERIOD_SCALE = 4;
    public static final String DRAGON_KEY = "dragon";
    public static final int DRAGON_ANIMATION_MIN = 250;
    public static final int DRAGON_ANIMATION_MAX = 1000;

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

    public Point nextPosition(WorldModel world, Point destPos)
    {
        int horiz = Integer.signum(destPos.x - this.getPosition().x);
        Point newPos = new Point(this.getPosition().x + horiz, this.getPosition().y);

        Optional<Entity> occupant = world.getOccupant(newPos);

        if (horiz == 0 || (occupant.isPresent() && !(occupant.get() instanceof Ore)))
        {
            int vert = Integer.signum(destPos.y - this.getPosition().y);
            newPos = new Point(this.getPosition().x, this.getPosition().y + vert);
            occupant = world.getOccupant(newPos);

            if (vert == 0 || (occupant.isPresent() && !(occupant.get() instanceof Ore)))
            {
                newPos = this.getPosition();
            }
        }

        return newPos;
    }
}
