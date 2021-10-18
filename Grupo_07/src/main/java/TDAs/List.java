/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;
//No parar bola
/**
 *
 * @author Usuario
 */
public interface List<E> extends Iterable<E>{
    
    public boolean addFirst(E e);

    public boolean addLast(E e); 

    public boolean removeFirst(); 

    public boolean removeLast(); 

    public int size();

    public boolean isEmpty();

    public void clear();
    
    public boolean add(int index, E element);

    public E remove(int index); 

    public E get(int index); 

    public E set(int index, E element);
    
    public String toString();

   
}
