/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
/**
 *
 * @author Elix
 */
public class CircularPane extends Pane {

    private final double radius;
    //El atributo state ayuda a ver si el juego debe continuar o no
    private boolean state = true;
    
    public CircularPane(double radius){
        this.radius=radius;
 

        setMinSize(2*radius, 2d*radius);
        setPrefSize(2*radius, 2d*radius);
        setMaxSize(2*radius, 2d*radius);
    }

    
    @Override
    protected void layoutChildren() {
        if(getChildren().size()!=0){
        final double increment = 360 / getChildren().size();
        double degreese = 0;
        for (Node node : getChildren()) {
            double x = this.radius * Math.cos(Math.toRadians(degreese)) + getWidth() / 2;
            double y = this.radius * Math.sin(Math.toRadians(degreese)) + getHeight() / 2;
            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degreese += increment;
        }
        }else{
        //Si el circularPane esta vacio significa que el jugador se quedo sin circulos en orbita y por tanto perdio el juego
        this.state=false;
        }
        
    }
}