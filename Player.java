public class Player{
	int x;
	int y;
	int h;
	int w;
	Player(){
		x=200;
		y=200;
		h=50;
		w=50;
	}
	void moveRight()
	{
		x+=2;
	}
	void moveLeft()
        {
                x-=2;
        }
	void moveUp()
        {
                y-=2;
        }
	void moveDown()
        {
                y+=2;
	} 
	void checkBoundaries(int swidth,int sheight)
	{
		if(x<0)
			x=0;
		if(y<0)
			y=0;
		if(x+w>swidth)
			x=swidth-w;
		if(y+h>sheight)
			y=sheight-h;
	
	}
}
