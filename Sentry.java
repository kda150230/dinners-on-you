//Import statements
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


//playerOne sentry will be a red circle with a white triangle on the inside
class Sentry {

	//Class variables
	final int radius = 10;
	double x, y, angle, drawAngle, rotationalSpeed;
	boolean turningLeft, turningRight, active;
	int[] xCirclePts, yCirclePts, gunXPts, gunYPts;
	int shotDelay, shotDelayLeft, health;
	boolean alive = false;
	int defaultHealth, playerID;

	//Color declaration (BLUE)
	Color blue = new Color(0, 153, 255);

	//Sentry coordinates
	int[] origGunX = {15,0,0};
	int[] origGunY = {0,10,-10};


	/*Primary Constructor*/
	public Sentry(double x, double y, double angle, double drawAngle, double rotationalSpeed, int shotDelay, int inHealth, int inPlayerID)
	{
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.drawAngle = drawAngle;
		this.rotationalSpeed=rotationalSpeed;
		turningLeft=false;
		turningRight=false;
		active=false;
		gunXPts=new int[3];
		gunYPts=new int[3];
		this.shotDelay=shotDelay;
		shotDelayLeft=0;
		health=inHealth;
		defaultHealth=inHealth;
		playerID = inPlayerID;
	}


	public void draw(Graphics g)
	{
		if(alive==true)
		{

			if(active==true)
				g.setColor(Color.white);
			else
				g.setColor(Color.darkGray);

			g.fillOval((int)getX() - 15, (int)getY() - 15, 30, 30);

			//Calculate rotation for ship and draw
			for(int i=0; i<3; i++)
			{
				gunXPts[i]=(int)(origGunX[i]*Math.cos(drawAngle)-origGunY[i]*Math.sin(drawAngle)+x+.5);
				gunYPts[i]=(int)(origGunX[i]*Math.sin(drawAngle)+origGunY[i]*Math.cos(drawAngle)+y+.5);
			}

			//Sets ship color based on playerID
			if(playerID==1){
			g.setColor(Color.red);
			}
			if(playerID==2){
			g.setColor(blue);
			}

			g.fillPolygon(gunXPts,gunYPts,3);
		}

	} //draw()



	public void move()
	{
		if(alive==true)
		{
			if(shotDelayLeft>0)
				shotDelayLeft--;
			if(turningLeft)
				angle-=rotationalSpeed;
			if(turningRight)
				angle+=rotationalSpeed;
			if(angle>(2*Math.PI))
				angle-=(2*Math.PI);
			else if(angle<0)
				angle+=(2*Math.PI);
		}

	} //Closes move()


	public boolean getAlive()
	{
		return alive;
	}


	public void setAlive(boolean alive)
	{
		this.alive=alive;
	}


	public int getHealth()
	{
		return health;
	}


	public void setHealth(int health)
	{
		this.health=health;
	}


	public double getX(){
		return x;
	}


	public double getY(){
		return y;
	}


	public double getDrawAngle()
	{
		return drawAngle;
	}


	public void setDrawAngle(double inDrawAngle)
	{
		drawAngle = inDrawAngle;
	}


	public void setX(double newX){
		x=newX;
	}


	public void setY(double newY){
		y=newY;
	}


	public void setActive(boolean active){
		this.active=active;
	}


	public boolean isActive(){
		return active;
	}


	public boolean canShoot()
	{
		if(alive==false)
			return false;
		if(active==false)
			return false;
		if(shotDelayLeft>0)
			return false;
		else
			return true;
	}


	public Shot shootPlayerOneSentry()
	{
		shotDelayLeft = shotDelay; //Set delay till next shot can be fired
		return new Shot(x, y, angle/*+90*/, 0, 0, 150, 1);
	}


	public Shot shootPlayerTwoSentry()
	{
		shotDelayLeft = shotDelay;
		return new Shot(x, y, angle, 0, 0, 150, 2);
	}


	public Rectangle getBounds(){
		Rectangle r;
		r = new Rectangle((int)getX() - 15, (int)getY() - 15, 30, 30);
		return r;
	}


	public double getAngle()
	{
		return angle;
	}


	public void setAngle(double angle)
	{
		this.angle=angle;
	}


	public int getDefaultHealth()
	{
		return defaultHealth;
	}


} //Closes Sentry.java