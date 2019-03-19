import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;

import com.sun.deploy.util.BlackList;
import processing.core.PImage;
import processing.core.PApplet;

public interface Miner extends ActiveEntity
{
    public Point nextPositionMiner(WorldModel world, Point destPos);

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);
}