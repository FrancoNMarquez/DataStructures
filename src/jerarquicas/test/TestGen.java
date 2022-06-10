package jerarquicas.test;

import jerarquicas.ArbolGen;
import lineales.dinamicas.Lista;

import java.util.List;

public class TestGen {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        arbol.insertar(1,null);
        arbol.insertar(2,1);
        arbol.insertar(3,1);
        arbol.insertar(4,1);
        arbol.insertar(5,4);
        arbol.insertar(6,5);
        arbol.insertar(7,6);

        System.out.println(arbol.toString());
        Lista ls = new Lista();
        ls.insertar(1,1);
        ls.insertar(4,2);
        ls.insertar(5,3);
        ls.insertar(6,4);

        System.out.println(arbol.verificarCamino(ls));
    }
}
