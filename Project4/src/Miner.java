import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;

import com.sun.deploy.util.BlackList;
import processing.core.PImage;
import processing.core.PApplet;

public abstract class Miner extends AnimatedEntity
{
    protected int resourceLimit;
    protected int resourceCount;

    public Miner(String id, List<PImage> images, Point position, int actionPeriod, int animationPeriod,
                 int resourceLimit, int resourceCount)
    {
        super(id, images, position, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
    }

    public Point nextPositionMiner(WorldModel world, Point destPos)
    {
        int horiz = Integer.signum(destPos.x - this.getPosition().x);
        Point newPos = new Point(this.getPosition().x + horiz,
                this.getPosition().y);

        if (horiz == 0 || world.isOccupied(newPos))
        {
            int vert = Integer.signum(destPos.y - this.getPosition().y);
            newPos = new Point(this.getPosition().x,
                    this.getPosition().y + vert);

            if (vert == 0 || world.isOccupied(newPos))
            {
                newPos = this.getPosition();
            }
        }

        return newPos;
    }



}