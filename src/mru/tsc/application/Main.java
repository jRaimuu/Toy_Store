package mru.tsc.application;
import javafx.application.Application;
import javafx.stage.Stage;
import mru.tsc.view.MainWindowController;
import mru.tsc.view.myGUI;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		myGUI gui=new myGUI(primaryStage);
		MainWindowController control = new MainWindowController();
	}
	
	public static void main(String[] args) throws Exception 
	{
		launch(args);
	}
}
