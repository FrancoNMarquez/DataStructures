package lineales.test;
import jerarquicas.ArbolGen;
public class TestGen {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();

        arbol.insertar(1,null);
        arbol.insertar(2,1);
        arbol.insertar(3,1);
        arbol.insertar(4,3);
        arbol.insertar(5,3);
        arbol.insertar(6,3);
        arbol.insertar(7,3);
        arbol.insertar(8,4);
        arbol.insertar(9,8);
        arbol.insertar(10,9);
       // System.out.println("la altura del arbol es :"+arbol.altura());
        System.out.println("el nivel de 9 es:"+arbol.nivel(9));
        System.out.println(arbol.toString());
    }
}
