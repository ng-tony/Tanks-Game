// The "Tank" class.
import java.awt.*;
import hsa.Console;
import java.awt.image.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.AffineTransform;

public class Tank extends Blob //implements Runnable
{
    public int angle;
    private int fireCooldown = 0;
    private Game game;
    boolean commands[];
    Thread t;
    public Tank(int x, int y, int ang, Game g, String img)
    {
	tag = Consts.TANK;
	commands = new boolean[5];
	for(int i = 0; i < 5; i++)
	{
	    commands[i] = false;
	}
	trueXBound = Consts.TANKXBOUND;
	trueYBound = Consts.TANKYBOUND;
	xLoc = x;
	yLoc = y;
	angle = ang;
	game = g;
	try
	{
	    image = ImageIO.read( new File( img ) );
	}
	catch(Exception e){};
	/*t = new Thread(this);
	t.start();*/
    }
    public void sendCommand(int command)
    {
	commands[command] = true;
	
    }
    public void revokeCommand(int command)
    {
	commands[command] = false;
    }
    public void calculateControl()
    {
	if (commands[0])
	{
	    yAccel = Math.sin (Math.toRadians(angle)) * Consts.TANKACCELERATE;
	    xAccel = Math.cos (Math.toRadians(angle)) * Consts.TANKACCELERATE;
	}
	if (commands[1])
	{
	    angle -= Consts.TANKTURNRATE;
	    //System.out.println(angle);
	}
	if (commands[2])
	{
	    yAccel = -1 * (Math.sin (Math.toRadians(angle)) * Consts.TANKACCELERATE);
	    xAccel = -1 * (Math.cos (Math.toRadians(angle)) * Consts.TANKACCELERATE);
	}
	if (commands[3])
	{
	    angle += Consts.TANKTURNRATE;
	    //System.out.println(angle);
	}
	if (commands[4])
	{
	    
	    if(fireCooldown == 0)
	    {
		game.addObject(new Bullet( (int)(Consts.XTANKGUN + xLoc + Math.round( Math.cos(Math.toRadians(angle)) * (double)Consts.XTANKGUNLENGTH)),
					   (int)(Consts.YTANKGUN + yLoc + Math.round( Math.sin(Math.toRadians(angle)) * (double)Consts.YTANKGUNLENGTH)),
					    angle)
			       );
		fireCooldown = Consts.RATEOFFIRE;
	    }
	}
    }
    public void calculateTurn()
    {
	if(fireCooldown > 0)
	{
	    fireCooldown --;
	}
	calculateControl();
	if(Math.round(xVel) != 0.0 && !commands[0] && !commands[2])
	{
	    //System.out.println("XVel" + xVel);
	    //xAccel = -1.0 * Consts.TANKACCELERATE * xVel/Math.abs(xVel);
	    xAccel = -1.0 * xVel/Consts.TANKFRICTION;
	}
	else if(xAccel != 0.0 && !commands[0] && !commands[2])
	{
	    xAccel = 0.0;
	    xVel = 0.0;
	}
	if(Math.round(yVel) != 0.0 && !commands[0] && !commands[2])
	{
	    //System.out.println("yVel" + yVel);
	    //yAccel = -1.0 * Consts.TANKACCELERATE * yVel/Math.abs(yVel);
	    yAccel = -1.0 * yVel/Consts.TANKFRICTION;
	}
	else if(yAccel != 0.0 && !commands[0] && !commands[2])
	{
	    yAccel = 0.0;
	    yVel = 0.0;
	}
	/*xAccel = xAccel - Consts.TANKFRICTION; 
	yAccel = yAccel - Consts.TANKFRICTION;*/
	xLoc += xVel;
	yLoc += yVel;
	if(xVel < Consts.TANKMAXVEL && xVel > -Consts.TANKMAXVEL && (commands[0] || commands[2]))
	{
	    xVel += xAccel;
	}
	else
	{
	    xVel += xAccel;
	}
	if(yVel < Consts.TANKMAXVEL && yVel > -Consts.TANKMAXVEL && (commands[0] || commands[2]))
	{
	    yVel += yAccel;
	}
	else
	{
	    yVel += yAccel;
	}

	xBound1 = xLoc;
	xBound2 = trueXBound + xLoc;
	yBound1 = yLoc;
	yBound2 = trueYBound + yLoc;
	xDrawLoc = xLoc - 20;
	yDrawLoc = yLoc - 20;
    }
    public boolean onCollision(int id, int dir)
    {
	//System.out.println("Tank Collide");
	//System.out.println(id);
	if(id == Consts.WALL)
	{ 
	    if(dir == Consts.LEFTCOLLIDE)
	    {
		xLoc += Consts.COLLIDEPUSH;
		xAccel = 0;
		xVel = Consts.WALLSLOW * xVel;
		//System.out.println("Left");
	    }
	    else if(dir == Consts.RIGHTCOLLIDE)
	    {
		xLoc -= Consts.COLLIDEPUSH;
		xAccel = 0;
		xVel = Consts.WALLSLOW * xVel;
		//System.out.println("Right");
	    }
	    else if(dir == Consts.UPCOLLIDE)
	    {
		yLoc += Consts.COLLIDEPUSH;
		yAccel = 0;
		yVel = Consts.WALLSLOW * yVel;
		//System.out.println("Up");
	    }
	    else if(dir == Consts.DOWNCOLLIDE)
	    {
		yLoc -= Consts.COLLIDEPUSH;
		yAccel = 0;
		yVel = Consts.WALLSLOW * yVel;
		//System.out.println("Down");
	    }
	    return false;
	}
	else if(id == Consts.BULLET)
	{
	    //Draw Explosion
	    return true;
	}
	else if(id == Consts.BULLETNOCOLLIDE)
	{
	    //Fresh bullet (still within firing tank's collision box
	    return false;
	}
	else if(id == Consts.TARGET)
	{
	    if(dir == Consts.LEFTCOLLIDE)
	    {
		xAccel = xAccel * 0.25;
		xVel = 0.25 * xVel;
		//System.out.println("Left");
	    }
	    else if(dir == Consts.RIGHTCOLLIDE)
	    {
		xAccel = xAccel * 0.25;
		xVel = 0.25 * xVel;
		//System.out.println("Right");
	    }
	    else if(dir == Consts.UPCOLLIDE)
	    {
		yAccel = yAccel * 0.25;
		yVel = 0.25 * yVel;
		//System.out.println("Up");
	    }
	    else if(dir == Consts.DOWNCOLLIDE)
	    {
		yAccel = yAccel * 0.5;
		yVel = 0.25 * yVel;
		//System.out.println("Down");
	    }
	    return false;
	}
	return false;
    }
    public BufferedImage draw()
    {
	
	BufferedImage rot = new BufferedImage((Consts.TANKXBOUND*2), (Consts.TANKYBOUND*2), BufferedImage.TYPE_INT_ARGB);
	Graphics g = rot.createGraphics();
	AffineTransform xform = new AffineTransform(); 
	xform.rotate(Math.toRadians(angle), Consts.TANKXBOUND, Consts.TANKYBOUND);
	AffineTransformOp op = new AffineTransformOp(xform,AffineTransformOp.TYPE_BILINEAR); 
	/*BufferedImage d = new BufferedImage((Consts.TANKXBOUND*2), (Consts.TANKYBOUND*2), BufferedImage.TYPE_INT_ARGB);
	d = op.filter(image, null);*/
	g.drawImage(op.filter(image, null), 0, 0, null);
	g.dispose();
	return rot;
    }
    
    /*public void run()
    {
	while(true)
	{
	    try{Thread.sleep(Consts.CALCFPS);}catch(Exception e){};
	    this.calculateControl();
	}
    }*/
    
} // Tank class
