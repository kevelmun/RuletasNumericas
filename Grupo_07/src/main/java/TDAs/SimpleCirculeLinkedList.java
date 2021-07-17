/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class SimpleCirculeLinkedList<E> implements List<E>{
    private NodeSimpleCircularList<E> last;
    private int size;
    
    public SimpleCirculeLinkedList(){
        last = null;
        size = 0;
    }
    public NodeSimpleCircularList<E> getHeader(){
        return last.getNext();
    }

    @Override
    public boolean addFirst(E e) {
        NodeSimpleCircularList<E> nodo = new NodeSimpleCircularList<>(e);
        if(e == null)
            return false;
        else if(isEmpty())
            last = nodo;
        else{
            nodo.setNext(getHeader());
            last.setNext(nodo);
        }
        size++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        NodeSimpleCircularList<E> nodo = new NodeSimpleCircularList<>(e);
        if(e == null)
            return false;
        else if(isEmpty())
            last = nodo;
        else{
            nodo.setNext(getHeader());
            last.setNext(nodo);
            last=nodo;
        }
        size++;
        return true;
    }
    @Override
    public boolean removeFirst() {
        NodeSimpleCircularList first=getHeader();
        if(isEmpty())
            return false;
        else if(last == first)
            last = null;
        else{
            last.setNext(first.getNext());
            first.setNext(null);
        }
        size--;
        return true;
    }

    @Override
    public boolean removeLast() {
        NodeSimpleCircularList first=getHeader();
        if(this.isEmpty())
            return false;
        else if(first == last)
            last= null;
        else{
            NodeSimpleCircularList<E> nodo = getHeader();
            while(nodo.getNext() != last){
                nodo = nodo.getNext();
            }
            last.setNext(null);
            last = nodo;
            last.setNext(first);
        }
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public void clear() {
        last=null; 
    }

    @Override
    public boolean add(int index, E element) {
        if(element == null || index < 0 || index >= size) {
            return false;
        }else if(index == 0){
            addFirst(element);
            size++;
            return true;
        }else if( index == size-1){
            addLast(element);
            size ++;
            return true;
        }        
        NodeSimpleCircularList<E> nodo = new NodeSimpleCircularList<>(element);
        NodeSimpleCircularList<E> n=getHeader();
        for(int j = 0; j<size; j++){
            if((index-1)  == j){
                nodo.setNext(n.getNext());
                n.setNext(nodo);
                size++;
                return true;
            }
            n=n.getNext();
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size){
            return null;
        }else if(index == 0){

            E tmp = getFirat();
            removeFirst();
            return tmp;
       }else if( index == size - 1){
           E tmp = getLast();
           removeLast();
           return tmp;
        }
        NodeSimpleCircularList<E> j = getHeader();
        for (int i = 0; i != index-1; i++){
            j = j.getNext();
        }
        System.out.println("hola 3");
        NodeSimpleCircularList<E> tmp = j.getNext();
        j.setNext(tmp.getNext());
        tmp.setNext(null);
        size--;
        return tmp.getContent();
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size)
            return null;
        int j = 0;
        NodeSimpleCircularList<E> n=getHeader();
         for(int i = 0; i<size; i++){
            if(i == index)
                return n.getContent();
            n=n.getNext();
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if (element == null || index < 0 || index >= size){
            return null;
        }
        NodeSimpleCircularList<E> n=getHeader();
         for(int i = 0; i<size; i++){
            if(i == index){
                E tmp = n.getContent();
                n.setContent(element);
                return tmp;
            }
            n=n.getNext();
        }
        return null;
    }

    public E getLast() {
        return last.getContent();
    }

    public E getFirat() {
        return getHeader().getContent();
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null || ! (o instanceof SimpleCirculeLinkedList))
            return false;
        SimpleCirculeLinkedList<E> lista = (SimpleCirculeLinkedList<E>) o;
        
        if(size != lista.size)
            return false;
        
        NodeSimpleCircularList<E> nodo = lista.getHeader();
        
        NodeSimpleCircularList<E> n=getHeader();
         for(int i = 0; i<size; i++){
            if(!nodo.getContent().equals(n.getContent()))
                return false;

            nodo = nodo.getNext();
            n=n.getNext();
        }
        return true;
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        if(isEmpty())
            return "[]";
        s.append("[");
        
        NodeSimpleCircularList<E> n=getHeader();
        for(int i = 0; i<size; i++){
            if(n != last)
                s.append(n.getContent() + ",");
            else
                s.append(n.getContent() + "]");
            n=n.getNext();
        }
        return s.toString();
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            private NodeSimpleCircularList<E> p = getHeader();
            private int x=0;
            
            @Override
            public boolean hasNext() {
                return x!= size;
            }
            
            @Override
            public E next() {
                E tmp = p.getContent();
                p = p.getNext();
                x++;
                return tmp;
            }
        };
        
        return it;
    }
    
    
}
