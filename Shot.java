//Import statements
import java.awt.*;
import java.awt.Rectangle;


public class Shot
{
	//Class variables
	final double shotSpeed = 6;
	double x, y, xVelocity, yVelocity;
	int lifeLeft, playerID;

	//Color declaration (BLUE)
	Color blue = new Color(0, 153, 255);


	/*Primary Constructor*/
	public Shot(double x, double y, double angle, double playerOneXVel,
			double playerOneYVel, int lifeLeft, int inPlayerID)
			{
				this.x=x;
				this.y=y;
				xVelocity=shotSpeed*Math.cos(angle)+playerOneXVel;
				yVelocity=shotSpeed*Math.sin(angle)+playerOneYVel;
				this.lifeLeft = lifeLeft;
				this.playerID = inPlayerID;
			}


	public void move(int scrnWidth, int scrnHeight)
	{
		lifeLeft--;

		x+=xVelocity;
		y+=yVelocity;

		if(x<0) //To wrap the shot to the other side (NOT USED; BUT NEEDED)
		{
			x+=scrnWidth;
			lifeLeft = lifeLeft - lifeLeft;
		}
		else if(x>scrnWidth)
		{
			x-=scrnWidth;
			lifeLeft = lifeLeft - lifeLeft;
		}
		if(y<0)
		{
			y+=scrnHeight;
			lifeLeft = lifeLeft - lifeLeft;
		}
		else if(y>scrnHeight)
		{
			y-=scrnHeight;
			lifeLeft = lifeLeft - lifeLeft;
		}

	} //Closes move()


	public void draw(Graphics g)
	{
		//Colors shots based on playerID
		if(playerID==1){
		g.setColor(Color.red);
		}
		if(playerID==2){
		g.setColor(blue);
		}

		g.fillOval((int)(x-.5), (int)(y-.5), 3, 3);
	}


	public int getLifeLeft()
	{
		return lifeLeft;
	}


	public void setLifeLeft(int inLife)
	{
		lifeLeft = inLife;
	}


	public double getX(){
		return x;
	}


	public double getY(){
		return y;
	}


	public Rectangle getBounds(){
		Rectangle r;
		r = new Rectangle((int)getX() - 2, (int)getY() - 2, 2, 2);
		return r;
	}

} //Closes Shot.java