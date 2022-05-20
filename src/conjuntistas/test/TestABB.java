package conjuntistas.test;

import conjuntistas.dinamicas.ArbolBB;

public class TestABB {
    public static void main(String[] args) {
        ArbolBB arbol = new ArbolBB();
        for (int i = 1; i <= 5 ; i++) {
            arbol.insertar(3*i);
        }
        for (int i = 1; i <= 5 ; i++) {
            arbol.insertar(2*i);
        }
        System.out.println(arbol.toString());
        System.out.println(arbol.listar());
        System.out.println("Maximo:"+arbol.maximoElem());
        System.out.println("Minimo:"+arbol.minimoElem());
    }
}
