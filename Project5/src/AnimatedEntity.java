import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

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

    public boolean adjacent(Point p1, Point p2)
    {
        return (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) ||
                (p1.y == p2.y && Math.abs(p1.x - p2.x) == 1);
    }

    public Point nextPosition(WorldModel world, Point destPos)
    {
        PathingStrategy pathingStrategy = new AStarPathingStrategy();
        Predicate<Point> canPassThrough = point ->
                world.withinBounds(point) && !world.isOccupied(point);

        BiPredicate<Point, Point> withinReach = (point1, point2) ->
                adjacent(point1, point2);

        List<Point> points = pathingStrategy.computePath(position, destPos, canPassThrough,
                withinReach, PathingStrategy.CARDINAL_NEIGHBORS);

        if (points.size() == 0)
        {
            return position;
        }
        else
            return points.get(0);

    }

}
