package com.mycompany.grupo_07;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField textFieldID;
    @FXML
    private Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println(textFieldID.getText());
        App.setRoot("secondary");
        
        
    }
    
    
    
}
