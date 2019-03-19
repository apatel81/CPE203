import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class OreBlob implements ActiveEntity
{
    private String id;
    private int imageIndex;
    private List<PImage> images;
    private Point position;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;

    public static final String BLOB_ID_SUFFIX = " -- blob";
    public static final int BLOB_PERIOD_SCALE = 4;

    public static final String QUAKE_KEY = "quake";

    public OreBlob(String id, List<PImage> images, Point position, int resourceLimit, int resourceCount,
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
        Optional<Entity> blobTarget = world.findNearest(this.getPosition(),
                new Vein(this.id, this.images, this.position, this.resourceLimit, this.resourceCount,
                        this.actionPeriod, this.animationPeriod));

        long nextPeriod = this.actionPeriod;

        if (blobTarget.isPresent())
        {
            Point tgtPos = blobTarget.get().getPosition();

            if (moveToOreBlob(world, blobTarget.get(), scheduler))
            {
                ActiveEntity quake = tgtPos.createQuake(imageStore.getImageList(QUAKE_KEY));

                world.addEntity((Entity)quake);
                nextPeriod += this.actionPeriod;
                quake.scheduleActions(scheduler, world, imageStore);
            }
        }

        Activity activity = new Activity(this, world, imageStore, 0);
        scheduler.scheduleEvent((Entity)this, activity.createActivityAction(world, imageStore), nextPeriod);

    }

    public boolean moveToOreBlob(WorldModel world, Entity target, EventScheduler scheduler)
    {
        if (this.position.adjacent(target.getPosition()))
        {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        }
        else
        {
            Point nextPos = nextPositionOreBlob(world, target.getPosition());

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

    public Point nextPositionOreBlob(WorldModel world, Point destPos)
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