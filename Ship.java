//Import statements
import java.awt.*;


public class Ship
{
	//Class variables

	//Coordinates for drawing the ship/flames
	final double[] origXPts = {14,-10,-6,-10}, origYPts = {0,-8,0,8},
		origFlameXPts = {-6,-23,-6}, origFlameYPts = {-3,0,3};
	//Constructor Variables
	double x, y, angle, xVelocity, yVelocity, acceleration, defaultAcceleration, velocityDecay, rotationalSpeed;

	boolean turningLeft, turningRight, accelerating, active;
	int[] xPts, yPts, flameXPts, flameYPts;
	int shotDelay, shotDelayLeft, playerID, health, defaultHealth, ammo, defaultAmmo;
	boolean shotsAllowed=true;
	boolean sentryAllowed = true;
	boolean stun = false;

	//Color declaration (BLUE)
	Color blue = new Color(0, 153, 255);

	//Color declaration for ship shades
	Color fiveHealth = new Color(255, 255, 255);
	Color fourHealth = new Color(215, 215, 215);
	Color threeHealth = new Color(165, 165, 165);
	Color twoHealth = new Color(125, 125, 125);
	Color oneHealth = new Color(85, 85, 85);
	Color noHealth = new Color(45, 45, 45);


	/*Primary Constructor*/
	public Ship(double x, double y, double angle, double acceleration,
				double velocityDecay, double rotationalSpeed,
				int shotDelay, int playerID, int health, int ammo, boolean stun)
				{
					this.x = x;
					this.y = y;
					this.angle = angle;
					this.acceleration = acceleration;
					this.defaultAcceleration = acceleration;
					this.rotationalSpeed = rotationalSpeed;
					this.shotDelay = shotDelay;
					this.playerID = playerID;
					this.health = health;
					this.ammo = ammo;
					this.stun = stun;

					xVelocity = 0;
					yVelocity = 0;
					turningLeft = false;
					turningRight = false;
					accelerating = false;
					active = false;
					xPts = new int[4];
					yPts = new int[4];
					flameXPts = new int[3];
					flameYPts = new int[3];
					shotDelayLeft = 0;
					defaultHealth = health;
					defaultAmmo = ammo;
				}


			public void draw(Graphics g)
			{
				//Draws flames if accelerating
				if(accelerating && active && !stun)
				{
					//correctly rotates the flame in accordance to the ship
					for(int i=0; i<3; i++)
					{
						flameXPts[i]=(int)(origFlameXPts[i]*Math.cos(angle)-
								origFlameYPts[i]*Math.sin(angle)+
									x+.5);
						flameYPts[i]=(int)(origFlameXPts[i]*Math.sin(angle)+
								origFlameYPts[i]*Math.cos(angle)+
									y+.5);
					}
					//Colors ships different based on player ID
					switch(playerID)
					{
						case 1:
							g.setColor(Color.red);
						break;
						case 2:
							g.setColor(blue);
						break;
					}

					g.fillPolygon(flameXPts, flameYPts, 3);

				}

				//Calculate rotation for ship and draw
				for(int i=0; i<4; i++)
				{
					xPts[i]=(int)(origXPts[i]*Math.cos(angle)-
							origYPts[i]*Math.sin(angle)+
								x+.5);
					yPts[i]=(int)(origXPts[i]*Math.sin(angle)+
							origYPts[i]*Math.cos(angle)+
								y+.5);
				}

				//Changes ship color based on health while active
				if(active)
				{
					if(health == 5)
						g.setColor(fiveHealth);
					if(health == 4)
						g.setColor(fourHealth);
					if(health == 3)
						g.setColor(threeHealth);
					if(health == 2)
						g.setColor(twoHealth);
					if(health == 1)
						g.setColor(oneHealth);
					if(health <= 0)
						g.setColor(noHealth);
				}
				else
					g.setColor(Color.darkGray);

				g.fillPolygon(xPts, yPts, 4);

			} //Closes draw()


			public void move(int scrnWidth, int scrnHeight)
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
				if(accelerating)
				{
					xVelocity+=acceleration*Math.cos(angle);
					yVelocity+=acceleration*Math.sin(angle);
				}
				x+=xVelocity;
				y+=yVelocity;
				xVelocity*=velocityDecay;
				yVelocity*=velocityDecay;

				//Screen wrapping (Warning: Hard coded :s)
				//top/bottom
				if(y>610)
					y = -10;
				if(y< -10)
					y=610;

				//left/right
				if(x>1010)
				{
					angle = angle + Math.PI;
					if(y>300)
					{
						y = 600 - y;
					}
					else if(y<300)
					{
						y = 300 - y;
						y = 300 + y;
					}
				}
				if(x<-10)
				{
					angle = angle + Math.PI;
					if(y>300)
					{
						y = 600 - y;
					}
					else if(y<300)
					{
						y = 300 - y;
						y = 300 + y;
					}
				}

			}

			public boolean getSentryAllowed()
			{
				return sentryAllowed;
			}

			public void setSentryAllowed(boolean sentryAllowed)
			{
				this.sentryAllowed=sentryAllowed;
			}

			public int getDefaultAmmo()
			{
				return defaultAmmo;
			}

			public int getAmmo()
			{
				return ammo;
			}

			public void setAmmo(int ammo)
			{
				this.ammo=ammo;
			}

			public int getHealth()
			{
				return health;
			}

			public void addHealth(int healthAdd)
			{
				health = health + healthAdd;
			}

			public void addAmmo(int ammoAdd)
			{
				ammo = ammo + ammoAdd;
			}

			public void setHealth(int health)
			{
				this.health=health;
			}


			public void setAccelerating(boolean accelerating){
				this.accelerating=accelerating;
			}

			public boolean getAccelerating(){
				return accelerating;
			}


			public void reduceAccleration(double change){
				acceleration -= change;
			}

			public double getAcceleration(){
				return acceleration;
			}

			public void setAcceleration(double acceleration){
				this.acceleration = acceleration;
			}

			public void setStun(boolean stun){
				this.stun = stun;
			}

			public boolean getStun(){
				return stun;
			}

			public void setTurningLeft(boolean turningLeft){
				this.turningLeft=turningLeft;
			}

			public void setTurningRight(boolean turningRight){
				this.turningRight=turningRight;
			}

			public double getX(){
				return x;
			}

			public double getY(){
				return y;
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

			public double getAngle()
			{
				return angle;
			}

			public boolean isActive(){
				return active;
			}

			public boolean canShoot(){
				if(shotsAllowed==true)
				{
				if(shotDelayLeft>0||ammo<=0)
					return false;
				else
					return true;
				}else return false;
			}

			public void setShotsAllowed(boolean shotsAllowed){
				this.shotsAllowed=shotsAllowed;
			}

			public int getDefaultHealth()
			{
				return defaultHealth;
			}

			public double getDefaultAcceleration()
			{
				return defaultAcceleration;
			}

			public void setAngle(double angle)
			{
				this.angle=angle;
			}

			public Shot shootPlayerOne()
			{
				shotDelayLeft = shotDelay; //Set delay till next shot can be fired
				ammo--;
				return new Shot(x, y, angle, xVelocity, yVelocity, 45, 1);
			}

			public Shot shootPlayerTwo()
			{
				shotDelayLeft = shotDelay; //Set delay till next shot can be fired
				ammo--;
				return new Shot(x, y, angle, xVelocity, yVelocity, 45, 2);
			}

			public Polygon getBounds(){
				Polygon r;
				r = new Polygon(xPts, yPts, 4);
				return r;
			}


} //Closes Ship.java