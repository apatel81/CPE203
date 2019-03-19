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

public interface ActiveEntity extends Entity
{
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

}