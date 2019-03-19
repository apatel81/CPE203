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


public abstract class AnimatedEntity extends ActiveEntity
{
    protected int animationPeriod;
    protected int imageIndex;

    public AnimatedEntity(String id, List<PImage> images, Point position, int actionPeriod, int animationPeriod)
    {
        super(id, images, position, actionPeriod);
        this.animationPeriod = animationPeriod;
        this.imageIndex = 0;
    }

    public PImage getCurrentImage()
    {
        return images.get(this.imageIndex);
    }

    public int getAnimationPeriod()
    {
        return this.animationPeriod;
    }

    public void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        Activity activity = new Activity(this, world, imageStore, 0);
        Animation animation = new Animation(this, 0);
        scheduler.scheduleEvent(this, activity, actionPeriod );
        scheduler.scheduleEvent(this, animation, this.animationPeriod);
    }


}
