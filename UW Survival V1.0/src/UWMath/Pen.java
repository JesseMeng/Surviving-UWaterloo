package UWMath;

public class Pen extends ThingsAtSchool{
	private int speed = 5;
	
    /** 
     * Constructor
     * pen's location depends on the student
     * @param x x determined from student 
     * @param y y determined from student
     */  
	public Pen(int x, int y){
        image = Game.pen;  
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
