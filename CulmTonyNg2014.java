// The "CulmTonyNg2014" class.
/*
Programmed by: Tony Ng
Last Modified: 02/06/2014
Purpose: Main Class, creates applet, and menus,
	 Explaination of program can be found at User Manual
*/
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.net.*;
import java.io.*;
import java.awt.image.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Container;
import java.awt.FlowLayout;
public class CulmTonyNg2014 extends Applet implements KeyListener, ActionListener, Runnable
{
    int menu = Consts.MENUMAIN;
    Button buttonPlay, buttonOptions, buttonCredits, buttonExit, buttonMain, buttonOptions2, buttonControls,
	   buttonPlay_Practice, buttonPlay_Two, buttonPlay_EndGame;
    Button buttonControlHandles[] = {new Button ("Change Key P1_1"), new Button ("Change Key P1_2"), new Button ("Change Key P1_3"),
				     new Button ("Change Key P1_4"), new Button ("Change Key P1_5"), new Button ("Change Key P2_1"),
				     new Button ("Change Key P2_2"), new Button ("Change Key P2_3"), new Button ("Change Key P2_4"), 
				     new Button ("Change Key P2_5")
				    };
    String controlLabels[];
    Game game;
    public Thread t;
    Keys keys1, keys2;
    Tank tank1 = null, tank2 = null;
    boolean changeKey1 = false, changeKey2 = false, controlLabelsShow = false, gameGo = false;
    int keyChange = 0;
    private Image background;
    
    public void init ()
    {
	
	resize (Consts.XSCREENSIZE, Consts.YSCREENSIZE);
	addKeyListener (this);
	setFocusable(true);
	setLayout (null);
	//initiateKeys();

	keys1 = new Keys(Consts.KEYFILE1);
	keys2 = new Keys(Consts.KEYFILE2);
	
	controlLabels = new String[10];
	for(int i = 0; i < 5; i++)
	{
	    controlLabels[i] = KeyEvent.getKeyText(keys1.getKey(i));
	    //System.out.println(KeyEvent.getKeyText(keys1.getKey(i)));
	}
	for(int i = 0; i < 5; i++)
	{
	    controlLabels[i+5] = KeyEvent.getKeyText(keys2.getKey(i));
	}
	
	buttonPlay = new Button ("Play");
	buttonOptions = new Button ("Options");
	buttonCredits = new Button ("Credits");
	buttonExit = new Button ("Exit");
	buttonMain = new Button ("Main Menu");
	buttonOptions2 = new Button ("Options");
	buttonControls = new Button ("Controls");
	buttonPlay_Practice = new Button ("Practice");
	buttonPlay_Two = new Button ("Two Player");
	buttonPlay_EndGame = new Button ("End Game"); //End Game Button cause easy
	
	buttonPlay.setFont (Consts.BUTTONFONT);
	buttonOptions.setFont (Consts.BUTTONFONT);
	buttonCredits.setFont (Consts.BUTTONFONT);
	buttonExit.setFont (Consts.BUTTONFONT);
	buttonMain.setFont (Consts.BUTTONFONT);
	buttonOptions2.setFont (Consts.BUTTONFONT);
	buttonControls.setFont (Consts.BUTTONFONT);
	buttonPlay_Practice.setFont (Consts.BUTTONFONT);
	buttonPlay_Two.setFont (Consts.BUTTONFONT);
	/*for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setFont (Consts.BUTTONFONT);
	}*/
	//buttonPlay_EndGame.setFont ();

	buttonPlay.setLocation (364, 122);
	buttonOptions.setLocation (364, 192);
	buttonCredits.setLocation (364, 262);
	buttonExit.setLocation (364, 332);
	buttonMain.setLocation (425, 500);
	buttonOptions2.setLocation (425, 500);
	buttonControls.setLocation (364, 262);
	buttonPlay_Practice.setLocation (200, 122);
	buttonPlay_EndGame.setLocation (624,571);
	buttonPlay_Two.setLocation (200, 322);
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    if(i < buttonControlHandles.length/2)
	    {
		buttonControlHandles[i].setLocation (190, 130 + i*52);
		//controlLabels[i].setLocation (295, 130+i*52);
	    }
	    else
	    {
		buttonControlHandles[i].setLocation (545, 130 + (i-5)*52);
		//controlLabels[i].setLocation (650, 130+i*52);
	    }
	}
	

	buttonPlay.setSize (235, 60);
	buttonOptions.setSize (235, 60);
	buttonCredits.setSize (235, 60);
	buttonExit.setSize (235, 60);
	buttonMain.setSize (235, 60);
	buttonOptions2.setSize (235, 60);
	buttonControls.setSize (235, 60);
	buttonPlay_Practice.setSize (235, 60);
	buttonPlay_EndGame.setSize (70, 20);
	buttonPlay_Two.setSize (235, 60);
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setSize (110, 30);
	}

	buttonMain.setVisible (false);
	buttonOptions2.setVisible (false);
	buttonControls.setVisible (false);
	buttonPlay_Practice.setVisible (false);
	buttonPlay_EndGame.setVisible (false);
	buttonPlay_Two.setVisible (false);
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setVisible (false);
	    //controlLabels[i].setVisible(false);
	}

	add (buttonPlay);
	add (buttonOptions);
	add (buttonCredits);
	add (buttonExit);
	add (buttonMain);
	add (buttonOptions2);
	add (buttonControls);
	add (buttonPlay_Practice);
	add (buttonPlay_EndGame);
	add (buttonPlay_Two);
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    add (buttonControlHandles[i]);
	    //add (controlLabels[i]);
	}


	buttonPlay.addActionListener (this);
	buttonOptions.addActionListener (this);
	buttonCredits.addActionListener (this);
	buttonExit.addActionListener (this);
	buttonMain.addActionListener (this);
	buttonOptions2.addActionListener (this);
	buttonControls.addActionListener (this);
	buttonPlay_Practice.addActionListener (this);
	buttonPlay_EndGame.addActionListener (this);
	buttonPlay_Two.addActionListener (this);
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].addActionListener (this);
	}
	
	background = getImage (getDocumentBase (), Consts.IMG_MAIN);
	//checkKeys();
    }


    public void paint (Graphics g)
    {
	update(g); //update to stop flickering
    }
    public void update( Graphics g ) 
    {
	/*BufferedImage bi = new BufferedImage(Consts.XSCREENSIZE, Consts.YSCREENSIZE, BufferedImage.TYPE_INT_ARGB);
	Graphics g2 = bi.createGraphics();
	g2.drawImage (background, 0, 0, this);
	g.drawImage(bi, 0,0,this);*/
	g.drawImage(background, 0,0,this);
	if(controlLabelsShow)
	{
	    for(int i = 0; i < controlLabels.length; i++)
	    {
		if(i < buttonControlHandles.length/2)
		{
		    g.drawString(controlLabels[i], 305, 150+i*52);
		}
		else
		{
		    g.drawString(controlLabels[i], 660, 150+(i-5)*52);
		}
	    }
	}
    }
    public void updateControlLabels()
    {
	for(int i = 0; i < 5; i++)
	{
	    controlLabels[i] = (KeyEvent.getKeyText(keys1.getKey(i)));
	    //System.out.println(KeyEvent.getKeyText(keys1.getKey(i)));
	}
	for(int i = 0; i < 5; i++)
	{
	    controlLabels[i+5] = (KeyEvent.getKeyText(keys2.getKey(i)));
	}
    }

	
    public void keyPressed (KeyEvent e) //Send command to tank
    {
	if(tank1 != null)
	{
	    int command = keys1.check(e.getKeyCode());
	    if (command > -1)
	    {
		tank1.sendCommand(command);
	    }
	}
	if(tank2 != null)
	{
	    int command = keys2.check(e.getKeyCode());
	    if (command > -1)
	    {
		tank2.sendCommand(command);
	    }
	}
	if(changeKey1)
	{
	    keys1.setKey(keyChange, e.getKeyCode());
	    changeKey1 = false;
	    updateControlLabels();
	    repaint();
	}
	if(changeKey2)
	{
	    keys2.setKey(keyChange, e.getKeyCode());
	    changeKey2 = false;
	    updateControlLabels();
	    repaint();
	}
	e.consume ();
    }


    public void keyReleased (KeyEvent e) //Send finished with command to tank
    {
	if(tank1 != null)
	{
	    int command = keys1.check(e.getKeyCode());
	    if (command > -1)
	    {
	       tank1.revokeCommand(command);
	    }
	}
	if(tank2 != null)
	{
	    int command = keys2.check(e.getKeyCode());
	    if (command > -1)
	    {
		tank2.revokeCommand(command);
	    }
	}
	e.consume ();
    }


    public void keyTyped (KeyEvent e)
    {
    }

    public void actionPerformed (ActionEvent evt) //Handle Buttons
    {
	String command = evt.getActionCommand ();

	if (command.equals ("Play"))
	{
	    playMenu ();
	}
	else if (command.equals ("Options"))
	{
	    optionsMenu ();
	}
	else if (command.equals ("Credits"))
	{
	    creditsMenu ();
	}
	else if (command.equals ("Exit"))
	{
	    System.exit (0);
	}
	else if (command.equals ("Main Menu"))
	{
	    mainMenu ();
	}
	else if (command.equals ("Controls"))
	{
	    controlsMenu ();
	}
	else if (command.equals ("Practice"))
	{
	    practice ();
	}
	else if (command.equals ("End Game"))
	{
	    endGame ();
	}
	else if (command.equals ("Change Key P1_1"))
	{
	    changeKey1 = true;
	    changeKey2 = false;
	    keyChange = 0;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Change Key P1_2"))
	{
	    changeKey1 = true;
	    changeKey2 = false;
	    keyChange = 1;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Change Key P1_3"))
	{
	    changeKey1 = true;
	    changeKey2 = false;
	    keyChange = 2;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Change Key P1_4"))
	{
	    changeKey1 = true;
	    changeKey2 = false;
	    keyChange = 3;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Change Key P1_5"))
	{
	    changeKey1 = true;
	    changeKey2 = false;
	    keyChange = 4;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Change Key P2_1"))
	{
	    changeKey2 = true;
	    changeKey1 = false;
	    keyChange = 0;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Change Key P2_2"))
	{
	    changeKey2 = true;
	    changeKey1 = false;
	    keyChange = 1;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Change Key P2_3"))
	{
	    changeKey2 = true;
	    changeKey1 = false;
	    keyChange = 2;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Change Key P2_4"))
	{
	    changeKey2 = true;
	    changeKey1 = false;
	    keyChange = 3;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Change Key P2_5"))
	{
	    changeKey2 = true;
	    changeKey1 = false;
	    keyChange = 4;
	    this.requestFocusInWindow();
	}
	else if (command.equals ("Two Player"))
	{
	    twoPlayer();
	}
    }
    
    public void run()
    {
	while(gameGo) //Gets image from the game and draws it
	{
	    try{Thread.sleep(Consts.CALCFPS);}catch(Exception e){};
	    background = game.draw();
	    //game.addObject(new Bullet(250, 250, 30));
	    repaint();
	}
    }
    public void endGame() //Reset variables and go to main menu
    {
	gameGo = false;
	try{Thread.sleep(100);}catch(Exception e){}; //Let's drawing finish before deleting game
	game = null;
	tank1 = null;
	tank2 = null;
	mainMenu();
	
    }
    public void practice()
    {
	buttonPlay.setVisible (false);
	buttonOptions.setVisible (false);
	buttonCredits.setVisible (false);
	buttonExit.setVisible (false);
	buttonMain.setVisible (false);
	buttonControls.setVisible (false);
	buttonOptions2.setVisible (false);
	buttonPlay_Practice.setVisible (false);
	buttonPlay_EndGame.setVisible (true);
	buttonPlay_Two.setVisible (false);
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setVisible (false);
	}
	
	//Setup game
	game = new Game(Practice.IMG_BACKGROUND);
	tank1 = new Tank(Practice.XTANK, Practice.YTANK, Practice.ANGLETANK, game, Practice.IMG_TANK);
	for(int i = 0; i < Practice.WALLS.length; i++)
	{
	    game.addObject(Practice.WALLS[i]);
	}
	for(int i = 0; i < Practice.TARGETS.length; i++)
	{
	    game.addObject(Practice.TARGETS[i]);
	}
	//Bullet b = new Bullet(250, 250, 30);
	//game.addObject(b);
	game.addObject(tank1);
	
	game.start();
	gameGo = true;
	t = new Thread(this);
	t.start();
    }
     public void twoPlayer()
    {
	buttonPlay.setVisible (false);
	buttonOptions.setVisible (false);
	buttonCredits.setVisible (false);
	buttonExit.setVisible (false);
	buttonMain.setVisible (false);
	buttonControls.setVisible (false);
	buttonOptions2.setVisible (false);
	buttonPlay_Practice.setVisible (false);
	buttonPlay_EndGame.setVisible (true);
	buttonPlay_Two.setVisible (false);
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setVisible (false);
	}
	
	//Setup game
	game = new Game(Practice.IMG_BACKGROUND);
	tank1 = new Tank(Practice.XTANK, Practice.YTANK, Practice.ANGLETANK, game, Practice.IMG_TANK);
	tank2 = new Tank(400, 250, 180, game, Practice.IMG_TANK2);
	for(int i = 0; i < Practice.WALLS.length; i++)
	{
	    game.addObject(Practice.WALLS[i]);
	}
	for(int i = 0; i < Practice.TARGETS.length; i++)
	{
	    game.addObject(Practice.TARGETS[i]);
	}
	//Bullet b = new Bullet(250, 250, 30);
	//game.addObject(b);
	game.addObject(tank1);
	game.addObject(tank2);
	
	game.start();
	gameGo = true;
	t = new Thread(this);
	t.start();
    }

    public void playMenu ()
    {
	buttonPlay.setVisible (false);
	buttonOptions.setVisible (false);
	buttonCredits.setVisible (false);
	buttonExit.setVisible (false);
	buttonMain.setVisible (true);
	buttonControls.setVisible (false);
	buttonOptions2.setVisible (false);
	buttonPlay_Practice.setVisible (true);
	buttonPlay_EndGame.setVisible (false);
	buttonPlay_Two.setVisible (true);
	controlLabelsShow = false;
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setVisible (false);
	}
	background = getImage (getDocumentBase (), Consts.IMG_PLAYMENU);
	repaint ();
    }


    public void creditsMenu ()
    {
	buttonPlay.setVisible (false);
	buttonOptions.setVisible (false);
	buttonCredits.setVisible (false);
	buttonExit.setVisible (false);
	buttonMain.setVisible (true);
	buttonControls.setVisible (false);
	buttonOptions2.setVisible (false);
	buttonPlay_Practice.setVisible (false);
	buttonPlay_EndGame.setVisible (false);
	buttonPlay_Two.setVisible (false);
	controlLabelsShow = false;
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setVisible (false);
	}
	background = getImage (getDocumentBase (), Consts.IMG_CREDITS);
	repaint ();
    }

    public void optionsMenu ()
    {
	buttonPlay.setVisible (false);
	buttonOptions.setVisible (false);
	buttonCredits.setVisible (false);
	buttonExit.setVisible (false);
	buttonMain.setVisible (true);
	buttonControls.setVisible (true);
	buttonOptions2.setVisible (false);
	buttonPlay_Practice.setVisible (false);
	buttonPlay_EndGame.setVisible (false);
	buttonPlay_Two.setVisible (false);
	controlLabelsShow = false;
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setVisible (false);
	}
	background = getImage (getDocumentBase (), Consts.IMG_OPTIONS);
	repaint ();
    }


    public void mainMenu ()
    {
	buttonPlay.setVisible (true);
	buttonOptions.setVisible (true);
	buttonCredits.setVisible (true);
	buttonExit.setVisible (true);
	buttonMain.setVisible (false);
	buttonControls.setVisible (false);
	buttonOptions2.setVisible (false);
	buttonPlay_Practice.setVisible (false);
	buttonPlay_EndGame.setVisible (false);
	buttonPlay_Two.setVisible (false);
	controlLabelsShow = false;
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setVisible (false);
	}
	background = getImage (getDocumentBase (), Consts.IMG_MAIN);
	
	repaint ();
    }
    
    public void controlsMenu ()
    {
	
	buttonPlay.setVisible (false);
	buttonOptions.setVisible (false);
	buttonCredits.setVisible (false);
	buttonExit.setVisible (false);
	buttonMain.setVisible (false);
	buttonControls.setVisible (false);
	buttonOptions2.setVisible (true);
	buttonPlay_Practice.setVisible (false);
	buttonPlay_EndGame.setVisible (false);
	buttonPlay_Two.setVisible (false);
	controlLabelsShow = true;    
	
	changeKey1 = false;
	changeKey2 = false;
	
	for(int i = 0; i < buttonControlHandles.length; i++)
	{
	    buttonControlHandles[i].setVisible (true);
	}
	background = getImage (getDocumentBase (), Consts.IMG_CONTROLS);
	repaint ();
    }
    /*public void setKeys()
    {
	try
	{
	    PrintWriter pw = new PrintWriter( (new FileWriter(Consts.KEYFILE)) );
	}catch(Exception e){};
    }*/
    
    /*public void initiateKeys()
    {
	key1 = new char[5];
	key2 = new char[5];
	try
	{
	    FileReader fr = new FileReader (Consts.KEYFILE);
	    BufferedReader br = new BufferedReader (fr);
	    int i = 0;
	    String s = br.readLine();
	    while(s != null)
	    {
		if(i <= 4)
		{
		    key1[i] = s.charAt(0);
		}
		else if(i <= 9)
		{
		    key2[i-5] = s.charAt(0);
		}
		i++;
		s = br.readLine();
	    }
	    br.close();
	}catch(Exception e){System.out.println(e.toString());};
    }*/
    
    /*public void checkKeys()
    {
	for(int i = 0; i < 5; i++)
	{
	    System.out.println(key1[i]);
	    System.out.println(key2[i]);
	}
	
    }*/
    
    /*public void loadBackground()
    {
	try
	{
	    if(menu = Consts.MENUMAIN)
		background = getImage(getDocumentBase(), Consts.IMG_MAIN);
	    else if(menu = Consts.MENUOPTIONS)
		background = getImage(getDocumentBase(), "images/OptionsMenu.JPG");
	    else if(menu = Consts.MENUCREDITS)
		background = getImage(getDocumentBase(), "images/CreditsMenu.JPG");
	}
	catch(Exception e) { }
     }*/
} // CulmTonyNg2014 class
