/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
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
    //private "aqui va la lista circular"<CircleShape> elements;
    private Circle ring;
    
    //Poner constructor una vez ya este implementado el TDA de la lista circular
    
    public CircularPane updateCirclePane() {
        //clase a completar
        return null;
    }

    public Circle getRing() {
        return ring;
    }

    public void setRing(Circle ring) {
        this.ring = ring;
    }
    
}
