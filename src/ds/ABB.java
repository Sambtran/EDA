package ds;

import exception.ElementoDuplicado;
import exception.ElementoNoEncontrado;
import exception.NoHayElementos;

class NodoABB<E> {
    public E dato;
    NodoABB<E> izq, der;
    public NodoABB(E dato) {
        this.dato = dato;
    }
    public NodoABB(NodoABB<E> izq, NodoABB<E> der) {
        this.izq = izq;
        this.der= der;
    }

    public E getDato() {
        return dato;
    }
}
public class ABB<E extends Comparable<E>> {

    public NodoABB<E> raiz = null;
    public ABB() { }
    public NodoABB<E> buscar(E x) throws ElementoNoEncontrado {
        NodoABB<E> res = buscar(x, raiz);
        if (res == null) throw new ElementoNoEncontrado();
        return res;
    }
    private NodoABB<E> buscar(E x, NodoABB<E> n) {
        if (n == null) return null;
        int compare = n.dato.compareTo(x);
        if (compare < 0) return buscar(x, n.der);
        else if (compare > 0) return buscar(x, n.izq);
        else return n;
    }
    public void insertar(E x) throws ElementoDuplicado {
        raiz = insertarSinDuplicados(x, raiz);
    }
    private NodoABB<E> insertarSinDuplicados(E x, NodoABB<E> n) throws ElementoDuplicado {
        if (n == null) return new NodoABB<E>(x);
        int compare = n.dato.compareTo(x);
        if (compare == 0) throw new ElementoDuplicado();
        else if (compare < 0) n.der = insertarSinDuplicados(x, n.der);
        else n.izq = insertarSinDuplicados(x, n.izq);
        return n;
    }
    public E maximo() throws NoHayElementos {
        if (raiz == null) throw new NoHayElementos();
        return maximo(raiz);
    }
    private E maximo(NodoABB<E> n) {
        NodoABB<E> actual = n;
        while (actual.der != null) actual = actual.der;
        return actual.dato;
    }
    public E minimo() throws NoHayElementos {
        if (raiz == null) throw new NoHayElementos();
        return minimo(raiz);
    }
    private E minimo(NodoABB<E> n) {
        NodoABB<E> actual = n;
        while (actual.izq != null) actual = actual.izq;
        return actual.dato;
    }
    public void eliminar(E x) throws ElementoNoEncontrado {
        raiz = eliminar(x, raiz);
    }
    private NodoABB<E> eliminar(E x, NodoABB<E> n) throws
            ElementoNoEncontrado {
        if (n == null) throw new ElementoNoEncontrado();
        int compare = n.dato.compareTo(x);
        if (compare < 0) n.der = eliminar(x, n.der); // buscar derecha
        else if (compare > 0) n.izq = eliminar(x, n.izq); // buscar izq
        else if (n.izq != null && n.der != null) { // 2 hijos
            n.dato = minimo(n.der);
            n.der = eliminarMin(n.der);
        } else {
            n = (n.izq != null)? n.izq: n.der;
        }
        return n;
    }
    public void eliminarMin() {
        raiz = eliminarMin(raiz);
    }
    public NodoABB<E> eliminarMin(NodoABB<E> n) {
        if (n.izq == null) return n.der;
        else {
            n.izq = eliminarMin(n.izq);
            return n;
        }
    }




}
