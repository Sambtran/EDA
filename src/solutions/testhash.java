package solutions;
import ds.*;
import exception.WrongIndexException;

public class testhash {

        public static void main(String[] args) throws WrongIndexException {
            HashTable<String, Integer> table = new HashTable<>(10);
            table.put("Juan", 25);
            table.put("Maria", 30);
            table.put("Maria", 45);
            table.put("Pedro", 28);


            System.out.println("Edad de Maria: " + table.get("Maria")); // 30
            System.out.println("Contiene 'Juan'? " + table.contains("Juan")); // true

            table.remove("Pedro");
            System.out.println("Contiene 'Pedro'? " + table.contains("Pedro")); // false

            System.out.println("Tamaño de la tabla: " + table.size()); // 2
        }

}
