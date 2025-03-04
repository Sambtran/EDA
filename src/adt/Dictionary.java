package adt;
import exception.WrongIndexException;

import java.util.Iterator;

public interface Dictionary<K,V> extends Iterable<K>
{
    V put(K key, V value) throws WrongIndexException;
    V get(K key) throws WrongIndexException;
    V remove(K key) throws WrongIndexException;
    boolean contains(K key) throws WrongIndexException;
    int size();
    boolean isEmpty();
    void clear() throws WrongIndexException;
    Iterator<K> iterator();
}
