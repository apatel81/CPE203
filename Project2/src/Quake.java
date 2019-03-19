import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class Quake implements ActiveEntity
{
    private String id;
    private int imageIndex;
    private List<PImage> images;
    private Point position;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;

    public final int QUAKE_ANIMATION_REPEAT_COUNT = 10;
    public static final String QUAKE_KEY = "quake";

    public Quake(String id, List<PImage> images, Point position, int resourceLimit, int resourceCount,
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

        scheduler.scheduleEvent((Entity)this,
                animation.createAnimationAction(QUAKE_ANIMATION_REPEAT_COUNT), this.getAnimationPeriod());
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents((Entity)this);
        world.removeEntity((Entity)this);
    }

}