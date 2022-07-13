package mru.tsc.model;

/**
*Contains all the methods to change the properties of a Toy
*
*@author Liam Sarejant, Reese Sanchez
*/

abstract public class Toy 
{
	//Instance variables
	protected String sn;
	protected int inventory;
	protected double price;
	protected String name;
	protected String brand;
	protected int age;
	
	public Toy ()
	{
		
	}
	
	/**
	* Parameterized Constructor
	*
	*@param s	the serial number
	*@param n	the name
	*@param b	the brand
	*@param p	the price
	*@param i	the inventory stock
	*@param a	the appropriate age
	*/
	public Toy(String s, String n, String b, double p, int i, int a)
	{
		sn = s;
		name = n;
		brand = b;
		price = p;
		inventory = i;
		age = a;
	}
	
	/**
	*Sets the serial number
	*
	*@param s the serial number to be passed
	*/
	public void setSN(String s)
	{
		sn = s;
	}
	
	/**
	*Gets the serial number
	*
	*@return sn the serial number to be returned
	*/
	public String getSN()
	{
		return sn;
	}
	
	/**
	*Sets the inventory size
	*
	*@param i the inventory to be set
	*/
	public void setInventory(int i)
	{
		inventory = i;
	}
	
	/**
	*Gets the size of the inventory
	*
	*@return inventory returns the inventory size
	*/
	public int getInventory()
	{
		return inventory;
	}
	
	/**
	*Sets the price of the toy
	*
	*@param p the price to be passed
	*/
	public void setPrice(double p)
	{
		price = p;
	}
	
	/**
	*Gets the price of the toy
	*
	*@return the price to  be returned
	*/
	public double getPrice()
	{
		return price;
	}
	
	/**
	*Sets the name of the toy
	*
	*@param n toy name is passed
	*/
	public void setName(String n) 
	{
		name = n;
	}
	
	/**
	*Gets the name of the toy
	*
	*@return  name the name to be returned
	*/
	public String getName()
	{
		return name;
	}
	
	/**
	*Sets the brand of the toy
	*
	*@param b the name of the toy to be passed
	*/
	public void setBrand(String b)
	{
		brand = b;
	}
	
	/**
	*Gets the name of the brand
	*
	*@return b the name to be returned
	*/
	public String getBrand()
	{
		return brand;
	}
	
	/**
	*Sets the age of the toy
	*
	*@param a passes the appropriate age
	*/
	public void setAge(int a)
	{
		age = a;
	}
	
	/**
	*Gets the appropriate age of the toy
	*
	*@return age return the appropriate age
	*/
	public int getAge()
	{
		return age;
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
	public void setAll(String s, String n, String b, double p, int i, int a)
	{
		sn = s;
		inventory = i;
		price = p;
		name = n;
		brand = b;
		age = a;
	}

	@Override
	public String toString() 
	{
		
		String i = String.format("\tInventory: %-3s", inventory);
		String p = String.format("\tPrice: %-3.2f", price);
		String n = String.format("\tName: %-45s", name);
		String b = String.format("Brand: %-15s", brand);
		String a = String.format("\tAge: %-2s", age);
		
		return "Toy SN: " + sn + i + p + n + b + a;
	}
	
	
	
	
}