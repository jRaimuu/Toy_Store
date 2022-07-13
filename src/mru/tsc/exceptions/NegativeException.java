package mru.tsc.exceptions;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Contains methods to display an exception when the user enters a negative price
 */
public class NegativeException extends Exception
{
	public NegativeException ()
	{
		super ("Error: Price cannot be negative");
	}
	
	/**
	 * Displays error message when the price is negative (when the user is adding a new toy)
	 * @param price
	 */
	public NegativeException (double price) 
	{
		super ("Error: Price cannot be negative " + price);
	}
	
}
