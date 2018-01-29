package UWMath;

/** 
 * the main character of the game who fights through the courses
 */  
public class UWStudent extends ThingsAtSchool{
    private int doubleFire; //number of double pencils  
    private int pens; //number of pens
    private int life; 
    private int score; 
    
    //getters
    public int getLife(){  
        return life;  
    } 
    public void decreaseLife(){
    	life--;
    }
    public int getScore(){  
        return score;  
    }  
    
    /** 
     * default constructor
     */  
    public UWStudent(){  
        image = Game.studentImage;  
        entityHeight = image.getHeight();  
        entityWidth = image.getWidth(); 
        //start location
        x = 127;  
        y = 388;  
        //start with 3 lives and no double fire
        doubleFire = 0; 
        pens = 0;
        life = 3;  
        score = 0;  
    }  
    
    @Override  
    public void move() {   
    } 
    
    @Override  
    public boolean outOfBoundary() {  
        return false;  
    }  
    
    /** 
     * moves the midpoint of the student to where mouse is
     * @param x mouse's x
     * @param y mouse's y
     */  
    public void move(int x,int y){    
        this.x = x - entityWidth / 2;  
        this.y = y - entityHeight / 2;  
    }  
    
    /** 
     * method to gain score or awards
     * @param oppo is the entity that was destroyed by the student
     */  
    public void getScore_Award(ThingsAtSchool oppo){  
        if(oppo instanceof Math135){
            score += ((Math135)oppo).getScore();  
        }else if(oppo instanceof Math136){
            score += ((Math136)oppo).getScore();  
        }else if(oppo instanceof Math137){
            score += ((Math137)oppo).getScore();  
        }else if(oppo instanceof Math138){
            score += ((Math138)oppo).getScore();  
        }else if(oppo instanceof Math145){
            score += ((Math145)oppo).getScore();  
            life += 1;
            pens += 30;
            doubleFire = 0;
        }else if(oppo instanceof Math146){
            score += ((Math146)oppo).getScore(); 
            life += 1;
            if(pens > 0) pens+=10;
            if(doubleFire > 0) doubleFire+=10;
        }else if(oppo instanceof Math147){
            score += ((Math147)oppo).getScore();  
            doubleFire += 20;
            pens = 0;
        }else if(oppo instanceof Math148){
            score += ((Math148)oppo).getScore(); 
            pens += 30;
            doubleFire = 0;
        }
    } 
    
    /** 
     * method to shoot pencils
     * @return array of pencils, could be 1 or 2 
     */  
    public ThingsAtSchool[] shoot(){  
        ThingsAtSchool[] weapons = null;  
        if(doubleFire != 0){ //double fire is on 
            weapons = new ThingsAtSchool[2];  
            Eraser e1 = new Eraser(x + entityWidth/4 - Game.eraser.getWidth()/2,y + Game.eraser.getWidth());  
            Eraser e2 = new Eraser(x + entityWidth*3/4 - Game.eraser.getWidth()/2,y + Game.eraser.getWidth());  
            weapons[0] = e1;  
            weapons[1] = e2;  
            doubleFire -= 1;  
        }else if(pens != 0){ // pens is on
        	weapons = new ThingsAtSchool[1];  
        	weapons[0]=new Pen(x + entityWidth/2 - Game.pen.getWidth()/2,y - Game.pen.getHeight()); 
        	pens -= 1;
        }else{
            weapons = new ThingsAtSchool[1];  
            weapons[0] = new Eraser(x + entityWidth/2 - Game.eraser.getWidth()/2,y - Game.eraser.getHeight());  
        }  
        return weapons;  
    }  

    /** 
     * detecting hits with opponents
     * @param oppo could be any opponent in discussion
     * @return if they collide
     */  
    public boolean die(ThingsAtSchool oppo){  
        boolean hit = ThingsAtSchool.hit(this, oppo);  
        if(hit){
            life--;  //loses a life
            doubleFire = 0;  //loses double fire ability
            pens = 0; // loses pen ability
        }  
        return hit;  
    }  
}