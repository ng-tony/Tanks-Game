// The "Bullet" class.
/*
Programmed by: Tony Ng
Last Modified: 02/06/2014
Purpose: Defines Bullet and their behaviours, inherits Blob
*/

import java.awt.*;
import hsa.Console;
import java.io.*;
import javax.imageio.*;

public class Bullet extends Blob
{
    
    private int bounce = 0;
    public int bulletCd = Consts.BULLETCOOLDOWN; //When the bullet can start killing tanks (avoids bullet killing firing tank when just being fired
    public Bullet (int x, int y, int angle)
    {
	tag = Consts.BULLETNOCOLLIDE;
	//Find the velocity
	yVel = (Math.sin (Math.toRadians(angle))) * Consts.BULLETVELOCITY; 
	xVel = (Math.cos (Math.toRadians(angle))) * Consts.BULLETVELOCITY;
	
	xLoc = x;
	yLoc = y;
	
	trueXBound = Consts.XBULLETSIZE;
	trueYBound = Consts.YBULLETSIZE;
	
	xBound1 = xLoc;
	xBound2 = trueXBound + xLoc;
	yBound1 = yLoc;
	yBound2 = trueYBound + yLoc;
	try
	{
	    image = ImageIO.read( new File( Consts.IMG_BULLET ) );
	}
	catch(Exception e){};
    }
    public void calculateTurn()
    {
	//Frictions?
	//Limitors (may be included in frictions
	if(bulletCd > 0)
	{
	    bulletCd --;
	}
	else
	{
	    tag = Consts.BULLET;
	}
	xLoc += xVel;
	yLoc += yVel;
	xDrawLoc = xLoc;
	yDrawLoc = yLoc;
	xBound1 = xLoc;
	xBound2 = trueXBound + xLoc;
	yBound1 = yLoc;
	yBound2 = trueYBound + yLoc;
	
    }


    public boolean onCollision (int id, int dir)
    {
	//System.out.println("Bullet Collide");
	if(id == Consts.WALL)
	{ 
	    //System.out.println(bounce);
	    //Maximum number of bounces
	    if (bounce < Consts.MAX_BOUNCE)
	    {
		if(dir <= Consts.RIGHTCOLLIDE)
		{                
		    xVel = xVel * -1; //Reflect X
		}
		else
		{
		    yVel = yVel * -1; //Reflect Y
		}
		bounce++; //Bounce count increase
	    }
	    else
		return true; //Bounced 5 times -- Delete itself
	    return false;
	}
	else if(id == Consts.TANK)
	{
	    if(bulletCd == 0) //If bullet is not freshly shot from tank 
	    {
		//Draw Explosion
		return true;
	    }
	    else 
	    {
		return false;
	    }
	}
	else if(id == Consts.TARGET)
	{
	    return true; //Destroy itself if hits target
	}
	return false; //Defaults to do nothing
    }
} // Bullet class
