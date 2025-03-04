package ds;

import java.util.Iterator;

public class Cola<T> implements Iterable<T> {
    private Object[] elementos;
    private int frente, fin, tamaño;
    private static final int CAPACIDAD_INICIAL = 10;

    public Cola() {
        elementos = new Object[CAPACIDAD_INICIAL];
        frente = 0;
        fin = 0;
        tamaño = 0;
    }

    public void encolar(T elemento) {
        if (tamaño == elementos.length) {
            expandir();
        }
        elementos[fin] = elemento;
        fin = (fin + 1) % elementos.length;
        tamaño++;
    }

    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("La cola está vacía");
        T elemento = (T) elementos[frente];
        elementos[frente] = null;  // Evita fugas de memoria
        frente = (frente + 1) % elementos.length;
        tamaño--;
        return elemento;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

    private void expandir() {
        Object[] nuevaCola = new Object[elementos.length * 2];
        for (int i = 0; i < tamaño; i++) {
            nuevaCola[i] = elementos[(frente + i) % elementos.length];
        }
        elementos = nuevaCola;
        frente = 0;
        fin = tamaño;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indice = frente;
            private int elementosRestantes = tamaño;

            @Override
            public boolean hasNext() {
                return elementosRestantes > 0;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                T elemento = (T) elementos[indice];
                indice = (indice + 1) % elementos.length;
                elementosRestantes--;
                return elemento;
            }
        };
    }
}
