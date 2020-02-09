import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Base {

	//Class variables
	final int radius = 10;
	double x, y, gun1PosX, gun1PosY, gun2PosX, gun2PosY; //base will hopefully have 2 guns on it
	boolean active; //active is just for gray or not
	int[] outerCircleXPts, outerCircleYPts, innerCircleXPts, innerCircleYPts;
	int health, defaultHealth, teamID; //1 is red, 2 is blu
	boolean alive = true;
	int regenDelay;
	int regenDelayLeft = 0;

	//Color declaration (BLUE)
	Color blue = new Color(0, 153, 255);


	public Base(int inHealth, int inTeamID, int regenDelay)
	{
		active=true;
		health=inHealth;
		teamID = inTeamID;
		defaultHealth = inHealth;
		this.regenDelay=regenDelay;
		regenDelayLeft=0;

		//HARD-CODED SCREEN SIZE FOR LOCATION AT THE EDGES!!!
		if(teamID==1)
		{
			x = 0;//left center side of screen
			y = 300;
		}
		if(teamID==2)
		{
			x = 1000;//right center side of screen
			y = 300;
		}
	}

	public void move()
	{
		if(regenDelayLeft>0)
			regenDelayLeft--;
	}

	public void draw(Graphics g)
	{
		if(alive==true)
		{
			//Outer circle(RECTANGLE???) that the other team shoots at
			g.setColor(Color.gray);
			if(teamID==1)
			g.fillRect((int)getX() - 150, (int)getY() - 100, 200, 200);
			if(teamID==2)
			g.fillRect((int)getX() - 50,  (int)getY() - 100, 200, 200);


			if(active==true)
			{
				if(teamID==1)
					g.setColor(Color.red);
				if(teamID==2)
					g.setColor(blue);
			}
			else
				g.setColor(Color.darkGray);
			//inner circle where the player spawns and returns to for health and (ammo?).
			g.fillOval((int)getX() - 30, (int)getY() - 30, 60, 60);


		}

	} //draw()

	public double getX(){
		return x;
	}


	public double getY(){
		return y;
	}

	public boolean getAlive()
	{
		return alive;
	}

	public int getHealth(){
		return health;
	}

	public int getDefaultHealth(){
		return defaultHealth;
	}


	public void setAlive(boolean alive)
	{
		this.alive=alive;
	}

	public void setHealth(int health)
	{
		this.health=health;
	}

	public void addHealth(int healthAdd)
	{
		health = health + healthAdd;
	}

	public void setActive(boolean active){
		this.active=active;
	}


	public boolean isActive(){
		return active;
	}

	public Rectangle getBoundsInner(){
		Rectangle r;
		r = new Rectangle((int)getX() - 30, (int)getY() - 30, 60, 60);
		return r;
	}

	public Rectangle getBoundsOuter(){
		Rectangle r;
		r=null;

		if(teamID==1)
			r = new Rectangle((int)getX() - 150, (int)getY() - 100, 200, 200);
		if(teamID==2)
			r = new Rectangle((int)getX() - 50,  (int)getY() - 100, 200, 200);

		return r;
	}

	public boolean canRegen()
	{
		if(alive==false)
			return false;
		if(active==false)
			return false;
		if(regenDelayLeft>0)
			return false;
		if(health>=defaultHealth)
			return false;
		else
			return true;
	}

}//class
