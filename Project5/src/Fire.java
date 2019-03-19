import java.util.*;
import processing.core.PImage;

final class Fire extends AnimatedEntity
{

    public static final String FIRE_ID = "fire";
    public static final String FIRE_KEY = "fire";
    public static final int FIRE_ACTION_PERIOD = 1100;
    public static final int FIRE_ANIMATION_PERIOD = 100;
    public static final int FIRE_ANIMATION_REPEAT_COUNT = 10;

    public Fire(List<PImage> images, Point position)
    {
        super(FIRE_ID, images, position, FIRE_ACTION_PERIOD, FIRE_ANIMATION_PERIOD);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }


    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        Activity activity = new Activity(this, world, imageStore, 0);
        Animation animation = new Animation(this, FIRE_ANIMATION_REPEAT_COUNT);

        scheduler.scheduleEvent(this, activity, actionPeriod);

        scheduler.scheduleEvent(this, animation, getAnimationPeriod());
    }


}
