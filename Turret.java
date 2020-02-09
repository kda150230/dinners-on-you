//this will be a short ranged version of the sentry, capable of being bound to
//destroyers, bases, or even other ships besides destroyers. They will be a solid triangle of the player's color
//Import statements
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


//playerOne sentry will be a red circle with a white triangle on the inside
class Turret {

	//Class variables
	double x, y, angle, drawAngle, rotationalSpeed;
	boolean turningLeft, turningRight, active, activelyShooting;//activelyshooting will pretty much be active without discoloration for when nothing is within range
	int[] xCirclePts, yCirclePts, gunXPts, gunYPts;
	int shotDelay, shotDelayLeft, health, range, accuracy, shotLife;
	boolean alive = true;
	boolean deployable = false;
	int defaultHealth, playerID;

	//Color declaration (BLUE)
	Color blue = new Color(0, 153, 255);

	//Turret coordinates
	int[] origGunX = {5,-5,-5};
	int[] origGunY = {0,5,-5};


	/*Primary Constructor*/
	public Turret(double x, double y, double angle, double drawAngle, int shotDelay, int inHealth, int inPlayerID, int range, boolean deployable, boolean alive, int accuracy, int shotLife)
	{
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.drawAngle = drawAngle;
		turningLeft=false;
		turningRight=false;
		active=false;
		activelyShooting = false;
		gunXPts=new int[3];
		gunYPts=new int[3];
		this.shotDelay=shotDelay;
		shotDelayLeft=0;
		health=inHealth;
		defaultHealth=inHealth;
		playerID = inPlayerID;
		this.range = range;
		this.deployable=deployable;
		this.alive=alive;
		this.accuracy = accuracy;
		this.shotLife = shotLife;
	}


	public void draw(Graphics g)
	{
		if(alive==true)
		{
			if(deployable==true)
			{
				g.setColor(Color.white);
				g.fillOval((int)getX() - 10, (int)getY() - 10, 20, 20);
			}


			//Calculate rotation for Turret and draw
			for(int i=0; i<3; i++)
			{
				gunXPts[i]=(int)(origGunX[i]*Math.cos(drawAngle)-origGunY[i]*Math.sin(drawAngle)+x+.5);
				gunYPts[i]=(int)(origGunX[i]*Math.sin(drawAngle)+origGunY[i]*Math.cos(drawAngle)+y+.5);

			}

			//Sets Turret color based on playerID
			if(playerID==1)
				g.setColor(Color.red);
			if(playerID==2)
				g.setColor(blue);
			if(active==false)
				g.setColor(Color.darkGray);

			g.fillPolygon(gunXPts,gunYPts,3);

		}

	} //draw()



	public void move()
	{
		if(alive==true)
		{
			if(shotDelayLeft>0)
				shotDelayLeft--;
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

	public int getAccuracy()
	{
		return accuracy;
	}

	public void setAccuracy(int accuracy)
	{
		this.accuracy = accuracy;
	}

	public boolean getDeployable()
	{
		return deployable;
	}

	public void setDeployable(boolean deployable)
	{
		this.deployable=deployable;
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

	public int getShotLife()
	{
		return shotLife;
	}

	public void setShotLife(int shotLife)
	{
		this.shotLife = shotLife;
	}


	public boolean canShoot()
	{
		if(alive==false)
			return false;
		if(active==false)
			return false;
		if(shotDelayLeft>0)
			return false;
		if(activelyShooting==false)
			return false;
		else
			return true;
	}


	public Shot shootPlayerOneTurret()
	{
		if(alive==true&&active==true)
		{
			shotDelayLeft = shotDelay; //Set delay till next shot can be fired
			return new Shot(x, y, angle, 0, 0, shotLife, 1);
			/*Asteroids.bill.setRedShotExpenses(bill.getRedShotExpenses()+bill.getShotCost());*/
		}else
			return null;
	}


	public Shot shootPlayerTwoTurret()
	{
		if(alive==true&&active==true)
		{
			shotDelayLeft = shotDelay;
			return new Shot(x, y, angle, 0, 0, shotLife, 2);
		}else
			return null;
	}


	public Rectangle getBounds(){
		Rectangle r;
		r = new Rectangle((int)getX() - 10, (int)getY() - 10, 20, 20);
		return r;
	}
	//THIS NEEDS TO BE A MORE CIRCULAR SHAPE SUCH AS A HEXAGON, ETC
	public Rectangle getRangeBounds(){ //#######################################################################################################################################################################3
		Rectangle r;
		r = new Rectangle((int)getX() - range, (int)getY() - range, range*2, range*2);
		return r;
	}

	public boolean getActivelyShooting()
	{
		return activelyShooting;
	}

	public void setActivelyShooting(boolean activelyShooting)
	{
		this.activelyShooting = activelyShooting;
	}

	public int getRange()
	{
		return range;
	}

	public void setRange(int range)
	{
		this.range = range;
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

	public boolean getActive()
	{
		return active;
	}


} //Closes Sentry.java