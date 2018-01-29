package UWMath;

public class Eraser extends ThingsAtSchool{
	private int speed = 3;
	
    /** 
     * Constructor
     * eraser's location depends on the student
     * @param x x determined from student 
     * @param y y determined from student
     */  
	public Eraser(int x, int y){
        image = Game.eraser;  
        entityWidth = image.getWidth();  
        entityHeight = image.getHeight();  
        this.x = x;  
        this.y = y;  
	}
	
	@Override
	public void move() {   
        y -= speed;  
    }  
	
	@Override
    public boolean outOfBoundary() {  
        return (y + entityHeight) < 0;  
    }  
}
