public class Enemy
{
	int x;
	int y;
	int w;
	int h;
	Enemy(int x,int y,int w,int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	
	}
	void moveRight()
	{
		x+=1;
	}
	void moveLeft()
        {
                x-=1;
        }
	void moveUp()
        {
                y-=1;
        }
	void moveDown()
        {
                y+=1;
        }
}
