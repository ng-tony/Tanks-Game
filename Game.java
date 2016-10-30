// The "Game" class
/*
Programmed by: Tony Ng
Last Modified: 02/06/2014
Purpose: Stores all the blobs in the game, runs a thread to calculate new values of those blobs, draws the game to send to applet, handles collisions
*/
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Game implements Runnable
{
    private ArrayList data = new ArrayList();
    private ArrayList databuffer = new ArrayList();
    private BufferedImage background, image;
    public boolean end = false;
    public Thread t;
    public Game(String b)
    {
	try
	{
	    background = ImageIO.read( new File( b ) );
	}
	catch(Exception e){};
	t = new Thread(this);
	image = background;
    }
    public void start()
    {
	t.start();
    }
    
    public void addObject(Blob obj)
    {
	databuffer.add(obj);
    }
    public void checkCollide(int i, int j)
    {
	
	int dir = 0;
	
	Object o = data.get(i);
	Object o1 = data.get(j);
	Blob s = (Blob)o;
	Blob d = (Blob)o1;
	/*System.out.print(s.xBound1);
	System.out.print(' ');
	System.out.print(s.xBound2);
	System.out.print(' ');
	System.out.print(s.yBound1);
	System.out.print(' ');
	System.out.println(s.yBound2);
	
	System.out.print(s.xBound1);
	System.out.print('-');
	System.out.print(s.xBound2);
	System.out.print('-');
	System.out.print(s.yBound1);
	System.out.print('-');
	System.out.println(s.yBound2);*/
	/*if(s.xBound1 < d.xBound2 && s.xBound1 > d.xBound1) //left collide
	{
	    if(s.yBound1 < d.yBound2 && s.yBound1 > d.yBound1) //up collide
	    {
		if ((s.xBound1 - d.xBound2) > (s.yBound1 - d.yBound2))
		    dir = Consts.XCOLLIDE;
		else dir = Consts.YCOLLIDE;
		System.out.println("Left Up");
	    }
	    else if(s.yBound2 < d.yBound2 && s.yBound2 > d.yBound1) //down collide
	    {
		 if ((s.xBound1 - d.xBound2) > (s.yBound2 - d.yBound1))
		    dir = Consts.XCOLLIDE;
		else dir = Consts.YCOLLIDE;
		System.out.println("Left Down");
	    }
	}
	else if (s.xBound2 < d.xBound2 && s.xBound2 > d.xBound1) //right
	{
	    if(s.yBound1 < d.yBound2 && s.yBound1 > d.yBound1) //up collide
	    {
		 if ((s.xBound2 - d.xBound1) > (s.yBound1 - d.yBound2))
		    dir = Consts.XCOLLIDE;
		else dir = Consts.YCOLLIDE;
		System.out.println("Right Up");
	    }
	    else if(s.yBound2 < d.yBound2 && s.yBound2 > d.yBound1) //down collide
	    {
		 if ((s.xBound2 - d.xBound1) > (s.yBound2 - d.yBound1))
		    dir = Consts.XCOLLIDE;
		else dir = Consts.YCOLLIDE;
		System.out.println("Right Down");
	    }
	}
	else if(d.xBound1 < s.xBound2 && d.xBound1 > s.xBound1) //left collide
	{
	    if(d.yBound1 < s.yBound2 && d.yBound1 > s.yBound1) //up collide
	    {
		if ((d.xBound1 - s.xBound2) > (d.yBound1 - s.yBound2))
		    dir = Consts.XCOLLIDE;
		else dir = Consts.YCOLLIDE;
		System.out.println("Left Up");
	    }
	    else if(d.yBound2 < s.yBound2 && d.yBound2 > s.yBound1) //down collide
	    {
		 if ((d.xBound1 - s.xBound2) > (d.yBound2 - s.yBound1))
		    dir = Consts.XCOLLIDE;
		else dir = Consts.YCOLLIDE;
		System.out.println("Left Down");
	    }
	}
	else if (d.xBound2 < s.xBound2 && d.xBound2 > s.xBound1) //right
	{
	    if(d.yBound1 < s.yBound2 && d.yBound1 > s.yBound1) //up collide
	    {
		 if ((d.xBound2 - s.xBound1) > (d.yBound1 - s.yBound2))
		    dir = Consts.XCOLLIDE;
		else dir = Consts.YCOLLIDE;
		System.out.println("Right Up");
	    }
	    else if(d.yBound2 < s.yBound2 && d.yBound2 > s.yBound1) //down collide
	    {
		 if ((d.xBound2 - s.xBound1) > (d.yBound2 - s.yBound1))
		    dir = Consts.XCOLLIDE;
		else dir = Consts.YCOLLIDE;
		System.out.println("Right Down");
	    }
	}*/
	if(!(s.xBound1 > d.xBound2 || s.xBound2 < d.xBound1))
	{
	    if(!(s.yBound1 > d.yBound2 || s.yBound2 < d.yBound1)) //Collide is yes
	    {
		//Determine Direction
		if( Math.abs(s.xBound1 - d.xBound2) > Math.abs(s.xBound2 - d.xBound1) ) //Left or Right
		{
		    if( Math.abs(s.yBound1 - d.yBound2) > Math.abs(s.yBound2 - d.yBound1)) //Up Or down
		    {
			if(Math.abs(s.xBound2 - d.xBound1) < Math.abs(s.yBound2 - d.yBound1))
			{
			    dir = Consts.LEFTCOLLIDE;
			}
			else
			{
			    dir = Consts.UPCOLLIDE;
			}
		    } 
		    else
		    {
			if( Math.abs(s.xBound2 - d.xBound1) < Math.abs(s.yBound1 - d.yBound2) )
			{
			    dir = Consts.LEFTCOLLIDE;
			}
			else
			{
			    dir = Consts.DOWNCOLLIDE;
			}
		    } 
		}
		else
		{
		    if( Math.abs(s.yBound1 - d.yBound2) > Math.abs(s.yBound2 - d.yBound1) )
		    {
			if (Math.abs(s.xBound1 - d.xBound2) < Math.abs(s.yBound2 - d.yBound1) )
			{
			    dir = Consts.RIGHTCOLLIDE;
			}
			else
			{
			    dir = Consts.UPCOLLIDE;
			}
		    }
		    else
		    {
			if (Math.abs(s.xBound1 - d.xBound2) < Math.abs(s.yBound1 - d.yBound2) )
			{
			    dir = Consts.RIGHTCOLLIDE;
			}
			else
			{
			    dir = Consts.DOWNCOLLIDE;
			}
		    }   
		}
	    }
	}
	if(dir > 0)
	{
	    boolean delS = false;
	    boolean delD = false;
	    //System.out.println(d.tag);
	    //System.out.println(data.size());
	    if(s.onCollision(d.tag, dir))
	    {
		delS = true;
	    }
	    if (d.onCollision(s.tag, dir))
	    {
		delD = true;
	    }
	    if(delS)
	    {
		s = null;
		data.remove(i);
		if(delD)
		{
		    d = null;
		    if(i < j)
		    {
			data.remove(j-1);
		    }
		    else
		    {
			data.remove(j);
		    }
		}
	    }
	    else if(delD)
	    {
		d = null;
		data.remove(j);
	    }
	    //System.out.println(data.size());
	}
    }
    public void turn()
    {
	data.addAll(databuffer);
	databuffer.clear();
	Iterator it = data.iterator();
	//System.out.println(data.size());
	while(it.hasNext())
	{
	    Object obj = it.next();
	    Blob s = (Blob)obj;
	    s.calculateTurn();
	    if(s.xLoc > Consts.XSCREENSIZE || s.xLoc < 0 || s.yLoc > Consts.YSCREENSIZE || s.yLoc < 0)
	    {
		s = null;
		it.remove();
	    }
	    //System.out.println(data.size());
	}
	/*it = data.iterator();
	while(it.hasNext())
	{
	    Object obj = it.next();
	    Blob s = (Blob)obj;
	    Iterator it2 = it;
	    it2.next();
	    while(it2.hasNext())
	    {
		Object obj2 = it2.next();
		Blob d = (Blob)obj;
		checkCollide(s, d, it, it2);
	    }
	}*/
	for(int i = 0; i < data.size(); i++)
	{
	    for(int j = i; j < data.size(); j++)
	    {
		if(j!= i)
		{
		    checkCollide(i , j);
		}
	    }
	}
	
	BufferedImage img = new BufferedImage(Consts.XSCREENSIZE, Consts.YSCREENSIZE, BufferedImage.TYPE_INT_ARGB);
	Graphics g = img.createGraphics();
	g.drawImage(background, 0, 0, null);
	it = data.iterator();
	while(it.hasNext())
	{
	    Object obj = it.next();
	    Blob s = (Blob)obj;
	    /*System.out.print(s.tag);
	    System.out.print(" ");
	    System.out.print(s.xLoc);
	    System.out.print(" ");
	    System.out.println(s.yLoc);*/
	    g.drawImage(s.draw(), s.xDrawLoc, s.yDrawLoc, null);
	}
	image = img;
	g.dispose();
    }
    public void run() 
    {
	while(true)
	{
	    try{Thread.sleep(Consts.CALCFPS);}catch(Exception e){};
	    turn();
	    /*if (this and that)
	    {
		this.end = true;
	    }*/
	}
	
    }
    public BufferedImage draw()
    {
	return image;
    }
} // Game class
