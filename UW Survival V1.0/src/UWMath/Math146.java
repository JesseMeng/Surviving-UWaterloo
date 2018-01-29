package UWMath;

import java.util.Random;

/** 
 * class for more challenging courses
 */  
public class Math146 extends ThingsAtSchool{
	//properties of an advanced class
    public static final int DOUBLE_ERASER = 0; //double erasers
    public static final int LIFE = 1; //one extra life award  
    public static final int PEN = 2; //pen award
    public static final int PENLIFE = 3; //pen award + life award
    private int xspeed = 2; 
    private int yspeed = 3; 
    private int score = 20;
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
    public Math146(){    
        image = Game.math146;  
        entityWidth = image.getWidth();  
        entityHeight= image.getHeight();  
        y = -entityHeight;  
        //random x coordinate
        Random r = new Random();  
        x = r.nextInt(Game.WIDTH - entityWidth);  
        awardType = 1;  
        duration = 3;
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