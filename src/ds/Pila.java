package ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Pila<T> implements Iterable<T> {
    private T[] array;
    private int top;
    private static final int CAPACIDAD_INICIAL = 10;

    @SuppressWarnings("unchecked")
    public Pila() {
        array = (T[]) new Object[CAPACIDAD_INICIAL];
        top = -1;
    }

    public void push(T elemento) {
        if (top == array.length - 1) {
            expandirCapacidad();
        }
        array[++top] = elemento;
    }

    public T extraer() {
        return array[top--];
    }


    public boolean isEmpty() {
        return top == -1;
    }

    private void expandirCapacidad() {
        @SuppressWarnings("unchecked")
        T[] nuevoArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, nuevoArray, 0, array.length);
        array = nuevoArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = top;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public T next() {
                return array[index--];
            }
        };
    }
}
