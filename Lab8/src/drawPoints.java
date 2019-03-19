import processing.core.*;
import java.util.*;
import java.io.*;
import java.util.stream.*;


public class drawPoints extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup()
	{
		background(180);
		noLoop();
	}

	public void draw()
	{

		double x, y, z;
		List<Point> points = new ArrayList<>();

		String[] lines = loadStrings("positions.txt");

		println("there are " + lines.length);

		for (int i=0; i < lines.length; i++)
		{
			if (lines[i].length() > 0 )
			{
				String[] words= lines[i].split(",");

				x = Double.parseDouble(words[0]);
				y = Double.parseDouble(words[1]);
				z = Double.parseDouble(words[2]);

				println("xy: " + x + " " + y);

				Point newP = new Point(x, y, z);
				points.add(newP);
			}
		}

		List<Point> filteredPoints = points.stream()
				.filter(p -> p.getZ() <= 2.0)
				.map(p -> new Point(p.getX() / 2,
						            p.getY() / 2,
						            p.getZ() / 2))
				.map(p -> new Point( p.getX() - 150,
						             p.getY() - 37,
						             p.getZ() - 0))
				.collect(Collectors.toList());

		for (Point p : filteredPoints) {
			ellipse((int)(p.getX()), (int)(p.getY()), 1, 1);
		}

	}

	public static void main(String args[]) {
		PApplet.main("drawPoints");
	}
}
