package com.mycompany.grupo_07;

import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField textFieldID;
    @FXML
    private Button primaryButton;
    @FXML
    private TextField textFieldID2;
    @FXML
    private Label errorLabelID;

    @FXML
    private void switchToSecondary() throws IOException {
        String prediction = textFieldID.getText();
        String nCircles = textFieldID2.getText();
        if(!prediction.equals("") && !nCircles.equals("")){
        try {
            FileWriter writer = new FileWriter(App.pathJuego, false);
            writer.write(prediction+","+nCircles);
            writer.close();
        } catch (IOException e) {
            
            e.printStackTrace();
        }finally{
                App.setRoot("secondary");
            }
        }
        else if(prediction.equals("") && nCircles.equals("")){
            errorLabelID.setText("DEBE INGRESAR VALORES EN LOS CAMPOS");
        }
        else if(prediction.equals("")){
            errorLabelID.setText("DEBE INGRESAR UNA PREDICCION");
        }else if(nCircles.equals("")){
            errorLabelID.setText("DEBE INGRESAR LA CANTIDAD DE CIRCULOS EN ORBITA");
        }
        
        
        
    }
    
    
    
}
