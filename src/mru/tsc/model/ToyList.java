package mru.tsc.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import mru.tsc.view.MainWindowController;

/**
 * <b>ToyList</b>
 * <p>
 *Contains the main menus, sub-menus and vital functions to the program
 *including loadData, saveData, snSearch, nameSearch, typeSearch, addToy, and removeToy
 *
 * @author Liam Sarjeant, Reese Sanchez
 */
public class ToyList extends Toy
{
	
	//Initialize array-list
	ArrayList<Toy> toys; //Array-list called "toys" populated with toy objects
	String list;
	
	// Path for file
	private final String FILE_PATH = "res/toys.txt";
	
	
	
	/**
	 *Constructor
	 *
	 *@throws	Exception
	 */
	public ToyList() throws Exception
	{
		toys = new ArrayList<Toy>();
		loadData();
	}

	/*********************************************************************************/
	
	/*Load and Save File*/
	
	/**
	 * Loads the file and its contents
	 * @throws FileNotFoundException 
	 * @throws Exception
	 */
	public void loadData () throws Exception
	{
		File infile = new File (FILE_PATH);
		String currentLine;
		String currentToken;
		String[] splittedLine = null;
		String[] dashedLine = null;
		
		
		if (infile.exists())
		{
			Scanner in = new Scanner (infile);
			
			while (in.hasNextLine()) // Reads each line
			{
				char sn = 0;
				
				currentLine = in.nextLine();
		
				splittedLine = currentLine.split(";"); // Splits the current line after each semi-colon
				dashedLine = currentLine.split("-"); // Splits the current line after each dash
				
				sn = splittedLine[0].charAt(0);
				
				
				//Testing what category each toy is
				if (sn == '0' || sn == '1')
				{
					Toy t = new Figures (splittedLine[0], splittedLine[1], splittedLine[2], Double.parseDouble(splittedLine[3]), Integer.parseInt(splittedLine[4]), Integer.parseInt(splittedLine[5]), splittedLine [6]); // Make an object with these parameters
					toys.add(t);
				}
				else if (sn == '2' || sn == '3')
				{
					Toy t = new Animals (splittedLine[0], splittedLine[1], splittedLine[2], Double.parseDouble(splittedLine[3]), Integer.parseInt(splittedLine[4]), Integer.parseInt(splittedLine[5]), splittedLine[6], splittedLine[7]);
					toys.add(t);
					
				}
				else if (sn == '4' || sn == '5' || sn == '6')
				{
					Toy t = new Puzzles (splittedLine[0], splittedLine[1], splittedLine[2], Double.parseDouble(splittedLine[3]), Integer.parseInt(splittedLine[4]), Integer.parseInt(splittedLine[5]), splittedLine[6]);
					toys.add(t);
				}
				else if (sn == '7' || sn == '8' || sn == '9')
				{
					//Initialize
					String minmum;
					String maximum;
					
					dashedLine = splittedLine[6].split("-");
							
					minmum = dashedLine[0];
					maximum = dashedLine[1];
					
					Toy t = new BoardGames (splittedLine[0], splittedLine[1], splittedLine[2], Double.parseDouble(splittedLine[3]), Integer.parseInt(splittedLine[4]), Integer.parseInt(splittedLine[5]), minmum, maximum, splittedLine[7]);
					toys.add(t);
				}
			}
			
			// Message telling the user that the file has loaded
			System.out.println("The file has been successfuly loaded");
			
			in.close();
		}
		
    
	}
	
	
	/**
	 * SAVE
	 * 
	 * Saves current session to the file
	 * @throws IOException
	 */
	public void saveData() throws IOException
	{
		int index = 0;
		
		File infile = new File (FILE_PATH);
		PrintWriter out = new PrintWriter(infile);
		
		System.out.println("Saving...");
		
		for (Toy a: toys) // For each player in the arrayList
		{	
			Toy compToy = toys.get(index); // get the toy for that index
			
			if (compToy instanceof Figures) // if the ToyObject that we extracted is an instance of figures 
			{
				Figures type = (Figures)toys.get(index); // then make the toy at that index an object of Figure
				out.println(type.format()); // Print the object into the file
			}
			else if (compToy instanceof Animals)
			{
				Animals type = (Animals)toys.get(index);
				out.println(type.format()); 	
			}
			else if (compToy instanceof Puzzles)
			{
				Puzzles type = (Puzzles)toys.get(index);
				out.println(type.format()); 
			}
			else if (compToy instanceof BoardGames)
			{
				BoardGames type = (BoardGames)toys.get(index);
				out.println(type.format()); 
			}
			
			index++;
		}
		out.close();
	}
	
	/*********************************************************************************/
	
	/**
	 * Search SERIAL NUMBER
	 * 
	 * Prints out any products found by the search results based on the Serial Number
	 * @param search The Serial Number that the user wants to search for
	 * @return snResult The resulting 
	 */
	public Toy snSearch(String search)
	{
		Toy snResult = null;
		int index = 0;
		boolean result = false;
		char sn;
		
		//Check all the toys in the arraylist
		for (Toy e : toys)
		{
			//Checking if the searched serial number matches with serial numbers in the arraylist
			if (search.equals(e.getSN()))
			{
				sn = search.charAt(0);
				
				snResult = toys.get(index);
				
				//setting true when a result was found
				result = true;
			}
			//increasing the index to track where in the arraylist we are
			index++;
		}
		
		//if no search result was found
		if (result == false)
		{
			System.out.println("\nNo Results of this Serial Number.");
		}
		
		//returning the result
		return snResult;
	}
	
	
	/**
	 * Prints out any products found by the search results based on the name of the product
	 * @param search The name that the user wants to search for in the arraylist
	 * @return nameResult The toy with the matching name of the inputed name searched for
	 */
	public Toy nameSearch(String search)
	{
		//initializing variables
		Toy nameResult = null;
		int index = 0;
		boolean result = false;
		char sn;
		
		//Check all the toys in the arraylist
		for (Toy e : toys)
		{
			//testing if the name searched matches with any names within the arraylists
			if (search.toLowerCase().equals(e.getName().toLowerCase()))
			{
				//getting the toy type of the found toy
				sn = e.getSN().charAt(0);
				
				nameResult = toys.get(index);
				
				//setting to true cause a result was found
				result = true;
			}
			//increasing the index to track where in the arraylist we are
			index++;
		}
		
		//in case no results are found
		if (result == false)
		{
			System.out.println("\nNo Results of this name.");
		}
		
		return nameResult;
	}
	
	//Search PRODUCT TYPE
	/**
	 * <p>
	 * Prints out any products found by the search results based on the name of the product
	 * @param search The type of toy the user is searching for
	 * @return typeResult An ArrayList of all the toys of the searched Toy type
	 */
	public ArrayList<Toy> typeSearch(String search)
	{
		//setting
		ArrayList<Toy> typeResult = new ArrayList<Toy>();
		String toyType = "";
		char sn;
		
		//For each loop to check all the toys in the arraylist
		for (Toy e : toys)
		{
			//getting the first digit of the SN to figure out the toy type
			sn = e.getSN().charAt(0);
			
			//Testing what category each toy is and storing the result in a variables
			//is the toy is of the Figure type
			if (sn == '0' || sn == '1')
			{
				toyType = "figure";
			}
			//if the toy is of the Animal type
			else if (sn == '2' || sn == '3')
			{
				toyType = "animal";
			}
			//if the toy is of the puzzles type
			else if (sn == '4' || sn == '5' || sn == '6')
			{
				toyType = "puzzles";
			}
			//if the toy is of the board games type
			else if (sn == '7' || sn == '8' || sn == '9')
			{
				toyType = "board games";
			}
			
			//testing if the toys' type matches the one being searched for
			if (toyType.equals(search.toLowerCase()))
			{
				typeResult.add(e);
			}
		}
		
		return typeResult;
	}
	
	public ArrayList<Toy> getList()
	{
		return toys;
	}
	
	/*********************************************************************************/
	
	/*ADD add REMOVE Toy*/
	
	
	//Add Toy
	/**
	*Obtains the user inputs from another class then adds it to the toys ArrayList
	*@param		tObj the object with entry characteristics	
	*/
	public void addToy (Toy tObj)
	{
		toys.add(tObj);
//		menu.validate(); //Messages to validate the added toy
	}
	
	//Remove Toy 
	/**
	 * Functions of the remove Menu
	 * Removes the toy from the list based on user input properties
	 * @param search The type of toy the user is searching for
	 */
	public void removeToy(Toy removingToy)
	{
		toys.remove(removingToy);
	}
}
