// The "Blob" class.
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Blob //Shouldn't be created,
{
    public int xBound1, xBound2, yBound1, yBound2, xLoc, yLoc, tag, xDrawLoc, yDrawLoc;
    public double  xAccel, yAccel, xVel, yVel;
    protected BufferedImage image;
    protected int trueXBound, trueYBound;
    //public Blob(){}
    public BufferedImage draw(){ return image;}
    
    public boolean onCollision(int id, int dir){System.out.println("Hit");return true;} //Boolean indicates if deleted
    
    public void calculateTurn()
    {
	//Nothing Here
    }
} // Blob class
