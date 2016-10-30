// The "Wall" class.
/*
Programmed by: Tony Ng
Last Modified: 02/06/2014
Purpose: Defines Wall and their behaviours, inherits Blob
*/
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class Wall extends Blob
{
    public Wall(int x, int y, int xb, int yb)
    {
	tag = Consts.WALL;
	image = null;
	xLoc = x;
	yLoc = y;
	xDrawLoc = 0; //Nothing to draw, doesnt matter
	yDrawLoc = 0; //Nothing to draw, doesnt matter
	trueXBound = xb;
	trueYBound = yb;
	xBound1 = xLoc;
	xBound2 = trueXBound + xLoc;
	yBound1 = yLoc;
	yBound2 = trueYBound + yLoc;
	try
	{
	    image = ImageIO.read( new File( Consts.IMG_BLANK ) ); //Needs some image to print
	    //Walls are drawn on background because they dont move anyways
	}
	catch(Exception e){}
    };
    public void calculateTurn()
    {
	//Nothing here
    }
    public boolean onCollision (int id, int dir)
    {
	//No object will affect the wall
	return false;
    }
} // Wall class
