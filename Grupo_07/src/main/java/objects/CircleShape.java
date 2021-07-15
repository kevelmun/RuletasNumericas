/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 *
 * @author Elix
 */
public class CircleShape {
    private double radious;
    private Color color;
    private int number;
    private StackPane content;

    public CircleShape(double radious, Color color, int number) {
        this.radious = radious;
        this.color = color;
        this.number = number;
        Circle circle = new Circle(radious, color);
        Text text = new Text(String.valueOf(number));
        text.setBoundsType(TextBoundsType.VISUAL); 
        StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);
        this.content=stack;
    }

    public double getRadious() {
        return radious;
    }

    public void setRadious(double radious) {
        this.radious = radious;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public StackPane getContent() {
        return content;
    }

    public void setContent(StackPane content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CircleShape{" + "number=" + number + '}';
    }
    
    
    

    
    
}
