//Import statements
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//Class for star scrolling background
public class Star
{
	//Class variables
	int x, y;
	boolean active, alive;

	/*Constructor*/
	public Star(int x, int y)
	{
		this.x = x;
		this.y = y;
		alive = false;
	}


	/*Drawing Config*/
	public void draw(Graphics g)
	{

		g.setColor(Color.white);
		g.fillRect(x, y, 2, 2);

	} //Closes draw()


	/*Move Method*/
	public void move()
	{
		x++;

		if(x > 1010)
		{
			x = -10;

			//Continually alters stars
			Random plusOrMinusGenX = new Random();
			int plusOrMinusX = plusOrMinusGenX.nextInt(2);

			Random plusOrMinusGenY = new Random();
			int plusOrMinusY = plusOrMinusGenY.nextInt(2);

			Random xChangeGen = new Random();
			int xChange = xChangeGen.nextInt(6);

			Random yChangeGen = new Random();
			int yChange = yChangeGen.nextInt(2);

			if(plusOrMinusX == 1)
			{
				xChange = xChange - (xChange * 2); //Turns into negative integer
				xChange -= 6;
			}else{
				xChange += 6;
			}

			if(plusOrMinusY == 1)
			{
				yChange = yChange - (yChange * 2); //Turns into negative integer
				yChange -= 2;
			}else{
				yChange += 2;
			}

			y += yChange;
			x += xChange;

		}

	} //Closes move()


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

	//Star sector calculation rectangle
	public Rectangle getBounds(int x, int y)
	{
		Rectangle r;
		r = new Rectangle(x-25, y-25, 50, 50);
		return r;
	}


}