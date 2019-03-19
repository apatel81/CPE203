import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.*;
import processing.core.PImage;
import processing.core.PApplet;


public class Obstacle extends Entity
{
    public Obstacle(String id, List<PImage> images, Point position)
    {
        super(id, images, position);
    }

}