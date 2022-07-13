package mru.tsc.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class popupMessageController {
	
	@FXML
    private Button closeWindow_btn;

    @FXML
    void closeWindow(ActionEvent event) {
    	Stage stage = (Stage) closeWindow_btn.getScene().getWindow();
        stage.close();
    }
}
