package solutions;
import ds.ABB;
import exception.ElementoDuplicado;
import exception.ElementoNoEncontrado;
import exception.NoHayElementos;

//falta un iterable
public class TestABB {
public static void main(String[] args) throws ElementoDuplicado, ElementoNoEncontrado, NoHayElementos {
    ABB arbol = new ABB();
    arbol.insertar(20);
    arbol.insertar(10);
    arbol.insertar(9);
    arbol.insertar(31);
    arbol.eliminar(10);
    System.out.println(arbol.maximo().toString());
    Object x=arbol.buscar(20);
    int x= 0;
    return ;

}
}
