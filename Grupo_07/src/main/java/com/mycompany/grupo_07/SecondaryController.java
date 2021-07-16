package com.mycompany.grupo_07;

import TDAs.SimpleCirculeLinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
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
        });

        rigthOptionID.setOnMouseClicked(e->{
            this.setState(1);
            System.out.println("ROTANDO A LA DERECHA");
            this.rotate("right");  
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
        sPaneID.getChildren().addAll(orbit.getRing(), orbit.updateCirclePane());
        sPaneID.getChildren().addAll(orbit2.getRing(), orbit2.updateCirclePane());
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
   
}