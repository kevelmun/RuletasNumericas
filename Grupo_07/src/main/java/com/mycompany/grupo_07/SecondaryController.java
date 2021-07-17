package com.mycompany.grupo_07;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import objects.Orbit;


public class SecondaryController {

    @FXML
    private StackPane sPaneID;
    @FXML
    private VBox vBID;
    @FXML
    private Label labelOneID;
    @FXML
    private ImageView leftOptionID;
    @FXML
    private ImageView rigthOptionID;
    @FXML
    private Label labelStateID;
    @FXML
    private Label labelActualNID;
    @FXML
    private ToggleGroup rotate;
    @FXML
    private RadioButton innerOptionID;
    @FXML
    private RadioButton outerOptionID;
    @FXML
    private Button btnDeleteID;
    @FXML
    private HBox hbRotateID;
    @FXML
    private Label labelActualNID1;
    private int state=0;
    private int prediction;
    private int nCircles;
    private Orbit orbit;
    private Orbit orbit2;
    private int s1, s2, id;
    
    @FXML
    private void initialize() throws IOException{
        
        try {
            readData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        orbit = new Orbit(40,nCircles, Color.WHITE);
        orbit2 = new Orbit(80,nCircles, Color.GOLDENROD);
        update();       
        rotateOption();
    }
    
    
    public void rotateOption(){
        
        leftOptionID.setOnMouseClicked(e->{
            this.setState(1);
            try {
                this.rotate("left");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            enableRotation(false);
            enableDelete(true);
        });

        rigthOptionID.setOnMouseClicked(e->{
            this.setState(1);
            try {
                this.rotate("right");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            enableRotation(false);
            enableDelete(true);
        });   
   }

    public void rotate(String direction) throws IOException{
       
       sPaneID.getChildren().clear();
        if(direction.equals("right")){
            if(innerOptionID.isSelected())
            orbit.rotateRight();
            if(outerOptionID.isSelected())
            orbit2.rotateRight();
        }else if (direction.equals("left")){
            if(innerOptionID.isSelected())
            orbit.rotateLeft();
            if(outerOptionID.isSelected())
            orbit2.rotateLeft();
        }
        update();
        labelStateID.setText("NOW YOU HAVE TO DELETE");
    }

    public void setState(int state) {
        this.state = state;
    }
    
    public void update() throws IOException{
        sPaneID.getChildren().addAll(orbit2.getRing(), orbit2.updateCirclePane());
        sPaneID.getChildren().addAll(orbit.getRing(), orbit.updateCirclePane());
        labelActualNID.setText(String.valueOf(orbit.getTotal()+ orbit2.getTotal()));
        if(prediction==orbit.getTotal()+ orbit2.getTotal()){
        Alert alert2 = new Alert(Alert.AlertType.WARNING, "Congratulations :D !!!!");
            alert2.setHeaderText("YOU WIN!!!");
            alert2.showAndWait();
            App.setRoot("primary");
        
        }
    }

   
    public void readData() throws FileNotFoundException, IOException{
        String[] list;
        FileReader reader = new FileReader(App.pathJuego);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        line = bufferedReader.readLine();
        list= line.split(",");
        reader.close();
        this.labelOneID.setText(list[0]);
        this.prediction=Integer.parseInt(list[0]);
        this.nCircles =Integer.parseInt(list[1]);
    }
    
    public void enableRotation(boolean valor){
        leftOptionID.setVisible(valor);
        rigthOptionID.setVisible(valor);       
        hbRotateID.setVisible(valor);
    }
    
    public void enableDelete(boolean valor){
        btnDeleteID.setVisible(valor);
    }
    
    @FXML
    private void btnDelete(MouseEvent event) throws IOException{
        int a= orbit.getDeleted();
        int b= orbit2.getDeleted();
        if(a!=(-1) || b!=(-1)){
            if(confimacion()){
                int c=Math.max(a, b);
                eliminarActualizar(c);
            }else
                setearDeleeted();
            if(!orbit.getState()){
            Alert alert2 = new Alert(Alert.AlertType.WARNING, "Sorry try again :(");
            alert2.setHeaderText("YOU LOSE!");
            alert2.showAndWait();
            App.setRoot("primary");
        }
        }else{
            
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select some circle to be able to delete the couple");
            alert.setHeaderText("Incorrect selection");
            alert.showAndWait();
        }
        labelStateID.setText("NOW YOU HAVE TO ROTATE");
    }
    
    
    public void eliminarActualizar(int c) throws IOException{
        orbit.getElements().remove(c);
        orbit2.getElements().remove(c);
        setearDeleeted();
        sPaneID.getChildren().clear();
        update();
        enableDelete(false);
        enableRotation(true); 
    }
    
    public void setearDeleeted(){
        orbit.setDeleted(-1);
        orbit2.setDeleted(-1);
    }
    
    public boolean confimacion() throws IOException{
        if(orbit.getDeleted()!=-1){
            id=orbit.getDeleted();
            s1=orbit.getElements().get(id).getNumber();
            s2=orbit2.getElements().get(id).getNumber();
        }else if( orbit2.getDeleted()!=-1 ){
            id=orbit2.getDeleted();
            s1=orbit.getElements().get(id).getNumber();
            s2=orbit2.getElements().get(id).getNumber();
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String texto="¿In Orbit: "+s1+"  |  "+" Out Orbit: "+s2+"?";
        alert.setHeaderText(texto);
        Optional<ButtonType> option = alert.showAndWait();
        
        return option.get() == ButtonType.OK;
    }
}