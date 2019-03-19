import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;

import com.sun.deploy.util.BlackList;
import processing.core.PImage;
import processing.core.PApplet;

public abstract class ActiveEntity extends Entity
{
    protected int actionPeriod;

    public ActiveEntity(String id, List<PImage> images, Point position, int actionPeriod)
    {
        super(id, images, position);
        this.actionPeriod = actionPeriod;
    }

    protected void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        Activity activity = new Activity(this, world, imageStore, 0);
        scheduler.scheduleEvent(this, activity, actionPeriod );
    }

    protected abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

//    public int getActionPeriod()
//    {
//        return this.actionPeriod;
//    }
}