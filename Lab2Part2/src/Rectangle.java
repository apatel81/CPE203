import java.lang.Math;

public class Rectangle
{
    private final Point topleft;
    private final Point bottomright;

    public Rectangle(Point topleft, Point bottomright){
        this.topleft = topleft;
        this.bottomright = bottomright;
    }

    public Point getTopLeft() {return this.topleft;}
    public Point getBottomRight() {return this.bottomright;}

    public double perimeter(){
        double xdist = Math.abs(this.topleft.getX() - this.bottomright.getX()) * 2;
        double ydist = Math.abs(this.topleft.getY() - this.bottomright.getY()) * 2;
        double perimeter = xdist + ydist;
        return perimeter;
    }

}
