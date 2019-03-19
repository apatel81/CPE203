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

}       
