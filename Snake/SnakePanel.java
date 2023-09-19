package Snake;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.io.*;
import java.util.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SnakePanel extends JPanel{


public static final int DEFAULT_W=1000;
public static final int DEFAULT_H=1000;
private int timerDelay;
private Timer timer;
private Timer timer2;
public ArrayList<Point> codaSnake = new ArrayList<Point>();

public final int UP=0,DOWN=1,LEFT=2,RIGHT=3;
public int direction=3;
public Point head;
public final int SCALE=10;
public int score=0;
public int minL=5;

public int cherrySize=20;
public int cherryPositionX=300;
public int cherryPositionY=200;
public int maxPosition=800;
public int minPosition=100;

String sHighScore="1";
int HighScore=1;

int R;
int G;
int B;



   
	
boolean gameOver=false;


public SnakePanel(){
MouseClass mouse = new MouseClass();
KeyClass key = new KeyClass();
this.setPreferredSize(new Dimension(DEFAULT_W, DEFAULT_H));
this.setVisible(true);
this.setBackground(Color.BLACK);
this.setFocusable(true);
this.addMouseListener(mouse);
this.addKeyListener(key);


head=new Point(20,20);  //START POINT<-------------

timerDelay=50;         //TIMER SPEED<-------------
timer= new Timer(timerDelay,timerAction);
timer.start();
try{
HighScore=getHighScore();
}catch(IOException e){}

musichetta("TunzTunz.wav");




}

		///MUSICHETTA TRIP!!!!||||
 public static synchronized void musichetta(final String url) {
    new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
      public void run() {
        try {
          Clip clip = AudioSystem.getClip();
          AudioInputStream inputStream = AudioSystem.getAudioInputStream(SnakePanel.class.getResourceAsStream(url));
          clip.open(inputStream);
          clip.start(); 
		  clip.loop(clip.LOOP_CONTINUOUSLY); 
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
    }).start();
  }
//aaaaaaaaaaaaaaaaaaaaaa
public static int getHighScore() throws IOException{
	
	FileReader f;
BufferedReader b;
	f=new FileReader("./HighScore.txt");
	b=new BufferedReader(f);
  
   String sHighScore = b.readLine();
   int HighScore = Integer.parseInt(sHighScore);  
    System.out.println(sHighScore);
	System.out.println(HighScore);
	b.close();
	f.close();
	return HighScore;
	
	
}
public static void setHighScore(int HighScore) throws IOException{
	
	FileWriter fw;
   fw = new FileWriter("./HighScore.txt");
  
   fw.write(Integer.toString(HighScore));
   
fw.close();


	
}




//aaaaaaaaaaa

ActionListener timerAction=new ActionListener(){

public void actionPerformed(ActionEvent event){
	Random r=new Random();
	
		
		
		if(!codaSnake.contains(new Point(head.x,head.y))&&gameOver==false){////Nuovo: gameOver true?  se esiste gia il punto in codaSnake
       codaSnake.add(new Point(head.x,head.y));
		}else{gameOver=true;}





		//lolz
		if(codaSnake.size()>(score+minL)){codaSnake.remove(0);} //se snakeSize>score+3-->togli ultimo puntoSnake
		//System.out.println(head.x+" "+head.y);
		
		//se 
		 
		 
		 int headPoint=codaSnake.size()-1; //puntoHead
		 int headX= (int)codaSnake.get(headPoint).getX(); //coordPuntoHead 
		 int headY= (int)codaSnake.get(headPoint).getY();
		 
		 boolean bX=(((cherryPositionX/SCALE)<=headX) && (headX<=((cherryPositionX/SCALE)+(cherrySize/SCALE) )));//condizioni cherry mangiata
		 boolean bY=(((cherryPositionY/SCALE)<=headY) && (headY<=((cherryPositionY/SCALE)+(cherrySize/SCALE) )));
		
		int randomX=r.nextInt(maxPosition-minPosition);  //nuova posizione cherry=random(da 0 a 900-100)
		int randomY=r.nextInt(maxPosition-minPosition);  //
		int cherryBordersX=randomX+minPosition;          //da 100 a 900
        int cherryBordersY=randomY+minPosition;		
		
		if(bX && bY){                    
		cherryPositionX=cherryBordersX;
		cherryPositionY=cherryBordersY;
		score+=1;
			if(score>1 && (score-1)%5==0){
			score=score*2;
			}
		}
		
		
		
		R=r.nextInt(256);
        G=r.nextInt(256);
        B=r.nextInt(256);
		
			//Nuovo: borders			
if(headX<=minPosition/SCALE && direction==LEFT){
		 head=new Point((maxPosition+minPosition)/SCALE-1,head.y);
		 System.out.println("BUGGONI LEFT: "+maxPosition+minPosition+headX+headY);
}
else if(headX>=(maxPosition+minPosition)/SCALE-1 && direction==RIGHT){
		 head=new Point(minPosition/SCALE+1,head.y);
		 System.out.println("BUGGONI RIGHT: "+maxPosition+" "+minPosition+" "+headX+" "+headY);
}
else if(headY<=minPosition/SCALE && direction==UP){
		 head=new Point(head.x,(maxPosition+minPosition)/SCALE-1);
		 System.out.println("BUGGONI UP: "+maxPosition+minPosition+headX+headY);
}
else if(headY>=(maxPosition+minPosition)/SCALE-1 && direction==DOWN){
		 head=new Point(head.x,minPosition/SCALE+1);
		 System.out.println("BUGGONI DOWN: "+maxPosition+minPosition+headX+headY);
}//fine borders
else if(direction==UP){
  head=new Point(head.x,head.y-1);
 }
 else if(direction==DOWN){
 head=new Point(head.x,head.y+1);
 }
 else if(direction==LEFT){
 head=new Point(head.x-1,head.y);
 }
 else if(direction==RIGHT){
 head=new Point(head.x+1,head.y);
}

///robe nuove

try{	



if(score>HighScore){
	HighScore=score;
setHighScore(HighScore);
}
}catch(IOException e){}
   
   
   


//

repaint();



}
};


public void paintComponent(Graphics g){
	super.paintComponent(g);
	
	Color co = new Color(R,G,B);
	
	if(score!=0 && score%5==0){
	g.setColor(co);
	}
	else{
	g.setColor(Color.GREEN)	; 
	}////////////
	//g.fillRect(head.x,head.y,10,10);
	
	//SNAKE
	for(Point point : codaSnake){
	  g.fillRect(point.x*SCALE,point.y*SCALE,SCALE,SCALE);
		
	}
	//TITOLO
	if(score!=0 && score%5==0){
		g.setColor(co);
	}else{		
    g.setColor(Color.GREEN);///////////	
	}
	g.setFont(new Font("TimesRoman", Font.ROMAN_BASELINE, 50));
	g.drawString("->SnAKe PsiCHeDeLiCo<-",220,50);
	g.drawRect(215,55,565,2);
	
	
	//SCORE
	g.setColor(Color.WHITE);                             
	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	g.drawString("SCORE:",10,20);
	g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
	g.drawString(Integer.toString(score),10,50);
	g.setColor(Color.WHITE);
	g.drawRect(0,0,90,60);
	
	//HighSCORE
	g.setColor(Color.WHITE);                             
	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	g.drawString("HIGHSCORE:",880,20);
	g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
	g.drawString(Integer.toString(HighScore),880,50);
	g.setColor(Color.WHITE);
	g.drawRect(875,0,125,60);
	;
	//CHERRY
	g.setColor(Color.RED);
	g.drawRect(cherryPositionX,cherryPositionY,cherrySize,cherrySize);
	//bonusCherry
	if(score!=0 && score%5==0){
	g.setColor(co);
	g.drawOval(cherryPositionX-5,cherryPositionY-5,cherrySize+10,cherrySize+10);
	g.fillOval(cherryPositionX-5,cherryPositionY-5,cherrySize+10,cherrySize+10);
	}
	
	//BORDERS
	g.setColor(Color.WHITE);
	g.drawRect(minPosition,minPosition,maxPosition,maxPosition);
	//GameOver
	if(gameOver==true){
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
	    g.drawString("GAME OVER!",200,500);
	}
	
	
}
 public class MouseClass extends MouseAdapter{

 
	public void mouseClicked(MouseEvent event){
    int x=event.getX()/SCALE;
	int y=event.getY()/SCALE;
	
	System.out.println("DIRECTION="+direction);
	System.out.println(head.x+" "+head.y);
	System.out.println("mouse="+x+" "+y);
    
	
	if(direction==UP){ //UP=0;
		if(x>head.getX()){direction=RIGHT;} //RIGHT=3;
		else if(x<head.getX()){direction=LEFT;}  //LEFT=2;
	}
	else if(direction==DOWN){
		if(x>head.getX()){direction=RIGHT;}
		else if(x<head.getX()){direction=LEFT;}
	}
	else if(direction==LEFT){
		if(y>head.getY()){direction=DOWN;}  //DOWN=1;
		else if(y<head.getY()){direction=UP;}
	}
		else if(direction==RIGHT ){
		if(y>head.getY()){direction=DOWN;}
		else if(y<head.getY()){direction=UP;}
	}
	
	System.out.println(" NEW DIRECTION="+direction);
	System.out.println();
		
}


}
class KeyClass extends KeyAdapter{
public void keyPressed(KeyEvent e){

int keyCode= e.getKeyCode();

 System.out.println("keycode= "+keyCode);
int keyUp= KeyEvent.VK_UP;
 System.out.println("keyUp="+keyUp);
int keyDown= KeyEvent.VK_DOWN;
 System.out.println("keyDown="+keyDown);
int keyRight= KeyEvent.VK_RIGHT; 
System.out.println("keyRigth="+keyRight);
int keyLeft= KeyEvent.VK_LEFT;
 System.out.println("keyLeft="+keyLeft);


}
}
}