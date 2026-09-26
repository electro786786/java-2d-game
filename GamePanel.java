import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
public class GamePanel extends JPanel{
	Player player;
	InputHandler input;
	ArrayList<Wall> walls;
	ArrayList<Coin> coins;
	Enemy enemy;
	ExitDoor door;
	int wallX=300;
	int wallY=200;
	int wallH=50;
	int wallW=100;
	int oldX;
	int oldY;
	int c=0;
	int flag=1;
	boolean gameOver=false;
	GamePanel(){
		player=new Player();
		input=new InputHandler();
		walls=new ArrayList<>();
		enemy=new Enemy(600,300,50,50);
		coins=new ArrayList<>();
		for(int i=1;i<=6;i++)
		{
			for(int j=1;j<=6;j++)
			{
		coins.add(new Coin(i*50,j*50,20,35));
		}
	        }

		walls.add(new Wall(380,0,40,275));
		walls.add(new Wall(380,335,40,600));
		addKeyListener(input);
		setFocusable(true);
		Timer timer=new Timer(17,e->{
			if(gameOver)
				return;
			oldX=player.x;
			int oldEnemX=enemy.x;
                        if(player.x-enemy.x<0)
                        {
                                enemy.moveLeft();
                        }
                        if(player.x-enemy.x>0)
                        {
                                enemy.moveRight();
                        }
                       
			if(input.right==true)
				player.moveRight();
			if(input.left==true)
                                player.moveLeft();
                        Rectangle playRect=new Rectangle(player.x,player.y,player.w,player.h);
			Rectangle enemyRect=new Rectangle(enemy.x,enemy.y,enemy.w,enemy.h);
			Rectangle wallRect;
			for(Wall wall : walls)
			{
                        wallRect=new Rectangle(wall.x,wall.y,wall.w,wall.h);
			if(playRect.intersects(wallRect))
                        {
                                player.x=oldX;
                                
                        }
			if(enemyRect.intersects(wallRect))
					{
						enemy.x=oldEnemX;
					}
			if(enemyRect.intersects(playRect))
                                        {
                                                flag=0;
                                        }
			}
			oldY=player.y;
                        int oldEnemY=enemy.y;
			if(player.y-enemy.y<0)
                        {
                                enemy.moveUp();
                        }
                        if(player.y-enemy.y>0)
                        {
                                enemy.moveDown();
                        }
			if(input.up==true)
                                player.moveUp();
			if(input.down==true)
                                player.moveDown();
			player.checkBoundaries(getWidth(),getHeight());
			 playRect=new Rectangle(player.x,player.y,player.w,player.h);
			 enemyRect=new Rectangle(enemy.x,enemy.y,enemy.w,enemy.h);
	                 Iterator<Coin> iterator=coins.iterator();
		        for(Wall wall:walls)
			{
			wallRect=new Rectangle(wall.x,wall.y,wall.w,wall.h);
			if(playRect.intersects(wallRect))
			{
				
				
				player.y=oldY;
			}
			if(enemyRect.intersects(wallRect))
                        {


                                enemy.y=oldEnemY;
                        }
			if(enemyRect.intersects(playRect))
                                        {
                                                flag=0;
                                        }
			}
		        while(iterator.hasNext())
			{
				Coin coin=iterator.next();
				Rectangle coinRect=new Rectangle(coin.x,coin.y,coin.w,coin.h);
				if(playRect.intersects(coinRect))
				{
					c++;
					iterator.remove();
				}
			
			}
			if(c==36)
			{
                        door=new ExitDoor(getWidth()-10,0,10,50);
			Rectangle exitRect=new Rectangle(door.x,door.y,door.w,door.h);
			
			if(playRect.intersects(exitRect))
			{
				flag=-1;
				
			}
			}
			

			repaint();});
		timer.start();
	}
           @Override
	   protected void paintComponent(Graphics g)
	   {
		   super.paintComponent(g);
		   g.setColor(Color.BLUE);
		   g.fillRect(player.x,player.y,player.w,player.h);
		   for(Wall wall:walls)
		   {
	           g.setColor(Color.BLACK);
		   g.fillRect(wall.x,wall.y,wall.w,wall.h);
		   }
		   for(Coin coin:coins)
		   {
			   g.setColor(Color.YELLOW);
			   g.fillOval(coin.x,coin.y,coin.w,coin.h);
		   }
		   g.setColor(Color.RED);
                   g.fillRect(enemy.x,enemy.y,enemy.w,enemy.h);
		   if(c==36)
		   {
			   g.setColor(Color.GREEN);
			   g.fillRect(door.x,door.y,door.w,door.h);
		   }
		   g.drawString("Coins Collected: "+c,20,30);
		   if(flag==0){
			   g.setFont(new Font("Arial", Font.BOLD,100));
			   g.drawString(" You Lose ", 100,300);
			   gameOver=true;
		   }
		   if(flag==-1){
                           g.setFont(new Font("Arial", Font.BOLD,100));
                           g.drawString(" You Win ", 100,300);
			   gameOver=true;
                   }
	   }
}

