package ds;

import java.util.Iterator;

public class ColaConPrioridad<T> implements Iterable<T> {
    // Estructura de almacenamiento de elementos
    private Object[] elementos;
    private int frente, fin, tamaño;
    private static final int CAPACIDAD_INICIAL = 10;

    // Clase interna para almacenar el elemento y su prioridad
    private static class ElementoConPrioridad<T> {
        T elemento;
        int prioridad;

        public ElementoConPrioridad(T elemento, int prioridad) {
            this.elemento = elemento;
            this.prioridad = prioridad;
        }
    }

    public ColaConPrioridad() {
        elementos = new Object[CAPACIDAD_INICIAL];
        frente = 0;
        fin = 0;
        tamaño = 0;
    }

    // Método para encolar un elemento con una prioridad
    public void encolar(T elemento, int prioridad) {
        if (tamaño == elementos.length) {
            expandir();
        }
        // Insertamos el elemento con prioridad
        ElementoConPrioridad<T> nuevoElemento = new ElementoConPrioridad<>(elemento, prioridad);

        // Buscar la posición correcta para insertar el elemento según su prioridad
        int i = fin;
        while (i > frente && ((ElementoConPrioridad<T>) elementos[(i - 1 + elementos.length) % elementos.length]).prioridad < prioridad) {
            elementos[i] = elementos[(i - 1 + elementos.length) % elementos.length];
            i = (i - 1 + elementos.length) % elementos.length;
        }

        elementos[i] = nuevoElemento;
        fin = (fin + 1) % elementos.length;
        tamaño++;
    }

    // Método para desencolar (eliminar) el elemento de mayor prioridad
    public T desencolar() {
        if (isEmpty()) throw new IllegalStateException("La cola está vacía");
        T elemento = (T) ((ElementoConPrioridad<T>) elementos[frente]).elemento;
        frente = (frente + 1) % elementos.length;
        tamaño--;
        return elemento;
    }

    // Verifica si la cola está vacía
    public boolean isEmpty() {
        return tamaño == 0;
    }

    // Expande el arreglo cuando se alcanza su capacidad máxima
    private void expandir() {
        Object[] nuevaCola = new Object[elementos.length * 2];
        for (int i = 0; i < tamaño; i++) {
            nuevaCola[i] = elementos[(frente + i) % elementos.length];
        }
        elementos = nuevaCola;
        frente = 0;
        fin = tamaño;
    }

    // Método para iterar sobre los elementos de la cola
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
                T elemento = (T) ((ElementoConPrioridad<T>) elementos[indice]).elemento;
                indice = (indice + 1) % elementos.length;
                elementosRestantes--;
                return elemento;
            }
        };
    }
}
