//Import statements
import java.awt.*;
import java.util.*;


public class Destroyer
{
	//Class variables

	//Coordinates for drawing the ship/flames
	final double[] origXPts = {-25,-15,-30,-15,-25,30}, origYPts = {-16,-7,0,7,16,0},
		origFlame1XPts = {-15,-15,-45},//Left flame
		origFlame1YPts = {-3,-11,-7},
		origFlame2XPts = {-15,-15,-45},//Right flame
		origFlame2YPts = {3,11,7},
		origFlame1StoppedXPts = {-15,-15,-27},//Left flame while not accelerating
		origFlame1StoppedYPts = {-3,-11,-7},
		origFlame2StoppedXPts = {-15,-15,-27},//Right flame while not accelerating
		origFlame2StoppedYPts = {3,11,7};


	//Constructor Variables
	double x, y, angle, xVelocity, yVelocity, acceleration,	velocityDecay, rotationalSpeed;
	boolean turningLeft, turningRight, accelerating, active;
	int[] xPts, yPts, flame1XPts, flame1YPts, flame2XPts, flame2YPts, flame3XPts, flame3YPts;
	int playerID, health, defaultHealth;

	//Color declaration (BLUE)
	Color blue = new Color(0, 153, 255);

	//directions are in relevence to the destroyer as it is facing upwards with the flames facing down
	//these are going to be the positions of the four Turrets on the destroyer
	int bottomLeftX, bottomLeftY;
	int bottomRightX, bottomRightY;
	int topLeftX, topLeftY;
	int topRightX, topRightY;
	boolean alive = true;

	/*Primary Constructor*/
	public Destroyer(double x, double y, double angle, double acceleration, double velocityDecay, double rotationalSpeed, int inPlayerID, int inHealth, boolean alive)
				{
					this.x = x;
					this.y = y;
					this.angle = angle;
					this.acceleration = acceleration;
					this.rotationalSpeed = rotationalSpeed;
					xVelocity = 0;
					yVelocity = 0;
					turningLeft = false;
					turningRight = false;
					accelerating = true;//this also determines if the flame is on or off
					active = false;
					xPts = new int[6];
					yPts = new int[6];
					flame1XPts = new int[3];
					flame1YPts = new int[3];
					flame2XPts = new int[3];
					flame2YPts = new int[3];
					playerID = inPlayerID;
					health = inHealth;
					defaultHealth=inHealth;
				}


				public void draw(Graphics g)
				{
					//Rotate points using newX = X*cos(A) – Y*sin(A)

					//Draws flames if accelerating
					if(active)
					{
						if(accelerating)
						{
							//correctly rotates the flame in accordance to the ship
							for(int i=0; i<3; i++)
							{
								flame1XPts[i]=(int)(origFlame1XPts[i] * Math.cos(angle) - origFlame1YPts[i] * Math.sin(angle) + x + .5);
								flame1YPts[i]=(int)(origFlame1XPts[i] * Math.sin(angle) + origFlame1YPts[i] * Math.cos(angle) + y + .5);
							}
							for(int i=0; i<3; i++)
							{
								flame2XPts[i]=(int)(origFlame2XPts[i] * Math.cos(angle) - origFlame2YPts[i] * Math.sin(angle) + x + .5);
								flame2YPts[i]=(int)(origFlame2XPts[i] * Math.sin(angle) + origFlame2YPts[i] * Math.cos(angle) + y + .5);

							}
						}else /*if(accelerating==false)*/
						{

							for(int i=0; i<3; i++)
							{
								flame1XPts[i]=(int)(origFlame1StoppedXPts[i] * Math.cos(angle) - origFlame1StoppedYPts[i] * Math.sin(angle) + x + .5);
								flame1YPts[i]=(int)(origFlame1StoppedXPts[i] * Math.sin(angle) + origFlame1StoppedYPts[i] * Math.cos(angle) + y + .5);
							}
							for(int i=0; i<3; i++)
							{
									flame2XPts[i]=(int)(origFlame2StoppedXPts[i] * Math.cos(angle) - origFlame2StoppedYPts[i] * Math.sin(angle) + x + .5);
								flame2YPts[i]=(int)(origFlame2StoppedXPts[i] * Math.sin(angle) + origFlame2StoppedYPts[i] * Math.cos(angle) + y + .5);
							}
						}



						//Colors flames different based on player ID
						switch(playerID)
						{
							case 1:
								g.setColor(Color.red);
							break;
							case 2:
								g.setColor(blue);
							break;
						}
						g.fillPolygon(flame1XPts, flame1YPts, 3);
						g.fillPolygon(flame2XPts, flame2YPts, 3);
					}

					//Calculate rotation for ship and draw
					for(int i=0; i<6; i++)
					{
						xPts[i]=(int)(origXPts[i]*Math.cos(angle)-origYPts[i]*Math.sin(angle)+x+.5);
						yPts[i]=(int)(origXPts[i]*Math.sin(angle)+origYPts[i]*Math.cos(angle)+y+.5);
					}

					if(active)
						g.setColor(Color.white);
					else
						g.setColor(Color.darkGray);

					g.fillPolygon(xPts, yPts, 6);

				} //Closes draw()


				public void move()
				{
					if(turningLeft)//if true
						angle-=rotationalSpeed;
					if(turningRight)//if true
						angle+=rotationalSpeed;
					if(angle>(2*Math.PI))
						angle-=(2*Math.PI);
					else if(angle<0)
						angle+=(2*Math.PI);
					if(accelerating)
					{
						xVelocity+=acceleration*Math.cos(angle);
						yVelocity+=acceleration*Math.sin(angle);
					}
					x+=xVelocity;
					y+=yVelocity;
					xVelocity*=velocityDecay;
					yVelocity*=velocityDecay;

				}


				public int getHealth()
				{
					return health;
				}


				public void setHealth(int health)
				{
					this.health=health;
				}


				public void setAccelerating(boolean accelerating){
					this.accelerating=accelerating;
				}


				public void setTurningLeft(boolean turningLeft){
					this.turningLeft=turningLeft;
				}


				public void setTurningRight(boolean turningRight){
					this.turningRight=turningRight;
				}

				public boolean getAccelerating(){
					return accelerating;
				}


				public double getX(){
					return x;
				}


				public double getY(){
					return y;
				}


				/*******
				 *DEBUG
				 ******/
				public void setX(double newX){
					x=newX;
				}

				/*******
				 *DEBUG
				 ******/
				public void setY(double newY){
					y=newY;
				}


				public void setActive(boolean active){
					this.active=active;
				}


				public boolean isActive(){
					return active;
				}

				public void setAlive(boolean alive){
					this.alive = alive;
				}

				public boolean getAlive(){
					return alive;
				}

				public int getDefaultHealth()
				{
					return defaultHealth;
				}

				public void setAngle(double angle)
				{
					this.angle=angle;
				}

				public double getAngle()
				{
					return angle;
				}

				public Polygon getBounds(){
					Polygon p;
					p = new Polygon(xPts, yPts, 6);
					return p;
				}


} //Closes Destroyer.java