//Import statements
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//Class for player dropped mines
public class Mine
{
	//Class variables
	int x, y, detRadius, expRadius, playerID;
	boolean active, alive, armed = false, activated = false, allowed;
	String lightColor;

	//Color declaration (BLUE)
	Color blue = new Color(0, 153, 255);
	Color red = new Color(255, 0, 0);
	Color green = new Color(34, 177, 76);
	Color gray = new Color(127, 127, 127);

	/*Constructor*/
	public Mine(int x, int y, int detRadius, int expRadius, int playerID, String lightColor, boolean activated, boolean allowed)
	{
		this.x = x;
		this.y = y;
		this.detRadius = detRadius;
		this.expRadius = expRadius;
		this.playerID = playerID;
		this.lightColor = lightColor;
		this.activated = activated;
		this.allowed = allowed;
		active = false;
	}


	/*Drawing Config*/
	public void draw(Graphics g)
	{
		if(alive==true)
		{
			//Colors player indicator ring
			if(playerID==2){ g.setColor(blue); }
			else { g.setColor(Color.red); }

			g.fillOval(x-7, y-7, 14, 14);

			//Colors inside gray ring
			g.setColor(Color.darkGray);
			g.fillOval(x-5, y-5, 10, 10);

			//Colors the inside detonator indic. light
			if(lightColor.equals("green"))
				g.setColor(Color.green);
			if(lightColor.equals("red"))
				g.setColor(red);
			if(lightColor.equals("gray"))
				g.setColor(gray);

			g.fillOval(x-3, y-3, 6, 6);


		} //Closes if(alive)

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

	public int getDetRadius()
	{
		return detRadius;
	}

	public int getExpRadius()
	{
		return expRadius;
	}

	public boolean getArmed()
	{
		return armed;
	}

	public String getColor()
	{
		return lightColor;
	}

	public boolean getActivated()
	{
		return activated;
	}

	public boolean getActive()
	{
		return active;
	}

	public boolean getAllowed()
	{
		return allowed;
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

	public void setArmed(boolean armed)
	{
		this.armed = armed;
	}

	public void setDetRadius(int detRadius)
	{
		this.detRadius = detRadius;
	}

	public void setColor(String lightColor)
	{
		this.lightColor = lightColor;
	}

	public void setActivated(boolean activated)
	{
		this.activated = activated;
	}

	public void setAllowed(boolean allowed)
	{
		this.allowed = allowed;
	}

} //Closes Mine.java