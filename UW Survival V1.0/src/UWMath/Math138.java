package UWMath;

import java.util.Random;

/** 
 * first year calculus 2
 */
public class Math138 extends ThingsAtSchool{
    private int speed = 1;
    private int score = 15;
    public int getScore(){  
        return score;  
    }
    
    /** 
     * Constructor
     */  
    public Math138(){  
        image = Game.math138;  
        entityWidth = image.getWidth();  
        entityHeight = image.getHeight();  
        y = -entityHeight;  
        Random r = new Random();  
        x = r.nextInt(Game.WIDTH - entityWidth); 
        duration = 3;
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
