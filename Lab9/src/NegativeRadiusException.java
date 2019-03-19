public class NegativeRadiusException extends CircleException
{
    private double radius;

    public NegativeRadiusException(double radius)
    {
        super("zero radius");
        this.radius = radius;
    }

    public double radius()
    {
        return this.radius;
    }
}
