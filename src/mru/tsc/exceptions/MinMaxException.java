package mru.tsc.exceptions;

/*
 * Contains methods to display an exception when the user enters an improper player count
 */
public class MinMaxException extends Exception
{
	
	public MinMaxException ()
	{
		super ("Error: The minnimum players cannot be greater than the maximum");
	}
	
	/**
	*Displays error message when the input price is negative (when the user is adding a new toy).
	*
	*@param min the minimum number of players allowed
	*@param max the maximum number of players allowed
	*/
	public MinMaxException (int min, int max)
	{
		super ("Error: The minnimum player cannot be greater than the maximum" + 
				"Min: " + min + "Max: " + max);
	}
	
	
}
