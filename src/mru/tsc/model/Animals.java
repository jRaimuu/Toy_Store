package mru.tsc.model;

/**
*Contains all the methods to change the properties of a toy
*
*@author Liam Sarejant, Reese Sanchez
*/

public class Animals extends Toy
{
	
	private String material;
	private String size;
	
	/**
	* Parameterized Constructor
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
	public Animals(String s, String n, String b, double p, int i, int a, String m, String si) {
		super(s,n,b,p,i,a);
		// TODO Auto-generated constructor stub
		material = m;
		size = si;
	}
	
	/**
	*Sets the material
	*
	*@param m Material of the animal toy
	*/
	public void setMaterial(String m)
	{
		material = m;
	}
	
	/**
	*Gets the material of the animal toy
	*
	*@return material the material of the toy
	*/
	public String getMaterial()
	{
		return material;
	}
	
	/**
	*Sets the Size
	*
	*@param si The size of the toy
	*/
	public void setSize(String si)
	{
		size = si;
	}
	
	/**
	*Gets the Size
	*
	*@return size The size of the toy
	*/
	public String getSize()
	{
		return size;
	}
	
	/**
	* Sets all parameters
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
	public void setAll(String s, String n, String b, double p, int i, int a, String m, String si)
	{
		sn = s;
		name = n;
		brand = b;
		price = p;
		inventory = i;
		age = a;
		material = m;
		size = si;
	}
	/*********************************************************************************/
	
	// Format methods
	
	/*
	* Format for the save file
	*/
	public String format ()
	{
		return sn + ";" + name + ";" + brand + ";" + price + ";" + inventory + ";" + age + ";" + material + ";" + size + ";"; // Prints the instance variable into the given format
	}
}
