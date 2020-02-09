//a small target the player can deploy, like the sentry, just to guide the destroyer.
//I don't know if this should be killable yet.

//The destroyer will NOT move and rotate at the same time,
//if it is not headed STRAIGHT at the waypoint, it will STOP and ROTATE slowly until it is facing
//the waypoint before accelerating. It will look like a small target.

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Waypoint {
	//class variables
	double x, y;
	boolean active;
	int[] outerCircleXPts, outerCircleYPts, middleCircleXPts, middleCircleYPts, innerCircleXPts, innerCircleYPts;
	int playerID;//1 is red, 2 is blu
	boolean alive = false;
	boolean wayAllowed = true;

	//Color declaration (BLUE)
	Color blue = new Color(0, 153, 255);

	public Waypoint(int playerID)
	{
		active=true;
		this.playerID=playerID;
		x=500;//these coordinates are just default, not actually used or important, but needed
		y=300;
	}

	public void draw(Graphics g)
	{
		if(alive==true)
		{
			//outer-most circle
			if(playerID==1)
				g.setColor(Color.red);
			if(playerID==2)
				g.setColor(blue);
			if(active==false)
				g.setColor(Color.darkGray);

			g.fillOval((int)getX() - 8, (int)getY() - 8, 16, 16);

			//middle circle
			g.setColor(Color.white);
			g.fillOval((int)getX() - 5, (int)getY() - 5, 10, 10);

			//iner-most circle
			if(playerID==1)
				g.setColor(Color.red);
			if(playerID==2)
				g.setColor(blue);
			if(active==false)
				g.setColor(Color.darkGray);

			g.fillOval((int)getX() - 2, (int)getY() - 2, 4, 4);


		}

	} //draw()

	public double getX(){
		return x;
	}


	public double getY(){
		return y;
	}

	public void setY(double y){
		this.y = y;
	}

	public void setX(double x){
		this.x = x;
	}

	public boolean getAlive()
	{
		return alive;
	}

	public void setAlive(boolean alive)
	{
		this.alive=alive;
	}

	public void setActive(boolean active){
		this.active=active;
	}


	public boolean isActive(){
		return active;
	}

	public boolean getWayAllowed()
	{
		return wayAllowed;
	}

	public void setWayAllowed(boolean wayAllowed)
	{
		this.wayAllowed = wayAllowed;
	}

}//class
