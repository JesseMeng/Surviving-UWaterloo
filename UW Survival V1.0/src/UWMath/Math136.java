package UWMath;

import java.util.Random;

/** 
 * first year linear algebra
 */
public class Math136 extends ThingsAtSchool{
    private int speed = 1;
    private int score = 10;
    public int getScore(){  
        return score;  
    }
    
    /** 
     * Constructor
     */  
    public Math136(){  
        image = Game.math136;  
        entityWidth = image.getWidth();  
        entityHeight = image.getHeight();  
        y = -entityHeight;  
        Random r = new Random();  
        x = r.nextInt(Game.WIDTH - entityWidth); 
        duration = 2;
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