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
        System.out.println("Elimino el 8 \n");
        arbol.eliminar(8);
        System.out.println(arbol.toString());
    }
}
