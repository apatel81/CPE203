import java.util.List;
import java.util.Optional;
import java.util.*;
import processing.core.PImage;

public interface Action
{
   public void executeAction(EventScheduler scheduler);
}

