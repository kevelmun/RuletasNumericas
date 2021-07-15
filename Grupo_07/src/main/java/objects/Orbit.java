/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import TDAs.SimpleCirculeLinkedList;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Elix
 */
/**
 Se me ocurrio crear esta clase para que aqui se contengan tanto los ciculos como el aro que los contiene
 * mi idea es que se tengan solo los dos atributos que ya dejé puestos y tener un metodo el cual devuelva un circlePane actualizado
 * tipo (public CircularPane updateCirclePane) el cual recorra la lisda elements y cree el CirclePane segun corresponda,  
 * para que se lo use en el SecondaryController pues cuando hay q hacer una eliminacion, pues se va a necesitar 
 * actualizar toda la orbita(los CircleShape) ya que no encontre forma de elminarlos estaticamente con un getChildren().remove(index) TnT
 
 
 */
public class Orbit{
    private SimpleCirculeLinkedList<CircleShape> elements;
    private Circle ring;
    private double radius;
    
    
    
    //Poner constructor una vez ya este implementado el TDA de la lista circular
    public Orbit(double r, int n) {
        elements = new SimpleCirculeLinkedList<>();
        ring = new Circle(r);
        ring.setFill(null);
        ring.setStroke(Color.BLACK);
        radius = r;
        generateElements(n);
    }

    public CircularPane updateCirclePane() {
        CircularPane pane = new CircularPane(radius);
        for(int i = 0; i<elements.size(); i++) {
            CircleShape c = new CircleShape(10,Color.RED,elements.get(i).getNumber());
            pane.getChildren().add(i,c.getContent());
        }
        return pane;
    }

    public void generateElements(int cantidad){
        Random rand = new Random();
        for (int i=0 ; i<cantidad ; i++){
            CircleShape c = new CircleShape(10,Color.RED,rand.nextInt(10));
            elements.addLast(c);
        }
    }

    public void rotateLeft(){
        CircleShape tempCircle = elements.getFirat();
        elements.removeFirst();
        elements.addLast(tempCircle);
        for (int i=0 ; i<elements.size() ; i++){
            elements.get(i).setNumber(elements.get(i).getNumber()-1);
        }
    }

    public void rotateRight(){
        CircleShape tempCircle = elements.getLast();
        elements.removeLast();
        elements.addFirst(tempCircle);
        for (int i=0 ; i<elements.size() ; i++){
            elements.get(i).setNumber(elements.get(i).getNumber()+1);
        }
    }

    public Circle getRing() {
        return ring;
    }

    public void setRing(Circle ring) {
        this.ring = ring;
    }
    
}
