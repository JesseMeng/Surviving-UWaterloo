package UWMath;

import java.awt.Font;  
import java.awt.Graphics;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.image.BufferedImage;  
import java.io.IOException;  
import java.util.Arrays;  
import java.util.Random;  
import java.util.Timer;  
import java.util.TimerTask;  
  
import javax.imageio.ImageIO;  
import javax.swing.JFrame;  
import javax.swing.JPanel; 

public class Game extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	//background image 320*568  
	public static final int WIDTH = 320;  
	public static final int HEIGHT = 568;  
	//frame size 336*607  
	public static final int FRAME_WIDTH = 336;  
	public static final int FRAME_HEIGHT = 607;  

	public static BufferedImage background;
	public static BufferedImage start;  
	public static BufferedImage pause; 
	public static BufferedImage gameover; 
	//courses
	public static BufferedImage math135;
	public static BufferedImage math136;
	public static BufferedImage math137;
	public static BufferedImage math138;
	public static BufferedImage math145;
	public static BufferedImage math146;
	public static BufferedImage math147;
	public static BufferedImage math148;
	public static BufferedImage studentImage; 
	public static BufferedImage pen;  
	public static BufferedImage eraser;  

	
	//loading  all the images required for this game
	//these images will be static and reused as they don't change
	static{  
	    try {  
	        background = ImageIO.read(Game.class.getResource("/imgs/background.png"));  
	        math135 = ImageIO.read(Game.class.getResource("/imgs/math135.png"));
	        math136 = ImageIO.read(Game.class.getResource("/imgs/math136.png"));
	        math137 = ImageIO.read(Game.class.getResource("/imgs/math137.png"));
	        math138 = ImageIO.read(Game.class.getResource("/imgs/math138.png"));
	        math145 = ImageIO.read(Game.class.getResource("/imgs/math145.png"));
	        math146 = ImageIO.read(Game.class.getResource("/imgs/math146.png"));
	        math147 = ImageIO.read(Game.class.getResource("/imgs/math147.png"));
	        math148 = ImageIO.read(Game.class.getResource("/imgs/math148.png"));
	        pen = ImageIO.read(Game.class.getResource("/imgs/pen.png"));  
	        eraser = ImageIO.read(Game.class.getResource("/imgs/eraser.png"));
	        start = ImageIO.read(Game.class.getResource("/imgs/start.png"));  
	        pause = ImageIO.read(Game.class.getResource("/imgs/pause.png"));  
	        studentImage = ImageIO.read(Game.class.getResource("/imgs/student.png"));  
	        gameover = ImageIO.read(Game.class.getResource("/imgs/gameover.png"));  
	    } catch (IOException e) {   
	        e.printStackTrace();  
	    }  
	}   
	// Construct one student, an array of opponents and pencils
	public UWStudent student = new UWStudent();  
	public ThingsAtSchool[] opponents = {}; //contains all current opponents
	public ThingsAtSchool[] weapons = {}; //contains all current weapons  
	  
	//default is start state
	private int state = START;    
	public static final int START = 0;  
	public static final int RUNNING = 1;  
	public static final int PAUSE = 2;  
	public static final int GAME_OVER = 3;  
	public int easiness = 250;
	  
    /** 
     * displaying the score and life of the student
     * @param g 
     */  
	public void displayStatus(Graphics g){  
		//position
	    int x = 10;  
	    int y = 15; 
	    Font font = new Font(Font.SANS_SERIF,Font.BOLD,14);  
	    g.setFont(font); 
	    g.drawString("SCORE: " + student.getScore(), x, y);  
	    y += 20;  
	    g.drawString("LIFE: " + student.getLife(), x, y);  
	}
	
    /** 
     * displaying the student
     * @param g
     */  
	public void paintStudent(Graphics g){  
	    g.drawImage(student.image, student.x, student.y, null);  
	}  
	
    /** 
     * displaying the opponents 
     * @param g  
     */ 
	public void paintOpponents(Graphics g){  
	    for(int i = 0;i < opponents.length;i++){  
	        g.drawImage(opponents[i].image, opponents[i].x, opponents[i].y, null);  
	    }  
	}  
	
    /** 
     * displaying the weapons
     * @param g 
     */  
	public void paintWeapons(Graphics g){  
	    for(int i = 0;i < weapons.length;i++){  
	        g.drawImage(weapons[i].image, weapons[i].x, weapons[i].y, null);  
	    }  
	}  
	
	@Override
	public void paint(Graphics g) {  
		//draws background, then student, then courses, then weapons, then status
	    g.drawImage(background, 0, 0, null);  
	    paintStudent(g);  
	    paintOpponents(g);  
	    paintWeapons(g);  
	    displayStatus(g);  
	    //depending on the state of the game, display the correct image
	    if(state == START){  
	        g.drawImage(start, 0, 0, null);  
	    }else if(state == PAUSE){  
	        g.drawImage(pause, 0, 0, null);  
	    }else if(state == GAME_OVER){  
	        g.drawImage(gameover, 0, 0, null);  
	    }        
	}
	
	/** 
	 * things to do when game starts 
	 */  
	public void actions(){  
	    //Setting up mouse
	    MouseAdapter mAdapt = new MouseAdapter(){ 
	    	@Override 
	        public void mouseMoved(MouseEvent e) {  
	            //Only be able to move student if game is running
	            if(state == RUNNING){  
	                int x = e.getX();  
	                int y = e.getY();  
	                student.move(x, y);  
	            }  
	        }  
	        @Override 
	        public void mouseClicked(MouseEvent e) {  
	            if(state == START || state == PAUSE){   
	                state = RUNNING;  
	            }else if(state == RUNNING){  
	                state = PAUSE;  
	            }else if(state == GAME_OVER){   
	                state = START;  
	                opponents = new ThingsAtSchool[0];  
	                weapons = new ThingsAtSchool[0];  
	                student = new UWStudent();  
	            }  
	        }  
	        @Override 
	        public void mouseExited(MouseEvent e) {  
	            if(state == RUNNING){  
	                //when game is running, mouse exiting the screen automatically pauses the game
	                state = PAUSE;  
	            }  
	        }  
	        @Override 
	        public void mouseEntered(MouseEvent e) {  
	            if(state == PAUSE){  
	                state = RUNNING;  
	            }  
	        }  
	    }; 
	    
	    this.addMouseMotionListener(mAdapt); //movements
	    this.addMouseListener(mAdapt);; //clicks 
	      
	    Timer timer = new Timer();  
	    timer.schedule(new TimerTask(){  
	        private int runTimes = 0;//number of times run() runs

	        @Override  
	        public void run() {  
	            if(state == RUNNING){  
	                runTimes++;
	                if(runTimes % 200 == 0){ //difficulty increases every 2 seconds
	                    easiness--;
	                }
	                //a new opponent every 2.5 seconds at first, then faster
	                if(runTimes % easiness == 0){  
	                    generateCourse(); //generate a new opponent 
	                }
	                
	                //refresh all the opponents
	                for(int i = 0;i < opponents.length;++i){  
	                    opponents[i].move();  
	                }  
	                  
	                //a new bullet every 500 milliseconds
	                if(runTimes % 50 == 0){  
	                    shoot(); //create a new weapon
	                }  
	                //refresh all bullets
	                for(int i = 0;i < weapons.length;i++){  
	                    weapons[i].move();  
	                }  
	          
	                //alternate student's images to show animation effect
	                student.move();
	                //checks for dead opponents, out of bound entities and
	                // if student is hit by a final of a course without preparation!
	                destroy();  
	                hitOpponents();//check for crashes with opponents
	                outOfBounds();  
	            }      
	            repaint();  
	        }  
	          
	    }, 10,10); //rerun every 10 milliseconds
	}
	     
	
	/** 
	 * create a new opponent randomly and add it to opponents[]
	 */  
	public void generateCourse(){  
	    Random r = new Random();  
	    ThingsAtSchool t = null;  
	    int indicator = r.nextInt(20);
	    if(indicator < 5){
	    	t = new Math137(); 
	    }else if(indicator < 8){
	    	t = new Math136(); 
	    }else if(indicator < 11){
	    	t = new Math138(); 
	    }else if(indicator < 13){
	    	t = new Math135(); 
	    }else if(indicator < 16){
	    	t = new Math147(); 
	    }else if(indicator < 17){
	    	t = new Math146(); 
	    }else if(indicator < 19){
	    	t = new Math148(); 
	    }else if(indicator == 19){
	    	t = new Math145(); 
	    }
	    //expand the current opponents array
	    opponents = Arrays.copyOf(opponents, opponents.length + 1);  
	    opponents[opponents.length - 1] = t;  
	}  
	  
	/** 
	 * create new weapons and add them to the weapons array
	 */  
	public void shoot(){  
	    ThingsAtSchool[] newWeapons = student.shoot();//get the newly created weapons
	    weapons = Arrays.copyOf(weapons, weapons.length + newWeapons.length);  
	    //add the weapons to the big array
	    System.arraycopy(newWeapons, 0, weapons, weapons.length - newWeapons.length, newWeapons.length);  
	      
	}  
	
	/** 
	 * detecting weapon collision with the opponents
	 */  
	public void destroy(){  
	    for(int i = 0;i < weapons.length;++i){  
	        for(int j = 0;j < opponents.length;++j){  
	            if(ThingsAtSchool.hit(weapons[i], opponents[j])){
	            	int damage = 0;
	            	if(weapons[i] instanceof Eraser){
	            		damage = 1;
	            	}else if(weapons[i] instanceof Pen){
	            		damage = 3;
	            	}else{
	            		System.out.println("weapon not supported!");
	            	}
	            	opponents[j].setDuration(opponents[j].getDuration()-damage);
	            	if(opponents[j].getDuration() <= 0){
	            		//opponent is dead
		                student.getScore_Award(opponents[j]);
		                opponents[j] = opponents[opponents.length - 1];   
		                opponents = Arrays.copyOf(opponents, opponents.length - 1); 
	            	}
	                //remove the bullet
	                weapons[i] = weapons[weapons.length - 1];  
	                weapons = Arrays.copyOf(weapons, weapons.length -1);  
	                --i; //re check this one, because this is actually the last bullet from previous array
	                break; //only one opponent can be hit at a time  
	            }  
	        }  
	    }  
	} 
	
	/** 
	 * checks if any of the opponents are out of boundary
	 */  
	public void outOfBounds(){  
	    ThingsAtSchool[] newOpponents = new ThingsAtSchool[opponents.length];  

	    int index = 0;  
	    for(int i = 0;i < opponents.length;i++){  
	        if(!opponents[i].outOfBoundary()){ 
	            newOpponents[index] = opponents[i];  
	            index++;  
	        }else{
	        	student.decreaseLife();
	        }
	    }  
	    opponents = Arrays.copyOf(newOpponents, index);  
	      
	    //check if any of the pencils are out of bounds so they can be removed
	    ThingsAtSchool[] newWeapons = new ThingsAtSchool[weapons.length];  
	    index = 0;  //this is to keep the actual number of in-bound pencils
	    for(int i = 0;i < weapons.length;++i){  
	        if(!weapons[i].outOfBoundary()){  
	            newWeapons[index] = weapons[i];  
	            ++index;  
	        }  
	    }  
	    weapons = Arrays.copyOf(newWeapons, index);  
	}  
	
	/** 
	 * check to see if any collisions between student and courses
	 */  
	public void hitOpponents(){  
	    ThingsAtSchool[] newOpponents = new ThingsAtSchool[opponents.length];  
	    int index = 0;  
	    for(int i = 0;i < opponents.length;i++){  
	        if(!student.die(opponents[i])){  
	            newOpponents[index] = opponents[i];  
	            ++index;  
	        }  
	    }  
	    if(student.getLife() <= 0){
	        state = GAME_OVER;  
	    }  
	    opponents = Arrays.copyOf(newOpponents, index);     
	}  
	
	public static void main(String[] args) {  
	    JFrame frame = new JFrame("UWMathSurvival v1.0");  
	    frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);//(336, 607);  
	    frame.setAlwaysOnTop(true); 
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setLocationRelativeTo(null); 
	    Game game = new Game();   
	    frame.add(game); 
	    frame.setVisible(true);  
	    game.actions();  //actions to be done after window is set up
	}
}