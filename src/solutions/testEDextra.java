package solutions;

import ds.*;

public class testEDextra {
        public static void main(String[] args) {
            // Prueba para la Cola
            System.out.println("Prueba de Cola:");
            Cola<Integer> cola = new Cola<>();
            cola.encolar(1);
            cola.encolar(2);
            cola.encolar(3);

            System.out.println("Elementos en la cola:");
            for (Integer elem : cola) {
                System.out.println(elem);
            }

            System.out.println("Dequeue: " + cola.dequeue());
            System.out.println("Elementos en la cola después de dequeue:");
            for (Integer elem : cola) {
                System.out.println(elem);
            }
            System.out.println();

            // Prueba para la Cola con Prioridad
            System.out.println("Prueba de Cola con Prioridad:");
            ColaConPrioridad<Integer> colaPrioridad = new ColaConPrioridad<>();
            colaPrioridad.encolar(10, 1);  // Elemento 10 con prioridad 1
            colaPrioridad.encolar(20, 2);  // Elemento 20 con prioridad 2
            colaPrioridad.encolar(30, 0);  // Elemento 30 con prioridad 0

            System.out.println("Elementos en la cola con prioridad:");
            for (Integer elem : colaPrioridad) {
                System.out.println(elem);
            }

            System.out.println("Dequeue de la cola con prioridad: " + colaPrioridad.desencolar());
            System.out.println("Elementos en la cola con prioridad después de dequeue:");
            for (Integer elem : colaPrioridad) {
                System.out.println(elem);
            }
            System.out.println();

            // Prueba para la Pila
            System.out.println("Prueba de Pila:");
            Pila<Integer> pila = new Pila<>();
            pila.push(1);
            pila.push(2);
            pila.push(3);

            System.out.println("Elementos en la pila:");
            for (Integer elem : pila) {
                System.out.println(elem);
            }

            System.out.println("Pop: " + pila.extraer());
            System.out.println("Elementos en la pila después de pop:");
            for (Integer elem : pila) {
                System.out.println(elem);
            }
        }
    }

