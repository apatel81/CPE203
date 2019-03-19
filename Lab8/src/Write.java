import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Write
{
    public static void main(String args[])
    {
        BufferedWriter print_writer;
        try
        {
            File input = new File("positions.txt");
            File output = new File("drawMe.txt");
            Scanner scanner = new Scanner(input);

            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNextLine())
            {
                String string = scanner.nextLine();
                lines.add(string);
            }

            try
            {
                print_writer = new BufferedWriter(new FileWriter("drawMe.txt"));

                double x, y, z;
                ArrayList<Point> points = new ArrayList<>();

                for (int i = 0; i < lines.size(); i++)
                {
                    if (lines.get(i).length() > 0)
                    {
                        String[] dataPoints = lines.get(i).split(",");
                        x = Double.parseDouble(dataPoints[0]);
                        y = Double.parseDouble(dataPoints[1]);
                        z = Double.parseDouble(dataPoints[2]);

                        Point newPoint = new Point(x, y, z);
                        points.add(newPoint);

                        for (Point p : points)
                        {
//                            System.out.println(p);
                            print_writer.write("xy: " + p.getX() + " " + p.getY());
                            print_writer.newLine();
                        }
                    }
                }
                print_writer.close();
            }

            catch (IOException e)
            {
                System.err.println("Input error");
            }

        }

        catch (FileNotFoundException e)
        {
            System.err.println("File not found");
        }

    }

}

