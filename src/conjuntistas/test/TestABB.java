package conjuntistas.test;

import conjuntistas.dinamicas.ArbolBB;

public class TestABB {
    public static void main(String[] args) {
        ArbolBB arbol = new ArbolBB();
      arbol.insertar(10);
        arbol.insertar(5);

        arbol.insertar(3);
        arbol.insertar(4);
        arbol.insertar(1);
        arbol.insertar(8);
        arbol.insertar(9);
        arbol.insertar(15);
        System.out.println(arbol.toString());
        //System.out.println(arbol.listar());
      //  System.out.println("Maximo:"+arbol.maximoElem());
        //System.out.println("Minimo:"+arbol.minimoElem());
       //arbol.eliminarMinimo();
       // arbol.eliminarMinimo();
       // System.out.println(arbol.toString());
        ArbolBB arbolClon = arbol.clone();

        System.out.println(arbolClon.toString());
    }
}
