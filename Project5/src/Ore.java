import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


final class Ore extends ActiveEntity
{

    public Ore(String id, List<PImage> images, Point position, int actionPeriod)
    {
        super(id, images, position, actionPeriod);
    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Point pos = position;

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        OreBlob blob =  new OreBlob(id + OreBlob.BLOB_ID_SUFFIX,
              imageStore.getImageList(OreBlob.BLOB_KEY), pos,
              actionPeriod / OreBlob.BLOB_PERIOD_SCALE, OreBlob.BLOB_ANIMATION_MIN +
                        ImageStore.rand.nextInt(OreBlob.BLOB_ANIMATION_MAX - OreBlob.BLOB_ANIMATION_MIN));

        world.addEntity(blob);
        blob.scheduleActions(scheduler, world, imageStore);
    }

}