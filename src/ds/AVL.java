package eda;

import java.util.Iterator;
import java.util.NoSuchElementException;

class NodoAVL<E> {
    E dato;
    NodoAVL<E> izq, der;
    int altura;

    public NodoAVL(E dato) {
        this.dato = dato;
        this.altura = 1;
    }
}

public class AVL<E extends Comparable<E>> implements Iterable<E> {

    private NodoAVL<E> raiz;

    public NodoAVL<E> search(E x) {
        NodoAVL<E> actual = raiz;
        while (actual != null) {
            int cmp = x.compareTo(actual.dato);
            if (cmp < 0) actual = actual.izq;
            else if (cmp > 0) actual = actual.der;
            else return actual;
        }
        return null;
    }

    public void add(E x) {
        raiz = insertar(x, raiz);
    }

    private NodoAVL<E> insertar(E x, NodoAVL<E> n) {
        if (n == null) return new NodoAVL<>(x);

        int cmp = x.compareTo(n.dato);
        if (cmp < 0) n.izq = insertar(x, n.izq);
        else if (cmp > 0) n.der = insertar(x, n.der);
        else return n; // Duplicado, no se inserta

        actualizarAltura(n);
        return balancear(n);
    }

    public void delete(E x) {
        raiz = eliminar(x, raiz);
    }

    private NodoAVL<E> eliminar(E x, NodoAVL<E> n) {
        if (n == null) return null;

        int cmp = x.compareTo(n.dato);
        if (cmp < 0) n.izq = eliminar(x, n.izq);
        else if (cmp > 0) n.der = eliminar(x, n.der);
        else {
            if (n.izq == null || n.der == null)
                n = (n.izq != null) ? n.izq : n.der;
            else {
                NodoAVL<E> sucesor = min(n.der);
                n.dato = sucesor.dato;
                n.der = eliminar(sucesor.dato, n.der);
            }
        }
        if (n == null) return null;

        actualizarAltura(n);
        return balancear(n);
    }

    public E max() {
        if (raiz == null) throw new NoSuchElementException();
        NodoAVL<E> actual = raiz;
        while (actual.der != null) actual = actual.der;
        return actual.dato;
    }

    public E min() {
        if (raiz == null) throw new NoSuchElementException();
        return min(raiz).dato;
    }

    private NodoAVL<E> min(NodoAVL<E> n) {
        while (n.izq != null) n = n.izq;
        return n;
    }

    private int altura(NodoAVL<E> n) {
        return n == null ? 0 : n.altura;
    }

    private void actualizarAltura(NodoAVL<E> n) {
        n.altura = 1 + Math.max(altura(n.izq), altura(n.der));
    }

    private int balanceFactor(NodoAVL<E> n) {
        return altura(n.izq) - altura(n.der);
    }

    private NodoAVL<E> balancear(NodoAVL<E> n) {
        int bf = balanceFactor(n);
        if (bf > 1) {
            if (balanceFactor(n.izq) < 0)
                n.izq = rotarIzq(n.izq);
            return rotarDer(n);
        }
        if (bf < -1) {
            if (balanceFactor(n.der) > 0)
                n.der = rotarDer(n.der);
            return rotarIzq(n);
        }
        return n;
    }

    private NodoAVL<E> rotarDer(NodoAVL<E> y) {
        NodoAVL<E> x = y.izq;
        NodoAVL<E> T2 = x.der;
        x.der = y;
        y.izq = T2;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    private NodoAVL<E> rotarIzq(NodoAVL<E> x) {
        NodoAVL<E> y = x.der;
        NodoAVL<E> T2 = y.izq;
        y.izq = x;
        x.der = T2;
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    // Iterador postorden
    @Override
    public Iterator<E> iterator() {
        return new PostOrdenIterador<>(raiz);
    }

    private static class PostOrdenIterador<E> implements Iterator<E> {
        private java.util.Stack<NodoAVL<E>> pila = new java.util.Stack<>();
        private NodoAVL<E> anterior = null;

        public PostOrdenIterador(NodoAVL<E> raiz) {
            pushIzq(raiz);
        }

        private void pushIzq(NodoAVL<E> nodo) {
            while (nodo != null) {
                pila.push(nodo);
                nodo = nodo.izq;
            }
        }

        @Override
        public boolean hasNext() {
            return !pila.isEmpty();
        }

        @Override
        public E next() {
            while (hasNext()) {
                NodoAVL<E> actual = pila.peek();
                if (actual.der != null && actual.der != anterior) {
                    pushIzq(actual.der);
                } else {
                    anterior = pila.pop();
                    return anterior.dato;
                }
            }
            throw new NoSuchElementException();
        }
    }
}
