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
import java.util.Random;


public class SnakePanel2 extends JPanel{


public static final int DEFAULT_W=1000;
public static final int DEFAULT_H=1000;
private int timerDelay;
private Timer timer;
public ArrayList<Point> codaSnake = new ArrayList<Point>();

public final int UP=0,DOWN=1,LEFT=2,RIGHT=3;
public int direction=3;
public Point head;
public final int SCALE=10;
public int score=0;
public int minL=3;

public int cherrySize=20;
public int cherryPositionX=100;
public int cherryPositionY=100;
public int maxPosition=800;
public int minPosition=100;

int R;
int G;
int B;



public SnakePanel(){
MouseClass mouse = new MouseClass();
this.setPreferredSize(new Dimension(DEFAULT_W, DEFAULT_H));
this.setVisible(true);
this.setBackground(Color.BLACK);
this.setFocusable(true);
this.addMouseListener(mouse);


head=new Point(20,20);  //START POINT<-------------

timerDelay=10;         //TIMER SPEED<-------------
timer= new Timer(timerDelay,timerAction);
timer.start();








}

ActionListener timerAction=new ActionListener(){

public void actionPerformed(ActionEvent event){
	Random r=new Random();
		codaSnake.add(new Point(head.x,head.y));
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
		}
		R=r.nextInt(256);
        G=r.nextInt(256);
        B=r.nextInt(256);
		
		

if(direction==UP){
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
repaint();

}
};


public void paintComponent(Graphics g){
	super.paintComponent(g);
	
	Color co = new Color(R,G,B);
	
	g.setColor(co);
	//g.fillRect(head.x,head.y,10,10);
	
	//SNAKE
	for(Point point : codaSnake){
	  g.fillRect(point.x*SCALE,point.y*SCALE,SCALE,SCALE);
		
	}
	//SCORE
	g.setColor(Color.WHITE);                             
	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	g.drawString("SCORE:",10,20);
	g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
	g.drawString(Integer.toString(score),10,50);
	g.setColor(Color.WHITE);
	g.drawRect(0,0,90,60);
	
	//CHERRY
	g.setColor(Color.RED);
	g.drawRect(cherryPositionX,cherryPositionY,cherrySize,cherrySize);
	
	//BORDERS
	g.setColor(Color.WHITE);
	g.drawRect(minPosition,minPosition,maxPosition,maxPosition);
	
	
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
}