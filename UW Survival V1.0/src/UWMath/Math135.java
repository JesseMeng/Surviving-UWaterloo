package UWMath;

import java.util.Random;

/** 
 * first year algebra 1
 */
public class Math135 extends ThingsAtSchool{
    private int speed = 1;
    private int score = 20;
    public int getScore(){  
        return score;  
    }
    
    /** 
     * Constructor
     */  
    public Math135(){  
        image = Game.math135;  
        entityWidth = image.getWidth();  
        entityHeight = image.getHeight();  
        y = -entityHeight;  
        Random r = new Random();  
        x = r.nextInt(Game.WIDTH - entityWidth); 
        duration = 4;
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