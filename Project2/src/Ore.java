import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class Ore implements ActiveEntity
{
    private String id;
    private int imageIndex;
    private List<PImage> images;
    private Point position;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;

    public static final String BLOB_KEY = "blob";
    public static final int BLOB_ANIMATION_MIN = 50;
    public static final int BLOB_ANIMATION_MAX = 150;


    public Ore(String id, List<PImage> images, Point position, int resourceLimit, int resourceCount,
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
        scheduler.scheduleEvent(this, activity.createActivityAction(world, imageStore), this.actionPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        OreBlob blob =  new OreBlob(this.id + OreBlob.BLOB_ID_SUFFIX,
              imageStore.getImageList(BLOB_KEY), this.position, this.resourceLimit, this.resourceCount,
              this.actionPeriod / OreBlob.BLOB_PERIOD_SCALE, BLOB_ANIMATION_MIN +
                        ImageStore.rand.nextInt(BLOB_ANIMATION_MAX - BLOB_ANIMATION_MIN));

        world.removeEntity((Entity)this);
        scheduler.unscheduleAllEvents((Entity)this);

        world.addEntity((Entity)blob);
        blob.scheduleActions(scheduler, world, imageStore);
    }


}