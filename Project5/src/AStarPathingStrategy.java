import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AStarPathingStrategy implements PathingStrategy
{

    public boolean withinBounds(Point pos)
    {
        return pos.y >= 0 && pos.y < 30 && pos.x >= 0 && pos.x < 40;
    }


    public ArrayList<Point> getNeighbors(Point point)
    {
        ArrayList<Point> neighbors = new ArrayList<>();

        Point p1 = new Point(point.x + 1, point.y);
        if (withinBounds(p1))
        {
            neighbors.add(p1);
        }

        Point p2 = new Point(point.x - 1, point.y);
        if (withinBounds(p2))
        {
            neighbors.add(p2);
        }

        Point p3 = new Point(point.x, point.y + 1);
        if (withinBounds(p3))
        {
            neighbors.add(p3);
        }

        Point p4 = new Point(point.x, point.y - 1);
        if (withinBounds(p4))
        {
            neighbors.add(p4);
        }

        return neighbors;
    }


    public HashMap<Point, ArrayList<Point>> cameFromList(HashMap<Point, ArrayList<Point>> cameFrom, Point parent, Point newP)
    {
        ArrayList<Point> previous = new ArrayList<>();

        if (cameFrom.get(parent) != null)
        {
            for (Point p : cameFrom.get(parent))
            {
                previous.add(p);
            }
        }

        previous.add(newP);
        cameFrom.put(parent, previous);
        return cameFrom;
    }


    public ArrayList<Point> constructPath(HashMap<Point, ArrayList<Point>> cameFrom, Point current)
    {
        ArrayList<Point> total = new ArrayList<>();
        total.add(current);

        while (cameFrom.get(current) != null)
        {
            Point parent = cameFrom.get(current).get((cameFrom.get(current)).size()-1);
            if (parent != null)
            {
                total.add(0, parent);
                current = parent;
            }
        }

        total.remove(0);
        return total;
    }


    @Override
    public List<Point> computePath(Point start, Point end, Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach, Function<Point, Stream<Point>> potentialNeighbors)
    {

        ArrayList<Point> open_list = new ArrayList<>();
        ArrayList<Point> closed_list = new ArrayList<>();

        open_list.add(start);

        HashMap<Point, ArrayList<Point>> cameFrom = new HashMap<>();

        start.set_gValue(0);
        start.set_fValue(start.HeuristicDistance(end));

        while(!(open_list.isEmpty()))
        {
            Point smallest_point = open_list.get(0);

            for (Point point : open_list)
            {
                if (point.fValue() <= smallest_point.fValue())
                {
                    smallest_point = point;
                }
            }

            Point current = smallest_point;

            int smallest_point_idx = open_list.indexOf(smallest_point);
            open_list.remove(smallest_point_idx);
            closed_list.add(current);

            List<Point> neighbors = potentialNeighbors.apply(current)
                    .filter(canPassThrough)
                    .filter(p -> !p.equals(start) &&
                                 !closed_list.contains(p))
                    .collect(Collectors.toList());

            for (Point neighbor : neighbors)
            {
                if (getNeighbors(neighbor).contains(end))
                {
                    cameFromList(cameFrom, neighbor, current);

                    return constructPath(cameFrom, neighbor);

                }

                int currentGScore = current.gValue() + (int)(Math.sqrt(current.distanceSquared(current, neighbor)));

                if(!(open_list.contains(neighbor)))
                {
                    open_list.add(neighbor);
                }
                else if (currentGScore >= neighbor.gValue())
                {
                    continue;
                }

                cameFromList(cameFrom, neighbor, current);
                neighbor.set_gValue(currentGScore);
                neighbor.set_fValue(currentGScore + neighbor.HeuristicDistance(end));

            }

        }


        return constructPath(cameFrom, end);
    }
}





















