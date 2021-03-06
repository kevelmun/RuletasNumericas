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

public class Orbit{
    private SimpleCirculeLinkedList<CircleShape> elements;
    private Circle ring;
    private double radius;
    private int deleted;
    private int total;
    private Color color;
    private boolean state=true;
    
    public Orbit(double r, int n, Color color) {
        total = 0;
        radius = r;
        elements = new SimpleCirculeLinkedList<>();
        ring = new Circle(r);
        ring.setFill(null);
        ring.setStroke(Color.WHITE);
        this.generateElements(n);
        this.calculateTotal();
        deleted=-1;
        this.color = color;
    }

    public CircularPane updateCirclePane() {
        CircularPane pane = new CircularPane(radius);
        
        if(elements.size()==0){
        this.state=false;
        }else{
        for(int i = 0; i<elements.size(); i++) {
            CircleShape c = new CircleShape(10,this.color,elements.get(i).getNumber());
            c.getContent().setId(String.valueOf(i));
            c.getContent().setOnMouseClicked(e->{
                deleted= Integer.parseInt(c.getContent().getId());
            });
            calculateTotal();
            pane.getChildren().add(i,c.getContent());
        }}
        
        return pane;
    }

    public void generateElements(int cantidad){
        Random rand = new Random();
        for (int i=0 ; i<cantidad ; i++){
            CircleShape c = new CircleShape(20,this.color,rand.nextInt(10));
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
    
    public void calculateTotal(){
        int t = 0;
        for (int i=0 ; i<elements.size() ; i++){
            t+= elements.get(i).getNumber();
        }
        total = t;
        
    }

    public SimpleCirculeLinkedList<CircleShape> getElements() {
        return elements;
    }

    public void setElements(SimpleCirculeLinkedList<CircleShape> elements) {
        this.elements = elements;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public Circle getRing() {
        return ring;
    }

    public void setRing(Circle ring) {
        this.ring = ring;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
}
