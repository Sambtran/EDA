package solutions;
import ds.lista;
import exception.WrongIndexException;

import java.util.Iterator;

public class testlista {
    public static void main(String[] args) throws WrongIndexException {
        lista ls = new lista();
        ls.insert(0,"perro");
        ls.insert(1,3);
        ls.insert(2,4);
        Iterator iterable =ls.iterator();
        iterable.forEachRemaining(System.out::println);


    }

}
