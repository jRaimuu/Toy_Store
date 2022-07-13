
package mru.tsc.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class myGUI {
	private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Label welcomeLabel;
	Scene startScene;
	Scene mainScene;
	
	public myGUI(Stage primaryStage)
	{
		try 
		{
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("StartWindow.fxml"));
			
			//Starting Scene
			//elements in the window
			welcomeLabel = new Label("Welcome To Toys 'R' Us Inventory");
			Button start = new Button("Start");
			start.setOnAction(e -> primaryStage.setScene(mainScene));
			
			//making VBox
			VBox welcome = new VBox(15, welcomeLabel, start);
			welcome.setAlignment(Pos.CENTER);
			
			Scene startScene = new Scene(welcome,600,400);
			startScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Main Scene
			//Attached .fxml file to mainScene
			Parent mainMenu = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
			mainScene = new Scene(mainMenu,1200,800);
			
			primaryStage.setScene(startScene);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			log.log(Level.SEVERE, "Window could not be loaded.", e);
			e.printStackTrace();
		}
	}
}
