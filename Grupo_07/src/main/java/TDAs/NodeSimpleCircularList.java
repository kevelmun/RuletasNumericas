/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

/**
 *
 * @author Usuario
 */
public class NodeSimpleCircularList<E> {
    private E content;
    private NodeSimpleCircularList<E> next;
    
    public NodeSimpleCircularList(E content) {
        this.content = content;
        this.next=this;
        
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public NodeSimpleCircularList<E> getNext() {
        return next;
    }

    public void setNext(NodeSimpleCircularList<E> next) {
        this.next = next;
    }
    
    
}
