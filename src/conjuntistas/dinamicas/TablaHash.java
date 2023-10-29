package conjuntistas.dinamicas;

public class TablaHash {
    /*
    * Las operaciones basicas son:
    * constructor vacio
    * insertar(elemento):boolean
    * eliminar(elemento):boolean
    * pertenece(elemento):boolean
    * esVacia():boolean
    * listar():lista(de elementos)
    * se puede agregar clone o vaciar
    * */

    private static final int TAMANIO = 120;
    private CeldaHash[] hash;
    private int cant;
}
