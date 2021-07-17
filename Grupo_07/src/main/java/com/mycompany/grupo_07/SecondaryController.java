package com.mycompany.grupo_07;

import TDAs.SimpleCirculeLinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import objects.CircleShape;
import objects.CircularPane;
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
    //state = -1 (turno de rotar)
    //state = 1 (turno de eliminar)
    private int state=0;
    private int prediction;
    private int nCircles;
    private Orbit orbit;
    private Orbit orbit2;
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
    
    private int s1, s2, id;
    
    @FXML
    private void initialize(){
        
        try {
            readData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
        orbit = new Orbit(40,nCircles);
        orbit2 = new Orbit(80,nCircles);
        update();
        System.out.println(state);
        
        
        if(state==-1 || state ==0){
            
            rotateOption();
        }if(state==1 || state ==0){
            System.out.println("HOLA");
        }
    }
    
    
    public void rotateOption(){
        
        leftOptionID.setOnMouseClicked(e->{
            System.out.println("ROTANDO A LA IZQUIERDA");
            this.setState(1);
            this.rotate("left");
            enableRotation(false);
            enableDelete(true);
        });

        rigthOptionID.setOnMouseClicked(e->{
            this.setState(1);
            System.out.println("ROTANDO A LA DERECHA");
            this.rotate("right");
            enableRotation(false);
            enableDelete(true);
        });   
   }

    public void rotate(String direction){
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
        labelStateID.setText("NOW YOU GOT TO DELETE");
        
    }

    public void setState(int state) {
        this.state = state;
    }
    
    public void update(){
        sPaneID.getChildren().addAll(orbit2.getRing(), orbit2.updateCirclePane());
        sPaneID.getChildren().addAll(orbit.getRing(), orbit.updateCirclePane());
        labelActualNID.setText(String.valueOf(orbit.getTotal()+ orbit2.getTotal()));
    }
   
    public void deleteOption(){
       this.setState(-1);
       System.out.println("ELIMINANDO");  
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
    }
    public void enableDelete(boolean valor){
        btnDeleteID.setVisible(valor);
    }
    
    @FXML
    private void btnDelete(MouseEvent event) {
        int a= orbit.getDeleted();
        int b= orbit2.getDeleted();
        if(a!=(-1) || b!=(-1)){
            if(confimacion()){
                int c=Math.max(a, b);
                eliminarActualizar(c);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select in some circle to be able to delete the couple");
            alert.setHeaderText("Incorrect selection");
            alert.show();
              
        } 
    }
    public void eliminarActualizar(int c){
        orbit.getElements().remove(c);
        orbit2.getElements().remove(c);
        orbit.setDeleted(-1);
        orbit2.setDeleted(-1);
        sPaneID.getChildren().clear();
        update();
        enableDelete(false);
        enableRotation(true); 
    }
    
    public boolean confimacion(){
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