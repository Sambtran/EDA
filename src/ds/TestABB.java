package ds;

import exception.ElementoDuplicado;
import exception.ElementoNoEncontrado;
import exception.NoHayElementos;

public class TestABB {
    public static void main(String[] args) {
        ABB<Integer> arbol = new ABB<>();

        int[] valores = {30, 20, 40, 10, 25, 35, 50, 5, 15};

        // Insertar valores
        for (int valor : valores) {
            try {
                arbol.insertar(valor);
                System.out.println("Insertado: " + valor);
            } catch (ElementoDuplicado e) {
                System.out.println("Duplicado: " + valor);
            }
        }

        // Buscar elementos
        try {
            System.out.println("Buscar 25: " + arbol.buscar(25).dato);
            System.out.println("Buscar 100: ");
            arbol.buscar(100);  // Debería lanzar excepción
        } catch (ElementoNoEncontrado e) {
            System.out.println("Elemento no encontrado.");
        }

        // Mínimo y máximo
        try {
            System.out.println("Mínimo: " + arbol.minimo());
            System.out.println("Máximo: " + arbol.maximo());
        } catch (NoHayElementos e) {
            System.out.println("Árbol vacío.");
        }

        // Eliminar un valor
        try {
            arbol.eliminar(20);
            System.out.println("Eliminado 20.");
        } catch (ElementoNoEncontrado e) {
            System.out.println("Elemento no encontrado para eliminar.");
        }

        // Eliminar mínimo
        arbol.eliminarMin();
        System.out.println("Eliminado el mínimo.");

        // Intentar insertar duplicado
        try {
            arbol.insertar(25); // Ya está
        } catch (ElementoDuplicado e) {
            System.out.println("No se pudo insertar duplicado: 25");
        }
    }
}
