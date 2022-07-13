package mru.tsc.view;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mru.tsc.exceptions.MinMaxException;
import mru.tsc.exceptions.NegativeException;
import mru.tsc.model.Animals;
import mru.tsc.model.BoardGames;
import mru.tsc.model.Figures;
import mru.tsc.model.Puzzles;
import mru.tsc.model.Toy;
import mru.tsc.model.ToyList;

public class MainWindowController extends Toy{
	
	ToyList list = new ToyList();
	
	private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//Variables
	private int searchType = 0;
	private int selectionType = 0;
	
	private String snInput, nameInput, brandInput;
	private double priceInput;
	private int availInput;
	private int ageInput;
	
	// ArrayList
	private ArrayList<Toy> toys;
	private ArrayList<Toy> test;
	
	
	//public MainwindowController ()
	//{
		//list.callList();
	//}
	
	// Path for file
	private final String FILE_PATH = "res/toys.txt";
	
    @FXML
    private Button purchaseButton;

    @FXML
    private TextField searchTextBox;

    @FXML
    private Button searchButton;

    @FXML
    private Button clearButton;

    @FXML
    private ListView<Toy> searchToyResults;

	@FXML
    private ListView<Toy> removeToyResults;

    @FXML
    private MenuButton searchTypeMenu;

    @FXML
    private MenuItem menuSearchType;

    @FXML
    private MenuItem menuSearchName;

    @FXML
    private MenuItem menuSearchSN;

    @FXML
    private MenuButton categorySelector;

    @FXML
    private TextField snField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField availableField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField classificationField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField materialField;

    @FXML
    private TextField sizeField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField designerField;

    @FXML
    private Button removeSearchButton;

    @FXML
    private TextField removeTextBox;

    @FXML
    private Button removeClearButton;

    @FXML
    private Button removeToyButton;
    
//    @FXML
//    private Button selectFigure;
//    
//    @FXML
//    private Button selectAnimal;
//    
//    @FXML
//    private Button selectPuzzle;
//    
//    @FXML
//    private Button selectBrdGame;
    
    
    public MainWindowController()throws Exception
    {
    	toys = new ArrayList<Toy>();
		loadData();
    }
    
	/**
	 * Clears all the values and lists on the Search Toy Menu
	 * @param event when the element is clicked
	 */
    @FXML
    void searchClear(ActionEvent event) {
    	searchType = 0;
    	searchTypeMenu.setText("Select Search Type");
    	searchTextBox.setText("");
    	searchToyResults.getItems().clear();
    }

	/**
	 * This method will reduce the inventory of the selected Toy by 1 and log the purchase
	 * @param event when the element is clicked
	 */
    @FXML
    void purchaseToy(ActionEvent event) 
	{
		System.out.println(searchToyResults.getSelectionModel().getSelectedItem()); 
		
		Toy selectedToy = searchToyResults.getSelectionModel().getSelectedItem();
    	
    	//checking if an item was selected so the remove could would execute
    	if(selectedToy != null)
    	{
    		if(selectedToy.getInventory() > 0)
			{
				//subtracting 1 from the inventory of the toy purchased
				int newInventory = selectedToy.getInventory();
				newInventory--;
				selectedToy.setInventory(newInventory);
				
				//logging the purchase
				log.info("Toy "  + selectedToy.getName() + " was purchased");
				
				//making a new window that will show a message that the toy was remove from Toy List
    			try 
    			{
    				//BorderPane root = new BorderPane();
    				Parent message = FXMLLoader.load(getClass().getResource("toyPurchasedMessage.fxml"));
    				Scene removeMessage = new Scene(message,400,200);
    				Stage messageStage = new Stage();
    				messageStage.setScene(removeMessage);
    				messageStage.show();
    			} 
    			catch(Exception e) 
    			{
					log.warning("Popup message toyPurchasedMessage.fxml failed to load");
    				e.printStackTrace();
    			}
			}
			else
			{
				//making a new window that will show a message that the toy was remove from Toy List
    			try 
    			{
    				//BorderPane root = new BorderPane();
    				Parent message = FXMLLoader.load(getClass().getResource("noInventoryMessage.fxml"));
    				Scene removeMessage = new Scene(message,400,200);
    				Stage messageStage = new Stage();
    				messageStage.setScene(removeMessage);
    				messageStage.show();
    			} 
    			catch(Exception e) 
    			{
					log.info("Toy " + selectedToy.getName() + " has ran out of Inventory");
    				e.printStackTrace();
    			}
    			
			}

    		
    	}
    }

	/**
	 * Clears the list and search box in the remove toy tab
	 * @param event when the element is clicked
	 */
    @FXML
    void removeClear(ActionEvent event) {
    	removeTextBox.setText("");
    	//clearing the listView
		removeToyResults.getItems().clear();
    }

	/**
	 * This will search a toy by it's Serial Number
	 * @param event when the element is clicked
	 */
    @FXML
    void removeSearch(ActionEvent event) {
    	
		if(removeTextBox.getText().length() == 10)
		{
    		Toy searchResult;
    	
    		//clearing the listView
    		removeToyResults.getItems().clear();
    	
    		searchResult = list.snSearch(removeTextBox.getText());
    		removeToyResults.getItems().add(searchResult);
		}
		else
		{
			//making a new window that will show a message that the toy was remove from Toy List
    		try 
    		{
    			//BorderPane root = new BorderPane();
    			Parent message = FXMLLoader.load(getClass().getResource("InvalidSNMessage.fxml"));
    			Scene removeMessage = new Scene(message,400,200);
    			Stage messageStage = new Stage();
    			messageStage.setScene(removeMessage);
    			messageStage.show();
    		} 
    		catch(Exception e) 
    		{
				log.warning("Popup message InvalidSNMessage.fxml failed to load");
    			e.printStackTrace();
    		}
		}
    }

	/**
	 * When clicked, the selected toy in the list view is removed from the Toy list of the
	 * @param event when the element is clicked
	 */
    @FXML
    void removeToy(ActionEvent event) {
    	Toy selectedToy = removeToyResults.getSelectionModel().getSelectedItem();
    	
    	//checking if an item was selected so the remove could would execute
    	if(selectedToy != null)
    	{
    		//code to Remove the selected item from the inventory list
    		list.removeToy(selectedToy);
    		
    		//clearing the listView
    		removeToyResults.getItems().clear();

			//logging the removal of the toy
			log.info("Toy "  + selectedToy.getName() + " was removed from the Toy List");
    		
    		//making a new window that will show a message that the toy was remove from Toy List
    		try 
    		{
    			//BorderPane root = new BorderPane();
    			Parent message = FXMLLoader.load(getClass().getResource("toyRemoveMessage.fxml"));
    			Scene removeMessage = new Scene(message,400,200);
    			Stage messageStage = new Stage();
    			messageStage.setScene(removeMessage);
    			messageStage.show();
    		} 
    		catch(Exception e) 
    		{
				log.warning("Popup message toyRemoveMessage.fxml failed to load");
    			e.printStackTrace();
    		}
    	}
    }
    
    @FXML
    void selectedListItem() {
    	Toy select;
    	String menu;
    	
    	
    	searchToyResults.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	select = searchToyResults.getSelectionModel().getSelectedItem();
    	String test = select.toString();
    	
    	if (test == null || test.isEmpty())
    		System.out.println("Toy Not Selected" ); 
    	else
    		System.out.println(select); 
    }

	/**
	 * Based on what search type is selected, the program will search for a toy based on Type, name or Serial number
	 * @param event when the element is clicked
	 */
    @FXML
    void searchToy(ActionEvent event) throws Exception {
    	
    	
    	ObservableList<Toy> p = FXCollections.observableArrayList(toys);
    	searchToyResults.getItems().clear();
    	searchToyResults.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	//general search
        if (searchType == 0 && (searchTextBox.getText().equals(""))) // If there is nothing in any of the search fields
    	{
    		searchToyResults.getItems().addAll(p);
    	}
    	//searching by Toy Type
    	else if (searchType == 1)
    	{
    		ArrayList<Toy> searchResult = new ArrayList<Toy>();
			searchResult = list.typeSearch(searchTextBox.getText());
			ObservableList<Toy> read = FXCollections.observableArrayList(searchResult);
			searchToyResults.getItems().addAll(read);
    	}
    	//searching by Toy Name
    	else if (searchType == 2)
    	{
			Toy searchResult;
    		searchResult = list.nameSearch(searchTextBox.getText());
    		searchToyResults.getItems().add(searchResult);
    		
    	}
    	//searching by Serial Number
    	else if (searchType == 3)
    	{
			if(searchTextBox.getText().length() == 10)
			{
    			Toy searchResult;
    			searchResult = list.snSearch(searchTextBox.getText());
    			searchToyResults.getItems().add(searchResult);
			}
			else
			{
				//making a new window that will show a message that the toy was remove from Toy List
    			try 
    			{
    				//BorderPane root = new BorderPane();
    				Parent message = FXMLLoader.load(getClass().getResource("InvalidSNMessage.fxml"));
    				Scene removeMessage = new Scene(message,400,200);
    				Stage messageStage = new Stage();
    				messageStage.setScene(removeMessage);
    				messageStage.show();
    			} 
    			catch(Exception e) 
    			{
					log.warning("Popup message InvalidSNMessage.fxml failed to load");
    				e.printStackTrace();
    			}
			}
    	}
    	else
    	{
    		System.out.println("No results found"); 
    	}
    	
    }

	/**
	 * Sets the search type in the search tab to "Search Name"
	 * @param event when the element is clicked
	 */
    @FXML
    void searchName(ActionEvent event) {
    	searchType = 2;
    	searchTypeMenu.setText("Search Name");
    }
	
	/**
	 * Sets the search type in the search tab to "Search Serial Number"
	 * @param event when the element is clicked
	 */
    @FXML
    void searchSN(ActionEvent event) {
    	searchType = 3;
    	searchTypeMenu.setText("Search Serial Number");
    }

	/**
	 * Sets the search type in the search tab to "Search Toy Type"
	 * @param event when the element is clicked
	 */
    @FXML
    void searchType(ActionEvent event) {
    	searchType = 1;
    	searchTypeMenu.setText("Search Toy Type");
    }
    
	/**
	 * 
	 * @param event when the element is clicked
	 */
    @FXML
    void scroll(ActionEvent event) {
    	searchType = 1;
    	searchTypeMenu.setText("Search Toy Type");
    }

	/**
	 * 
	 * @param event when the element is clicked
	 */
	@FXML
    void namesnField(ActionEvent event) {
	
    }

	/**
	 * 
	 * @param event when the element is clicked
	 */
	@FXML
    void createToy(ActionEvent event) {
	
    }
    
/*********************************************************************************/
    //Add functionality
	
	/**
	 * makes the user choice equal to an integer for later use
	 * and sets text in the menu
	 */
	@FXML
    void selectFigure(ActionEvent event) {
    	selectionType = 1;
    	categorySelector.setText("Figures");
    }
	
	/**
	 * makes the user choice equal to an integer for later use
	 * and sets text in the menu
	 */
	@FXML
    void selectAnimal(ActionEvent event) {
		selectionType = 2;
		categorySelector.setText("Animals");
    }
	
	/**
	 * makes the user choice equal to an integer for later use
	 * and sets text in the menu
	 */
	@FXML
    void selectPuzzle(ActionEvent event) {
		selectionType = 3;
		categorySelector.setText("Puzzles");
    }
	
	/**
	 * makes the user choice equal to an integer for later use
	 * and sets text in the menu
	 */
	@FXML
    void selectBrdGame(ActionEvent event) {
		selectionType = 4;
		categorySelector.setText("Board Games");
    }
	
	/**
	*
	* adds a toy depending on the user choice
	*/
	@FXML
    void addToy(ActionEvent event) throws IOException
	{
		
		if (selectionType == 1)
    	{
    		createFigure();
    	}
    	else if (selectionType == 2)
    	{
    		createAnimal();
    	}
    	else if (selectionType == 3)
    	{
    		createPuzzle();
    	}
    	else if (selectionType == 4)
    	{
    		createBrdGame();
    	}
    	else
    	{
    		System.out.println("No Toy Type Selected");
    	}
    }
	
	
    /**
	* Creates a figure object
	* 
    *@throws IOException 
	*/
	@FXML
    void createFigure() throws IOException 
    {
    	Toy tObj;
    	
    	String snInput, nameInput, brandInput;
    	double priceInput;
    	int availInput;
    	int ageInput;
    	
    	boolean state;
    	String classifInput; // Figure
    	
    	// Initialize
		snInput = snField.getText();
		nameInput = nameField.getText();
		brandInput = brandField.getText();
		priceInput = Double.parseDouble(priceField.getText());  
		availInput = Integer.parseInt(availableField.getText());
		ageInput = Integer.parseInt(ageField.getText());
		
		classifInput = classificationField.getText();
    	
		state = priceCheck(priceInput);
		
		if (state == true)
		{
			classifInput.toUpperCase();
			tObj = new Figures(snInput, nameInput, brandInput, priceInput, availInput, ageInput, classifInput); // Create new object
			
			
			System.out.println("Figure added");
			toys.add(tObj); // Add the object to the ArrayList
		}	
    }
    
    /**
	* Creates a Animal object
	*
    *@throws IOException 
	*/
	@FXML
    void createAnimal() throws IOException 
    {
    	Toy tObj;
    	
    	String snInput, nameInput, brandInput;
    	double priceInput;
    	int availInput;
    	int ageInput;
    	
    	boolean state;
    	String materialInput, sizeInput; // Animal
    	
    	// Initialize
		snInput = snField.getText();
		nameInput = nameField.getText();
		brandInput = brandField.getText();
		priceInput = Double.parseDouble(priceField.getText());  
		availInput = Integer.parseInt(availableField.getText());
		ageInput = Integer.parseInt(ageField.getText());
		
		materialInput = materialField.getText();
		sizeInput = sizeField.getText();
		
		state = priceCheck(priceInput);
		
		if (state == true)
		{
			tObj = new Animals(sizeInput, nameInput, brandInput, priceInput, availInput, ageInput, materialInput, sizeInput);
			System.out.println("Animal created");
			toys.add(tObj); // Add the object to the ArrayList
		}
    	
    }
    
    /**
	* Creates a Puzzle object
	*
    * @throws IOException 
	*/
	@FXML
    void createPuzzle() throws IOException
    {
    	Toy tObj;
    	
    	String snInput, nameInput, brandInput;
    	double priceInput;
    	int availInput;
    	int ageInput;
    	
    	boolean state;
    	String typeInput; // Figure
    	
    	// Initialize
		snInput = snField.getText();
		nameInput = nameField.getText();
		brandInput = brandField.getText();
		priceInput = Double.parseDouble(priceField.getText());  
		availInput = Integer.parseInt(availableField.getText());
		ageInput = Integer.parseInt(ageField.getText());
		
		typeInput = typeField.getText();
    	
		
		state = priceCheck(priceInput);
		
		if (state == true)
		{
			typeInput.toUpperCase();
			tObj = new Puzzles(snInput, nameInput, brandInput, priceInput, availInput, ageInput, typeInput);
			System.out.println("Puzzle created");
	    	
			toys.add(tObj); // Add the object to the ArrayList
		}
		
    	
    }
    
    /**
	* Creates a BoardGame Object
	*
    *@throws IOException 
	*/
	@FXML
    void createBrdGame() throws IOException 
    {
    	Toy tObj;
    	String snInput, nameInput, brandInput;
    	double priceInput;
    	int availInput;
    	int ageInput;
    	
    	
    	boolean pState;
    	boolean mmState;
    	
    	String minInput, maxInput, designerInput; // Board Game
    	
    	// Initialize
		snInput = snField.getText();
		nameInput = nameField.getText();
		brandInput = brandField.getText();
		priceInput = Double.parseDouble(priceField.getText());  
		availInput = Integer.parseInt(availableField.getText());
		ageInput = Integer.parseInt(ageField.getText());
		
		minInput = minField.getText();
		maxInput = maxField.getText();
		designerInput = designerField.getText();
    	
		mmState = playerCheck(Double.parseDouble(minInput), Double.parseDouble(maxInput));
		pState = priceCheck(priceInput);
		
		if (pState == true && mmState == true)
		{
			tObj = new BoardGames(snInput, nameInput, brandInput, priceInput, availInput, ageInput, minInput, maxInput, designerInput);
			System.out.println("Board Game created");
			toys.add(tObj); // Add the object to the ArrayList
		}
    }
	
	/**
	 * Clears all text fields in the Add Toy tab
	 */		
	@FXML
	void clearAddFields ()
	{
		snField.clear();
		nameField.clear();
		brandField.clear();
		priceField.clear();
		availableField.clear();
		ageField.clear();
		classificationField.clear();
		typeField.clear();
		materialField.clear();
		sizeField.clear();
		minField.clear();
		maxField.clear();
		designerField.clear();
	}
    
	/**
	*checks if the price is negative
	*
	*@param		p 	the price input by the user
	*@return	state	verifies if it is a valid input
	*/
    public boolean priceCheck (double p) throws IOException
    {
    	boolean state = true;
    	try 
		{
    		if (p < 0) //If price is less than 0, show error message and window
    		{
    			state = false;
    			
    			//BorderPane root = new BorderPane();
    			Parent message = FXMLLoader.load(getClass().getResource("negativePrice.fxml"));
    			Scene removeMessage = new Scene(message,400,200);
    			Stage messageStage = new Stage();
    			messageStage.setScene(removeMessage);
    			messageStage.show();

    			throw new NegativeException();
    		}
		} 
		catch(NegativeException e) 
		{
			e.printStackTrace();
		}
    	
		return state;
    }
    
    /**
	*checks if the minimum players is greater than the maximum
	*
	*@param		min		the min number of players for the game
	*@param		max		the max number of players for the game
	*@return	state 	verifies by returning true/false
	*/
    public boolean playerCheck (double min, double max) throws IOException
    {
    	boolean state = true;
    	try 
		{
    		if (min > max) //If price is less than 0, show error message and window
    		{
    			state = false;
    			
    			//BorderPane root = new BorderPane();
    			Parent message = FXMLLoader.load(getClass().getResource("playerError.fxml"));
    			Scene removeMessage = new Scene(message,400,200);
    			Stage messageStage = new Stage();
    			messageStage.setScene(removeMessage);
    			messageStage.show();

    			throw new MinMaxException();
    		}
		} 
		catch(MinMaxException e) 
		{
			e.printStackTrace();
		}
    	
		return state;
    }
    

/*********************************************************************************/
	
	/*Primary Functionality*/
	
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
	 * Saves the file
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
    
}