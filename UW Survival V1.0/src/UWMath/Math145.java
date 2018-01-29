package UWMath;

import java.util.Random;

/** 
 * class for advanced algebra 1
 */  
public class Math145 extends ThingsAtSchool{
	//properties of an advanced class
    public static final int DOUBLE_ERASER = 0; //double erasers
    public static final int LIFE = 1; //one extra life award  
    public static final int PEN = 2; //pen award
    public static final int PENLIFE = 3; //pen award + life award
    private int xspeed = 2; 
    private int yspeed = 1; 
    private int score = 50;
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
    public Math145(){    
        image = Game.math145;  
        entityWidth = image.getWidth();  
        entityHeight= image.getHeight();  
        y = -entityHeight;  
        //random x coordinate
        Random r = new Random();  
        x = r.nextInt(Game.WIDTH - entityWidth);  
        awardType = 3;  
        duration = 10;
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