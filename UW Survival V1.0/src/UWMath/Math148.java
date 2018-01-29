package UWMath;

import java.util.Random;

/** 
 * class for advanced calculus 2
 */  
public class Math148 extends ThingsAtSchool{
	//properties of an advanced class
    public static final int DOUBLE_ERASER = 0; //double erasers
    public static final int LIFE = 1; //one extra life award  
    public static final int PEN = 2; //pen award
    public static final int PENLIFE = 3; //pen award + life award
    private int xspeed = 1; 
    private int yspeed = 1; 
    private int score = 30;
    private int awardType;  
    public int getAwardType(){  
        return awardType;  
    }  
    public int getScore(){  
        return score;  
    }
    
    /** 
     * Constructor for HarderClasses
     */  
    public Math148(){    
        image = Game.math148;  
        entityWidth = image.getWidth();  
        entityHeight= image.getHeight();  
        y = -entityHeight;  
        //random x coordinate
        Random r = new Random();  
        x = r.nextInt(Game.WIDTH - entityWidth);  
        awardType = 2;  
        duration = 7;
    } 
    
    @Override  
    public void move() {  
        x += xspeed;  
        y += yspeed;  
        //difficult courses don't just leave, they would come back 
        if(x < 0 || x > Game.WIDTH - entityWidth){  
            xspeed *= -1;  
        }  
    }
    
    @Override  
    public boolean outOfBoundary() {  
        return y > Game.HEIGHT;  
    }  
}