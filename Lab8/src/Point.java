public class Point
{
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point()
    {
        x = 1.0;
        y = 1.0;
        z = 1.0;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }
}
