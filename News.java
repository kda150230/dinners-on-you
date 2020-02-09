//Import statements
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//Class for ticket alert messages
public class News
{
	//Class variables
	int x, y;
	boolean alive;
	String message = "Hello World!";

	/*Constructor*/
	public News(int x, int y, boolean alive, String message)
	{
		this.x = x;
		this.y = y;
		this.alive = alive;
		this.message = message;
	}


	/*Drawing Config*/
	public void draw(Graphics g)
	{

		g.drawString(message, x, y);

	} //Closes draw()


	/*Move Method*/
	public void move()
	{
		x++;
	}


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

	public String getMessage()
	{
		return message;
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

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}


} //Closes Alert.java