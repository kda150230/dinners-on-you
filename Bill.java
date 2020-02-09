import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DecimalFormat;

class Bill {
	int x = 0;
	//CLASS VARIABLES
	DecimalFormat money = new DecimalFormat("$#,###,###.##");
	int winnerID = 0;
	double mealCost = 23.95;
	double taxRate = 0.08;
	double tipRate = 0.20;
	double totalExpenses = 0.0;


	double tipCost = 0.0;
	double taxCost = 0.0;
	double totalRedExpenses = 0.0;
	double totalBlueExpenses = 0.0;
	double totalWarExpenses = 0.0;

	//base war expenses
	double destroyerCost = 500000;//$500,000
	double shotCost = 50;//$50
	double turretCost = 50000;//$50,000
	double baseCost = 500;//$500
	double shipCost = 200000;//$200,000
	double mineCost = 5000;//$5,000

	//red war expenses
	double redDestroyerExpenses = 0.0;
	double redShotExpenses = 0.0;
	double redTurretExpenses = 0.0;
	double redBaseExpenses = 0.0;//repair fees per regen
	double redShipExpenses = 0.0;
	double redMineExpenses = 0.0;
	double redDestroyerFuel = 0.0;
	double redShipFuel = 0.0;

	//blue war expenses
	double blueDestroyerExpenses = 0.0;
	double blueShotExpenses = 0.0;
	double blueTurretExpenses = 0.0;
	double blueBaseExpenses = 0.0;//repair fees per regen
	double blueShipExpenses = 0.0;
	double blueMineExpenses = 0.0;
	double blueDestroyerFuel = 0.0;
	double blueShipFuel = 0.0;

	public Bill(int winnerID)//3 will be default and will act as NULL
	{
		this.winnerID = winnerID;
	}

	public void draw(Graphics g)
	{
		if(x==0)//so it only runs once
		{

			//meal cost
			totalExpenses = totalExpenses + mealCost;

			//total war expenses
			totalRedExpenses = totalRedExpenses + redDestroyerExpenses + redShotExpenses + redTurretExpenses + redBaseExpenses + redShipExpenses + redMineExpenses + redDestroyerFuel + redShipFuel;
			totalBlueExpenses = totalBlueExpenses + blueDestroyerExpenses + blueShotExpenses + blueTurretExpenses + blueBaseExpenses + blueShipExpenses + blueMineExpenses + blueDestroyerFuel + blueShipFuel;


			//blue base was destroyed
			if(winnerID==1)
				totalBlueExpenses = totalBlueExpenses + 3000000;
			//red base was destroyed
			if(winnerID==2)
				totalRedExpenses = totalRedExpenses + 3000000;

			totalWarExpenses = totalRedExpenses + totalBlueExpenses;
			totalExpenses = totalExpenses + totalRedExpenses + totalBlueExpenses;

			//tax payments
			taxCost = taxCost + (totalExpenses * taxRate);

			totalExpenses = totalExpenses + taxCost;

			//tip
			tipCost = tipCost + (totalExpenses * tipRate);

			totalExpenses = totalExpenses + tipCost;


			x++;
		}


		//Color declaration (BLUE)
		Color blue = new Color(0, 153, 255);

		g.setColor(Color.white);
		g.fillRect(325, 15, 350, 570);

		//Sets font
		//g.setFont(new Font("SansSerif", Font.PLAIN, 18));

		switch(winnerID)
		{


			//if BLUE wins
			case 2:
				g.setColor(Color.black);
				g.drawString("Dinner's on RED!", 450, 35);

				g.setColor(Color.red);
				g.drawString("Red's war expenses:", 350, 60);
				g.setColor(Color.black);
				g.drawString("Space station destroyed: $3,000,000", 350, 75);
				g.drawString("Ammunition: " +money.format(redShotExpenses)+"", 350, 90);
				g.drawString("Mines: " +money.format(redMineExpenses)+"", 350, 105);
				g.drawString("Downed destroyers: " +money.format(redDestroyerExpenses)+"", 350, 120);
				g.drawString("Downed ships: " +money.format(redShipExpenses)+"", 350, 135);
				g.drawString("Downed turrets: " +money.format(redTurretExpenses)+"", 350, 150);
				g.drawString("Space station repairs: " +money.format(redBaseExpenses)+"", 350, 165);
				g.drawString("Total expenses: " +money.format(totalRedExpenses)+"", 350, 180);

				g.setColor(blue);
				g.drawString("Blue's war expenses:", 350, 205);
				g.setColor(Color.black);
				g.drawString("Ammunition: " +money.format(blueShotExpenses)+"", 350, 220);
				g.drawString("Mines: " +money.format(blueMineExpenses)+"", 350, 235);
				g.drawString("Downed destroyers: " +money.format(blueDestroyerExpenses)+"", 350, 250);
				g.drawString("Downed ships: " +money.format(blueShipExpenses)+"", 350, 265);
				g.drawString("Downed turrets: " +money.format(blueTurretExpenses)+"", 350, 280);
				g.drawString("Space station repairs: " +money.format(blueBaseExpenses)+"", 350, 295);
				g.drawString("Total expenses: " +money.format(totalBlueExpenses)+"", 350, 310);

				g.setColor(Color.black);
				g.drawString("Total war expenses: " +money.format(totalWarExpenses)+"", 350, 400);
				g.drawString("Total dinner expenses: " +money.format(mealCost)+"", 350, 425);
				g.drawString("Tax due (8%): " +money.format(taxCost)+"", 350, 475);
				g.drawString("Tip (20%): " +money.format(tipCost)+"", 350, 500);

				g.drawString("Total ammount due:      " +money.format(totalExpenses)+"", 350, 575);
				g.setColor(Color.red);
				g.drawOval(470, 560, 100, 20);
				g.drawOval(469, 559, 101, 21);

			break;

			//if RED wins
			case 1:
				g.setColor(Color.black);
				g.drawString("Dinner's on BLUE!", 450, 35);

				g.setColor(blue);
				g.drawString("Blue's war expenses:", 350, 60);
				g.setColor(Color.black);
				g.drawString("Space station destroyed: $3,000,000", 350, 75);
				g.drawString("Ammunition: " +money.format(blueShotExpenses)+"", 350, 90);
				g.drawString("Mines: " +money.format(blueMineExpenses)+"", 350, 105);
				g.drawString("Downed destroyers: " +money.format(blueDestroyerExpenses)+"", 350, 120);
				g.drawString("Downed ships: " +money.format(blueShipExpenses)+"", 350, 135);
				g.drawString("Downed turrets: " +money.format(blueTurretExpenses)+"", 350, 150);
				g.drawString("Space station repairs: " +money.format(blueBaseExpenses)+"", 350, 165);
				g.drawString("Total expenses: " +money.format(totalBlueExpenses)+"", 350, 180);

				g.setColor(Color.red);
				g.drawString("Red's war expenses:", 350, 205);
				g.setColor(Color.black);
				g.drawString("Ammunition: " +money.format(redShotExpenses)+"", 350, 220);
				g.drawString("Mines: " +money.format(redMineExpenses)+"", 350, 235);
				g.drawString("Downed destroyers: " +money.format(redDestroyerExpenses)+"", 350, 250);
				g.drawString("Downed ships: " +money.format(redShipExpenses)+"", 350, 265);
				g.drawString("Downed turrets: " +money.format(redTurretExpenses)+"", 350, 280);
				g.drawString("Space station repairs: " +money.format(redBaseExpenses)+"", 350, 295);
				g.drawString("Total expenses: " +money.format(totalRedExpenses)+"", 350, 310);

				g.setColor(Color.black);
				g.drawString("Total war expenses: " +money.format(totalWarExpenses)+"", 350, 400);
				g.drawString("Total dinner expenses: " +money.format(mealCost)+"", 350, 425);
				g.drawString("Tax due(8%): " +money.format(taxCost)+"", 350, 475);
				g.drawString("Tip(20%): " +money.format(tipCost)+"", 350, 500);

				g.drawString("Total ammount due:      " +money.format(totalExpenses)+"", 350, 575);
				g.setColor(blue);
				g.drawOval(470, 560, 100, 20);
				g.drawOval(469, 559, 101, 21);
			break;

			default:
				g.setColor(Color.black);
				g.drawString("ERROR: winner ID not specified", 350, 35);
				//I will eat my hat if this ever happens
				//Note: At 12:39 AM, this happened. +1 hat Kyle has to eat
			break;
		}
	}

	public void setWinnerID(int winnerID)
	{
		this.winnerID = winnerID;
	}

	public double getMealCost()
	{
		return mealCost;
	}
	public void setMealCost(double mealCost)
	{
		this.mealCost = mealCost;
	}

	public double getTaxRate()
	{
		return taxRate;
	}
	public void setTaxRate(double taxRate)
	{
		this.taxRate = taxRate;
	}

	public double getTipRate()
	{
		return tipRate;
	}
	public void setTipRate(double tipRate)
	{
		this.tipRate = tipRate;
	}

	public double getTotalExpenses()
	{
		return totalExpenses;
	}
	public void setTotalExpenses(double totalExpenses)
	{
		this.totalExpenses = totalExpenses;
	}

	//base war expenses
	public double getDestroyerCost()
	{
		return destroyerCost;
	}
	public void setDestroyerCost(double destroyerCost)
	{
		this.destroyerCost = destroyerCost;
	}

	public double getShotCost()
	{
		return shotCost;
	}
	public void setShotCost(double shotCost)
	{
		this.shotCost = shotCost;
	}

	public double getTurretCost()
	{
		return turretCost;
	}
	public void setTurretCost(double turretCost)
	{
		this.turretCost = turretCost;
	}

	public double getBaseCost()
	{
		return baseCost;
	}
	public void setBaseCost(double baseCost)
	{
		this.baseCost = baseCost;
	}

	public double getShipCost()
	{
		return shipCost;
	}
	public void setShipCost(double shipCost)
	{
		this.shipCost = shipCost;
	}

	public double getMineCost()
	{
		return mineCost;
	}
	public void setMineCost(double mineCost)
	{
		this.mineCost = mineCost;
	}

	//red war costs
	public double getRedDestroyerExpenses()
	{
		return redDestroyerExpenses;
	}
	public void setRedDestroyerExpenses(double redDestroyerExpenses)
	{
		this.redDestroyerExpenses = redDestroyerExpenses;
	}

	public double getRedShotExpenses()
	{
		return redShotExpenses;
	}
	public void setRedShotExpenses(double redShotExpenses)
	{
		this.redShotExpenses = redShotExpenses;
	}

	public double getRedTurretExpenses()
	{
		return redTurretExpenses;
	}
	public void setRedTurretExpenses(double redTurretExpenses)
	{
		this.redTurretExpenses = redTurretExpenses;
	}

	public double getRedBaseExpenses()
	{
		return redBaseExpenses;
	}
	public void setRedBaseExpenses(double redBaseExpenses)
	{
		this.redBaseExpenses = redBaseExpenses;
	}

	public double getRedShipExpenses()
	{
		return redShipExpenses;
	}
	public void setRedShipExpenses(double redShipExpenses)
	{
		this.redShipExpenses = redShipExpenses;
	}

	public double getRedMineExpenses()
	{
		return redMineExpenses;
	}
//silly moose
	public void setRedMineExpenses(double redMineExpenses)
	{
		this.redMineExpenses = redMineExpenses;
	}

	public void addRedDestroyerFuel()
	{
		redDestroyerFuel++;
	}

	public void addRedShipFuel()
	{
		redShipFuel++;
	}

	//blue war expenses
	public double getBlueDestroyerExpenses()
	{
		return blueDestroyerExpenses;
	}
	public void setBlueDestroyerExpenses(double blueDestroyerExpenses)
	{
		this.blueDestroyerExpenses = blueDestroyerExpenses;
	}

	public double getBlueShotExpenses()
	{
		return blueShotExpenses;
	}
	public void setBlueShotExpenses(double blueShotExpenses)
	{
		this.blueShotExpenses = blueShotExpenses;
	}

	public double getBlueTurretExpenses()
	{
		return blueTurretExpenses;
	}
	public void setBlueTurretExpenses(double blueTurretExpenses)
	{
		this.blueTurretExpenses = blueTurretExpenses;
	}

	public double getBlueBaseExpenses()
	{
		return blueBaseExpenses;
	}
	public void setBlueBaseExpenses(double blueBaseExpenses)
	{
		this.blueBaseExpenses = blueBaseExpenses;
	}

	public double getBlueShipExpenses()
	{
		return blueShipExpenses;
	}
	public void setBlueShipExpenses(double blueShipExpenses)
	{
		this.blueShipExpenses = blueShipExpenses;
	}

	public double getBlueMineExpenses()
	{
		return blueMineExpenses;
	}
	public void setBlueMineExpenses(double blueMineExpenses)
	{
		this.blueMineExpenses = blueMineExpenses;
	}

	public void addBlueDestroyerFuel()
	{
		blueDestroyerFuel++;
	}

	public void addBlueShipFuel()
	{
		blueShipFuel++;
	}

} //Closes Bill.java



//		__________
//		|        |
//		|        |
//		|        |
//		|        |
//		|        |
//		|        |
//		==========
//		==========	| "Hello World!"
//		| --  -- |	| _________
//		|  O   O |  |/
//		| \_____/|
//		\        /
//		 --------       ^^^
//		  |     |       | |
//		----------|    /  /
//		|          \  /  /
//		|  |     |\ \/  /
//		|  |     | \   /
//		|  |     |  \_/
//		|  |     |
//		|  |     |
//		VVV=======
//		   |  O  |
//		   =======
//		   |  |  |
//		   |  |  |
//		   |  |  |
//		   |  |  |
//		   |  |  |
//		   |  |  |
//		   =======
//		   |___)__)
//