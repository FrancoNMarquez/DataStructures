package lineales.test;

import jerarquicas.ArbolBin;
import lineales.dinamicas.Lista;

public class TestBinario {
    public static void main(String[] args) {
        ArbolBin arbol=new ArbolBin();
        Lista l1 = new Lista();
        l1.insertar(1,1);
        l1.insertar(3,2);
        l1.insertar(2,3);
        l1.insertar(6,4);
        l1.insertar(8,5);




        arbol.insertar(1, 1, 'I');
        arbol.insertar(3, 1, 'I');
        arbol.insertar(4, 1, 'D');
        arbol.insertar(9, 3, 'I');
        arbol.insertar(2, 3, 'D');
        arbol.insertar(6, 2, 'I');
        arbol.insertar(8, 6, 'I');
        arbol.insertar(5, 6, 'D');
        arbol.insertar(10, 4, 'D');

        System.out.println(arbol.toString());
        if(arbol.verificarPatron(l1)){
            System.out.println("patron correcto");
        }else{
            System.out.println("patron incorrecto");
        }

    }
}
