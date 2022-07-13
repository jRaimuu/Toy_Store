package mru.tsc.model;

/**
*Contains all the methods to change the properties of an Figure
*
*@author Liam Sarejant, Reese Sanchez
*/

public class Figures extends Toy
{
	
	private String classification;
	
	/**
	* Parameterized Constructor
	*
	*@param s	the serial number
	*@param n	the name
	*@param b	the brand
	*@param p	the price
	*@param i	the inventory stock
	*@param a	the appropriate age
	*@param c	the classification
	*/
	public Figures(String s, String n, String b, double p, int i, int a, String c) 
	{
		super(s,n,b,p,i,a);
		classification = c;
	}

	/**
	*Sets the Classification
	*
	*@param c The new classification of the figure
	*/
	public void setClassification(String c)
	{
		classification = c;
	}
	
	/**
	*Gets the Classification
	*
	*@return classification The classification of the figure
	*/
	public String getClassification()
	{
		return classification;
	}
	
	/**
	* Setting all the parameter
	*
	*@param s	the serial number
	*@param n	the name
	*@param b	the brand
	*@param p	the price
	*@param i	the inventory stock
	*@param a	the appropriate age
	*@param c	the classification
	*/
	public void setAll(String s, String n, String b, double p, int i, int a, String c)
	{
		sn = s;
		name = n;
		brand = b;
		price = p;
		inventory = i;
		age = a;
		classification = c;
	}
	
	
	
	/*********************************************************************************/
	
	// Format methods
	
	/*
	* Format for the save file
	*/
	public String format ()
	{
		return sn + ";" + name + ";" + brand + ";" + price + ";" + inventory + ";" + age + ";" + classification + ";"; // Prints the instance variable into the given format
	}
}