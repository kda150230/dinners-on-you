//Import statements
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//Class for explosion animation / damage calculations
public class Explosion
{
	//Class variables
	int x, y, playerID, radius = 10;
	boolean active, alive;
	long currentTime = System.currentTimeMillis();
	long desiredTime;

	//Color declaration (BLUE)
	Color blue = new Color(0, 153, 255);

	/*Constructor*/
	public Explosion(int x, int y, int radius, int playerID)
	{
		this.x = x;
		this.y = y;
		active = false;
		alive = false;
		this.playerID = playerID;
		this.radius = radius;
	}


	/*Drawing Config*/
	public void draw(Graphics g)
	{
		if(alive==true)
		{
			if(desiredTime == 0)
				desiredTime = System.currentTimeMillis() + 2000;

			if(desiredTime > System.currentTimeMillis())
			{
				//Draws animation for explosion at point of impact
				if(playerID==2)
					g.setColor(blue);
				if(playerID==1)
					g.setColor(Color.red);

				g.fillOval(x-radius, y-radius, radius*2, radius*2);
			}

			alive = false;
			desiredTime = 0;
		}

	} //Closes draw()


	/*Accessor Methods*/
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public boolean getAlive()
	{
		return alive;
	}

	public int getRadius()
	{
		return radius;
	}


	/*Modifier Methods*/
	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}

	public void setPlayerID(int playerID)
	{
		this.playerID = playerID;
	}

	public void setRadius(int radius)
	{
		this.radius = radius;
	}


}