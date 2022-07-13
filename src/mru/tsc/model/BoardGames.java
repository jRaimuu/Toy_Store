package mru.tsc.model;

/**
*Contains all the methods to change the properties of an BoardGame
*
*@author Liam Sarjeant, Reese Sanchez
*/

public class BoardGames extends Toy
{
	
	private String minPlayers;
	private String maxPlayers;
	private String designer;
	
	/**
	* Parameterized Constructor
	*
	*@param s	the serial number
	*@param n	the name
	*@param b	the brand
	*@param p	the price
	*@param i	the inventory stock
	*@param a	the appropriate age
	*@param min	the minimum players
	*@param max the maximu players
	*@param d 	the designer of the product
	*/
	public BoardGames(String s, String n, String b, double p, int i, int a, String min, String max, String d) {
		super(s,n,b,p,i,a);
		// TODO Auto-generated constructor stub
    	minPlayers = min; 
		maxPlayers = max;
    	designer = d;
  }
	
	/**
	*Sets the Minimum Players
	*
	*@param min the Minimum Players need to play
	*/
	public void setMinPlayers(String min)
	{
		minPlayers = min;
	}
	
	/**
	*Gets the Minimum Players
	*
	*@return minPlayers The minimum players needed to play
	*/
	public String getMinPlayers()
	{
		return minPlayers;
	}
	
	/**
	*Sets the Maximum Players
	*
	*@param max Maximum players that can play
	*/
	public void setMaxPlayers(String max)
	{
		maxPlayers = max;
	}
	
	/**
	*Gets the Maximum Players
	*
	*@return maxPlayers Maximum players that can play
	*/
	public String getMaxPlayers()
	{
		return maxPlayers;
	}
	
	/**
	*Sets the Designers of the board game
	*
	*@param d Designers of the board game
	*/
	public void setDesigner(String d)
	{
		designer = d;
	}
	
	/**
	*Gets the Designer of the Board Game
	*
	*@return designer The designers of the Board Game
	*/
	public String getDesigner()
	{
		return designer;
	}
	
	/**
	* Sets all the parameters
	*
	*@param s	the serial number
	*@param n	the name
	*@param b	the brand
	*@param p	the price
	*@param i	the inventory stock
	*@param a	the appropriate age
	*@param m	the material
	*@param si	the size
	*/
	public void setAll (String s, String n, String b, double p, int i, int a, String min, String max, String d)
	{
		sn = s;
		name = n;
		brand = b;
		price = p;
		inventory = i;
		age = a;
		minPlayers = min; 
		maxPlayers = max;
    	designer = d;
		
	}
	
	/*********************************************************************************/
	
	// Format methods
	
	/*
	* Format for the save file
	*/
	public String format ()
	{
		return sn + ";" + name + ";" + brand + ";" + price + ";" + inventory + ";" + age + ";" + minPlayers + "-" + maxPlayers + ";" + designer + ";"; // Prints the instance variable into the given format
	}
}