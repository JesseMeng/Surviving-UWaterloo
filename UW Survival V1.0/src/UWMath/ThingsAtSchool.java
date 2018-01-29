package UWMath;

import java.awt.image.BufferedImage;

/** 
 * Essentially any thing that can move 
 */  

public abstract class ThingsAtSchool {
	// basic properties of an entity
    protected int x; //x coordinate
    protected int y; //y coordinate
    protected int entityHeight; 
    protected int entityWidth; 
    protected BufferedImage image; //image of this thing
    protected int duration; //applicable to courses and 0 for other things
    
    public void setDuration(int newD){
    	duration = newD;
    }
    
    public int getDuration(){
    	return duration;
    }
    
    /** 
     * All of the instances of the things at school move in some sort
     * the method implementation depends on the instance
     */      
    public abstract void move();
    
    /** 
     * checks if something is out of boundary
     * @return if out of bounds 
     */  
    public abstract boolean outOfBoundary();  
    
    /** 
     * static algorithm to check collision of rectangles
     * @param t1 thing1
     * @param t2 thing2
     * @return if collide
     */
    public static boolean hit(ThingsAtSchool t1, ThingsAtSchool t2){  
        //calculate the mid points
        int mid1x = t1.x + t1.entityWidth/2;  
        int mid1y= t1.y + t1.entityHeight/2;  
        int mid2x = t2.x + t2.entityWidth/2;  
        int mid2y = t2.y + t2.entityHeight/2;  
        //determine if t1 and t2 hit with each other
        return Math.abs(mid1x - mid2x) < (t1.entityWidth + t2.entityWidth)/2 &&  Math.abs(mid1y -mid2y) < (t1.entityHeight + t2.entityHeight)/2;
    } 
}  