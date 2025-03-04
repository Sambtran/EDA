package ds;
import adt.List;
import exception.WrongIndexException;

import java.util.Iterator;
import java.util.function.Consumer;

class Node <E>{
     public E dato;
     protected Node next = null;
     protected Node prev = null;

     public Node(E dato) {
         this.dato = dato;
     }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }
}
public class lista<T> implements List {
    WrongIndexException excepcionindice = new WrongIndexException();
    private int size=0;
    private Node primero = null;
    public lista() throws WrongIndexException {
        primero=null;
    }
    public lista(Object dato) throws WrongIndexException {
        insert(0,dato);
    }

    @Override
    public void insert(int pos, Object data) throws WrongIndexException {
        Node aux = primero;
        Node nuevo = new Node(data);
        if(pos>size){
            throw new WrongIndexException();
        } else if (pos==0) {
            nuevo.next=primero;
            primero=nuevo;
            size++;
        }
        else {
            for(int i=0;i<pos-1;i++){
                aux=aux.next;
            }
            if(aux.next==null){
                aux.next=nuevo;
            }else{
            nuevo.next=aux.next.next;
            aux.next=nuevo;}
            size++;
        }
    }

    @Override
    public void delete(int pos) throws WrongIndexException {
        Node aux = primero;
        if(pos>size){
            throw new WrongIndexException();
        } else if (pos==0) {
            primero=primero.next;
            size--;
        }else{
            for(int i=0;i<pos-1;i++){
                aux=aux.next;
            }
            aux.next=aux.next.next;
            size--;
        }

    }

    @Override
    public Object get(int pos) throws WrongIndexException {
        if(pos>size || pos < 0 ){
            throw excepcionindice;
        } else{
            Node aux = primero;
            for(int i=0; i<pos; i++){
                aux=aux.next;
            }
            return aux;
        }
    }

    @Override
    public int search(Object data) {
        Node aux = primero;
        int i = 0;
        while(aux!=null){
            if(aux.dato.equals(data)){
               return i;
            }
            aux=aux.next;
            i++;
        }
        return -1;
    }

    /**
     * @return
     */
    @Override
    public Iterator iterator() {
        return new CIterator();
    }

    private class CIterator<E> implements Iterator<E>{
        Node aux = primero;
          /**
           * Returns {@code true} if the iteration has more elements.
           * (In other words, returns {@code true} if {@link #next} would
           * return an element rather than throwing an exception.)
           *
           * @return {@code true} if the iteration has more elements
           */
          @Override
          public boolean hasNext() {
              if(aux.next!=null){
                  return true;
              }
              else
              return false;
          }

          /**
           * Returns the next element in the iteration.
           *
           * @return the next element in the iteration
           * @throws NoSuchElementException if the iteration has no more elements
           */
          @Override
          public E next() {
              E dato = (E) aux.dato;
              aux = aux.next;
              return dato;
          }

          /**
           * Removes from the underlying collection the last element returned
           * by this iterator (optional operation).  This method can be called
           * only once per call to {@link #next}.
           * <p>
           * The behavior of an iterator is unspecified if the underlying collection
           * is modified while the iteration is in progress in any way other than by
           * calling this method, unless an overriding class has specified a
           * concurrent modification policy.
           * <p>
           * The behavior of an iterator is unspecified if this method is called
           * after a call to the {@link #forEachRemaining forEachRemaining} method.
           *
           * @throws UnsupportedOperationException if the {@code remove}
           *                                       operation is not supported by this iterator
           * @throws IllegalStateException         if the {@code next} method has not
           *                                       yet been called, or the {@code remove} method has already
           *                                       been called after the last call to the {@code next}
           *                                       method
           * @implSpec The default implementation throws an instance of
           * {@link UnsupportedOperationException} and performs no other action.
           */
          @Override
          public void remove() {
              Iterator.super.remove();
          }

          /**
           * Performs the given action for each remaining element until all elements
           * have been processed or the action throws an exception.  Actions are
           * performed in the order of iteration, if that order is specified.
           * Exceptions thrown by the action are relayed to the caller.
           * <p>
           * The behavior of an iterator is unspecified if the action modifies the
           * collection in any way (even by calling the {@link #remove remove} method
           * or other mutator methods of {@code Iterator} subtypes),
           * unless an overriding class has specified a concurrent modification policy.
           * <p>
           * Subsequent behavior of an iterator is unspecified if the action throws an
           * exception.
           *
           * @param action The action to be performed for each element
           * @throws NullPointerException if the specified action is null
           * @implSpec <p>The default implementation behaves as if:
           * <pre>{@code
           *     while (hasNext())
           *         action.accept(next());
           * }</pre>
           * @since 1.8
           */
          @Override
          public void forEachRemaining(Consumer<? super E> action) {
              Iterator.super.forEachRemaining(action);
          }
      }


    @Override
    public int size() {
        return this.size;
    }
}
