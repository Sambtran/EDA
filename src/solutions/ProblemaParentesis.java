package solutions;
import java.util.Scanner;
import ds.*;
import java.util.*;
import exception.WrongIndexException;

public class ProblemaParentesis {
    public static void main(String[] args) throws WrongIndexException {
        boolean centinela = false;
        while (centinela==false){
            Scanner sc = new Scanner(System.in);
            String x = sc.nextLine();
            if(x.equals('0')){centinela=true;break;}else{
            HashTable tabla = new HashTable<>(6); // 6 tipos de parenteisis
            for (int i = 0; i < x.length(); i++) {
                char z = x.charAt(i);
                //cadenas unicamente de parentesis podemos ahorrar comprobar si lo son
                if(tabla.contains(z)){
                    tabla.put(z,(int)tabla.get(z)+1);
                }else{
                    tabla.put(z,1);
                }
            }
            if((tabla.get('(')==tabla.get(')'))&&(tabla.get('[')==tabla.get(']'))&&(tabla.get('{')==tabla.get('}'))){
                System.out.println("estan equilibrados");
            }else{
                System.out.println("NO estan equilibrados");
            }
            }

        }
    }
}
