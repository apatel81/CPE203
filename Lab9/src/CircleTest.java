public class CircleTest
{
    public static void main(String[] args)
    {
        try
        {
            Circle c1 = new Circle(-5);
            System.out.println(c1);
        }
        catch (CircleException e)
        {
            System.out.println(e.getMessage());
            return;
        }

        finally
        {
            System.out.println("In finally.");
        }

        System.out.println("Done.");
    }
}

