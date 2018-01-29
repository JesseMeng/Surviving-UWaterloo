package UWMath;

import java.util.Random;

/** 
 * first year calculus 1
 */
public class Math137 extends ThingsAtSchool{
    private int speed = 1;
    private int score = 5;
    public int getScore(){  
        return score;  
    }
    
    /** 
     * Constructor
     */  
    public Math137(){  
        image = Game.math137;  
        entityWidth = image.getWidth();  
        entityHeight = image.getHeight();  
        y = -entityHeight;  
        Random r = new Random();  
        x = r.nextInt(Game.WIDTH - entityWidth);  
        duration = 1;
    }  
    
    @Override
    public void move() {  
        y += speed;  
    }  
    
    @Override
    public boolean outOfBoundary() {  
        return y > Game.HEIGHT;  
    }  
    
}