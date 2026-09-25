import javax.swing.JFrame;
public class Main{
	public static void main(String[] args){
	JFrame window=new JFrame();
	window.setSize(800,600);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setTitle("MY game");
	GamePanel gp=new GamePanel();
	window.add(gp);
	window.setVisible(true);
	}
}
