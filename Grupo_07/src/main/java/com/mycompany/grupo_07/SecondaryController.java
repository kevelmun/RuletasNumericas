package com.mycompany.grupo_07;

import TDAs.SimpleCirculeLinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private boolean state;
    private int prediction;
    private int nCircles;
    private Orbit orbit;
    
    
    private void initialize(){
        
        try {
            readData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
        orbit = new Orbit(50,5);
        sPaneID.getChildren().addAll(orbit.getRing(), orbit.updateCirclePane());
        rotateOption();
    
    }
    
            
    
   public void rotateOption(){
        leftOptionID.setOnMouseClicked(e->{
            System.out.println("ROTANDO A LA IZQUIERDA");
            sPaneID.getChildren().remove(1);
            orbit.rotateLeft();
            sPaneID.getChildren().add(orbit.updateCirclePane());
            labelStateID.setText("NOW YOU GOT TO DELETE");
        });

        rigthOptionID.setOnMouseClicked(e->{
            System.out.println("ROTANDO A LA DERECHA");
            sPaneID.getChildren().remove(1);
            orbit.rotateRight();
            sPaneID.getChildren().add(orbit.updateCirclePane());
            labelStateID.setText("NOW YOU GOT TO DELETE");
        });
   }
    
   
   public void deleteOption(){
   
   //Aun no se como hacerla, mañana la terminaré
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