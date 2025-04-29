package solutions;
import ds.*;
import exception.WrongIndexException;

import java.util.Iterator;
public class testAVL {
    public static void main(String[] args) {
        eda.AVL<Integer> arbol = new eda.AVL<>();

        int[] valores = {30, 20, 40, 10, 25, 35, 50, 5, 15};

        System.out.println("Insertando elementos...");
        for (int v : valores) {
            arbol.add(v);
            System.out.println("Insertado: " + v);
        }

 Iterator it = arbol.iterator();
         while(it.hasNext()) {
             System.out.println("Elemento: " + it.next());
         }

        System.out.println("\n\nBuscando 25: " + (arbol.search(25) != null));
        System.out.println("Máximo: " + arbol.max());
        System.out.println("Mínimo: " + arbol.min());

        System.out.println("\nEliminando 20 y 30...");
        arbol.delete(20);
        arbol.delete(30);

        System.out.println("Postorden tras eliminar:");
        for (int v : arbol) {
            System.out.print(v + " ");
        }
    }
}
