package Snake;
import javax.swing.JFrame;
import java.awt.Color;

public class SnakeFrame extends JFrame{

SnakePanel snakePanel;
public SnakeFrame(){
	
JFrame frame=new JFrame();
snakePanel=new SnakePanel();
frame.setVisible(true);
frame.setTitle("SNAKE!");
frame.setSize(800,600);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.add(snakePanel);
frame.setFocusable(true);
frame.pack();


}
}