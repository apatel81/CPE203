import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class Knight extends AnimatedEntity {

    public static final String KNIGHT_ID_SUFFIX = " -- knight";
    public static final String KNIGHT_ID = "knight";
    public static final int KNIGHT_PERIOD_SCALE = 4;
    public static final String KNIGHT_KEY = "knight";
    public static final int KNIGHT_ANIMATION_MIN = 500;
    public static final int KNIGHT_ANIMATION_MAX = 1500;
    public static final int KNIGHT_ACTION_PERIOD = 750;
    public static final int KNIGHT_ANIMATION_PERIOD = 7500;

    public Knight(String id, List<PImage> images, Point position, int actionPeriod, int animationPeriod) {
        super(KNIGHT_ID, images, position, KNIGHT_ACTION_PERIOD, KNIGHT_ANIMATION_PERIOD);
    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> knightTarget = world.findNearest(position,
                new Dragon(id, images, this.getPosition(), actionPeriod, animationPeriod));

        long nextPeriod = actionPeriod;

        if (knightTarget.isPresent())
        {
            Point tgtPos = knightTarget.get().getPosition();

            if (moveTo(world, knightTarget.get(), scheduler))
            {
                Vein vein = new Vein(id, imageStore.getImageList("vein"), tgtPos, 0);

                world.addEntity(vein);
                nextPeriod += actionPeriod;
                vein.scheduleActions(scheduler, world, imageStore);

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

        else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }


}


