/*
Programmed by: Tony Ng
Last Modified: 02/06/2014
Purpose: Holds all the constants for easy access and organization
*/
import java.awt.Font; 
interface Consts
{
    //Predefined Constants
    public static final int XSCREENSIZE       = 702;
    public static final int YSCREENSIZE       = 590;

    public static final int WALL              = 1;
    public static final int BULLET            = 2;
    public static final int TANK              = 3;
    public static final int BULLETNOCOLLIDE   = 4;
    public static final int TARGET            = 5;

    public static final int LEFTCOLLIDE       = 1;
    public static final int RIGHTCOLLIDE      = 2;
    public static final int UPCOLLIDE         = 3;
    public static final int DOWNCOLLIDE       = 4;
    
    public static final int COLLIDEPUSH       = 1; //The bump from hitting the wall 
    public static final double WALLSLOW       = 0.25; //Lower is higher, zero is not acceptable
    
    public static final int MENUMAIN          = 1;
    public static final int MENUOPTIONS       = 2;
    public static final int MENUCREDITS       = 3;
    
    public static final int KEYSPERCLASS      = 5; //Keys in a Keys Class
    
    public static final int CALCFPS           = 30; //Calculation Period, in ms
    
    public static final int TANKFRICTION      = 12; //Higher is less
    public static final int TANKXBOUND        = 40;
    public static final int TANKYBOUND        = 40;
    public static final int TANKTURNRATE      = 3;
    public static final int TANKMAXVEL        = 1;
    public static final int XTANKGUN          = 20;
    public static final int YTANKGUN          = 20;
    public static final int XTANKGUNLENGTH    = 20;
    public static final int YTANKGUNLENGTH    = 20;
    public static final int RATEOFFIRE        = 30;
    public static final double TANKACCELERATE = 0.15;

    public static final int XBULLETSIZE       = 10;
    public static final int YBULLETSIZE       = 10;
    public static final int BULLETCOOLDOWN    = 3; //In frames  
    public static final int MAX_BOUNCE        = 5;
    public static final double BULLETVELOCITY = 12;   
    
    public static final int TARGETXSIZE       = 40;
    public static final int TARGETYSIZE       = 40;
    
    public static final String KEYFILE1       = "Keys1.data";
    public static final String KEYFILE2       = "Keys2.data";
    
    public static final String IMG_MAIN       = "images/MainMenu.JPG";
    public static final String IMG_OPTIONS    = "images/OptionsMenu.JPG";
    public static final String IMG_CONTROLS   = "images/ControlsMenu.JPG";
    public static final String IMG_CREDITS    = "images/CreditsMenu.JPG";
    public static final String IMG_PLAYMENU   = "images/PlayMenu.JPG";
    
    public static final String IMG_BULLET   = "images/Bullet.PNG";
    public static final String IMG_BLANK    = "images/Blank.PNG";
    public static final String IMG_TARGET   = "images/Target.PNG";
    
    
    public static final Font BUTTONFONT = new Font("SansSerif", Font.PLAIN, 30);
}
