
package mru.tsc.model;

/**
*Contains all the methods to change the properties of a puzzle
*
*@author Liam Sarejant, Reese Sanchez
*/

public class Puzzles extends Toy
{
	// Instance
	private String puzzleType;
	
	/**
	* Parameterized Constructor
	*
	*@param s	the serial number
	*@param n	the name
	*@param b	the brand
	*@param p	the price
	*@param i	the inventory stock
	*@param a	the appropriate age
	*@param t	the puzzle type
	*/
	public Puzzles(String s, String n, String b, double p, int i, int a, String t)
	{
		super(s,n,b,p,i,a);
		puzzleType = t;
	}
	
	/**
	*Sets the type of the puzzzle
	*
	*@param t passes the type of the puzzle
	*/
	public void setPuzzleType(String t)
	{
		puzzleType = t;
	}
	
	/**
	*Gets the type of the puzzle
	*
	*@return puzzletype returns the type of puzzle
	*/
	public String getPuzzleType()
	{
		return puzzleType;
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
	*/
	public void setAll (String s, String n, String b, double p, int i, int a, String t)
	{
		sn = s;
		name = n;
		brand = b;
		price = p;
		inventory = i;
		age = a;
		puzzleType = t;
	}
	
	
	
	/*********************************************************************************/
	
	// Format methods
	
	/*
	* Format for the save file
	*/
	public String format ()
	{
		return sn + ";" + name + ";" + brand + ";" + price + ";" + inventory + ";" + age + ";" + puzzleType + ";"; // Prints the instance variable into the given format
	}
}