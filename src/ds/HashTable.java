package ds;

import adt.Dictionary;
import exception.WrongIndexException;
import java.util.Iterator;

public class HashTable<K, V> implements Dictionary<K, V> {
    private static class TableEntry<K, V> {
        K clave;
        V valor;

        TableEntry(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TableEntry<?, ?>)) return false;
            TableEntry<K, V> ent = (TableEntry<K, V>) obj;
            return clave.equals(ent.clave);
        }

        public V getValor() {
            return valor;
        }
    }

    private lista<TableEntry<K, V>>[] tabla;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public HashTable(int m) throws WrongIndexException {
        tabla = new lista[m];
        for (int i = 0; i < m; i++) {
            tabla[i] = new lista<>();
        }
    }
    public HashTable() throws WrongIndexException {
        tabla = new lista[16];
        for (int i = 0; i < 16; i++) {
            tabla[i] = new lista<>();
        }
    }

    private int hash(K clave) {
        return Math.abs(clave.hashCode() % tabla.length);
    }

    @Override
    public V put(K key, V value) throws WrongIndexException {
        lista<TableEntry<K, V>> lista = tabla[hash(key)];
        int pos = lista.search(new TableEntry<>(key, null));

        if (pos != -1) {
            // Si la clave ya existe, actualizar su valor
          Node x = (Node) lista.get(pos);
            TableEntry Y= (TableEntry) x.getDato();
            Y.valor=value;
        } else {
            // Insertar nueva entrada en la lista
            lista.insert(0, new TableEntry<>(key, value));
            size++;
        }
        return value;
    }

    @Override
    public V get(K key) throws WrongIndexException {
        lista<TableEntry<K, V>> lista = tabla[hash(key)];
        int pos = lista.search(new TableEntry<>(key, null));

        if (pos != -1) {
            Node x = (Node) lista.get(pos);
            TableEntry y = (TableEntry) x.getDato();
            return (V) y.getValor();
        } else {
            return null;
        }
    }

    @Override
    public V remove(K key) throws WrongIndexException {
        lista<TableEntry<K, V>> lista = tabla[hash(key)];
        int pos = lista.search(new TableEntry<>(key, null));

        if (pos != -1) {
            lista.delete(pos);
            size--;
        }
        return null;
    }

    @Override
    public boolean contains(K key) throws WrongIndexException {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() throws WrongIndexException {
        for (int i = 0; i < tabla.length; i++) {
            tabla[i] = new lista<>();
        }
        size = 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<K> {
        private int index = 0;
        private Iterator<TableEntry<K, V>> currentListIterator = tabla[0].iterator();

        @Override
        public boolean hasNext() {
            while (index < tabla.length) {
                if (currentListIterator.hasNext()) return true;
                index++;
                if (index < tabla.length) {
                    currentListIterator = tabla[index].iterator();
                }
            }
            return false;
        }

        @Override
        public K next() {
            return hasNext() ? currentListIterator.next().clave : null;
        }
    }
}
