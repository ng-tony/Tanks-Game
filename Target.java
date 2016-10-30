// The "Wall" class.
/*
Programmed by: Tony Ng
Last Modified: 02/06/2014
Purpose: Defines Target and their behaviours, inherits Blob
*/
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class Target extends Blob
{
    public Target(int x, int y)
    {
	tag = Consts.TARGET;
	xLoc = x;
	yLoc = y;
	xDrawLoc = xLoc;
	yDrawLoc = yLoc;
	trueXBound = Consts.TARGETXSIZE;
	trueYBound = Consts.TARGETYSIZE;
	xBound1 = xLoc;
	xBound2 = trueXBound + xLoc;
	yBound1 = yLoc;
	yBound2 = trueYBound + yLoc;
	try
	{
	    image = ImageIO.read( new File( Consts.IMG_TARGET ) ); //Gets image
	}
	catch(Exception e){}
    };
    public void calculateTurn()
    {
	//Nothing here
	//No change in position or velocity e.t.c
    }
    public boolean onCollision (int id, int dir)
    {
	if(id == Consts.TANK || id == Consts.BULLET) //If hit by Tank or bullet
	{
	    return true; //Delete
	}
	else 
	{
	    return false; //Do nothing
	}
    }
} // Wall class
