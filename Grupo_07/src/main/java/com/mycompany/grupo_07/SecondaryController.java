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
    
    
    private boolean state;
    private int prediction;
    private int nCircles;
    
    
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    
    private void initialize(){
       
     //TODA ESTA LOGICA TENDRA QUE SER AÑADIDA A LA CLASE Orbit en particual al metodo updateCirclePane() DE "AQUI"
     CircularPane pane = new CircularPane(50);
    for(int i = 0; i < 5/* i<orbit.get*/; i++) {
        //Aqui se crean los circulos
        //Mi idea es que la lista circular que los contiene sea recorrida para si crearlos con las clases que creé
        CircleShape c = new CircleShape(10,Color.RED,String.valueOf(i+1));
        //Aqui le pongo un indice al añadirlos para cuando se quiera hacer un giro, solo haya que actualizarlos con un indice +1 ya sea
        //a la izquierda o a la derecha en el metodo rotateOption()
        pane.getChildren().add(i,c.getContent());
        
    }
    Circle circle = new Circle(50);
    circle.setFill(null);
    circle.setStroke(Color.WHITE);
    //HASTA AQUI
    
    
    
    //LA IDEA ES QUE EN EL LA SIGUIENTE LINEA SE HAGA CREE UNA CLASE Orbit orbit=new.... y se haga 
    //sPaneID.getChildren().addAll(orbit.getRing(), Orbit.updateCirclePane());
    sPaneID.getChildren().addAll(circle, pane);
        
    rotateOption();
    
    }
    
            
    
   public void rotateOption(){
       //Aqui lo unico que tendrian que hacer es actualizar el CircularPane con una indice +1 segun corresponda
       leftOptionID.setOnMouseClicked(e->{
       
       
           System.out.println("ROTANDO A LA IZQUIERDA");
           
           //EL SIGUIENTE CODIGO ES PARA MOSTRAR LA IDEA DE LA LOGICA
           sPaneID.getChildren().remove(1);
           
           CircularPane pane = new CircularPane(50);
        for(int i = 0; i < 5; i++) {
            if(i==0){
            CircleShape c = new CircleShape(10,Color.RED,String.valueOf(5));
            pane.getChildren().add(i,c.getContent());
            }
            else{CircleShape c = new CircleShape(10,Color.RED,String.valueOf(i));
            pane.getChildren().add(i,c.getContent());
            }

            

        }
           sPaneID.getChildren().add(pane);
           labelStateID.setText("NOW YOU GOT TO DELETE");
       });
       
       rigthOptionID.setOnMouseClicked(e->{
       
       
           System.out.println("ROTANDO A LA DERECHA");
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