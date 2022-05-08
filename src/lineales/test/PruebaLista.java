package lineales.test;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;
import java.util.Scanner;

public class PruebaLista {
    static Scanner sc = new Scanner((System.in));

    public static void main(String[] args) {

        Lista laLista = new Lista();
       /* for (int i = 1; i < 10; i++) {
            laLista.insertar(i * 2, i);
        }*/
        laLista.insertar(2,1);
        laLista.insertar(2,2);
        laLista.insertar(1,2);
        laLista.insertar(1,2);
        laLista.insertar(2,3);
        laLista.insertar(2,4);
        laLista.insertar(3,4);
        laLista.insertar(4,5);
        laLista.insertar(2,5);
        laLista.insertar(5,7);
        laLista.insertar(2,6);
        laLista.insertar(2,7);
        System.out.println(laLista.toString());
        //laLista.invertir();
       // laLista.eliminarApariciones(2);

        System.out.println("Nueva lista : " + laLista.toString());
    }
    public static void testLista(){
        int opcion;
        Lista listaInvertida = new Lista();
        Lista laLista = new Lista();
        Lista laLista2 = new Lista();
        Lista listaConcatenada = new Lista();

        for(int i=1;i<10;i++){
            laLista.insertar(i*2,i);
        }
        for (int i=1;i<10;i++){
            laLista2.insertar(i+2,i);
        }
        System.out.println(laLista.toString());
        // listaInvertida=invertir(laLista);
        //System.out.println("Lista invertida: "+listaInvertida.toString());
        //  System.out.println("Lista 1:"+laLista.toString()+" Lista2: "+laLista2.toString());
        // listaConcatenada = concatenar(laLista,laLista2);
        //  System.out.println("lista concatenada :"+listaConcatenada.toString());
        laLista.eliminarApariciones(6);
        System.out.println(laLista.toString());
    }
    public static void menu(){
        System.out.println("   MENU   ");
        System.out.println("1-Concatenar");
        System.out.println("2-Comprobar");
        System.out.println("3-Invertir");
    }
    public static Lista concatenar(Lista l1, Lista l2){
        //Recibe l1 y l2 y devuelva L1 concatenado a l2 en una nueva lista
        //no usar metodo longitud adentro del condicional de la repetitiva
        int longL2 = l2.longitud();
        Lista l3 = l1.clone();
        int finL3 = l3.longitud()+1;
        for(int i = 1; longL2 >= i;i++){
            l3.insertar(l2.recuperar(i),finL3);
            finL3++;
        }
        return l3;
    }
    public static boolean comprobar(Lista l1){
        boolean existe=false;
        Pila pilaAux;
        Cola colaAux;
        //Recibo lista con numeros del 0 al 9 Y devuelvo un boolean
        //Tengo que recorrer la Lista hasta encontrar un 0
        //Hasta no encontrar el 0, guardo los valores en una Cola
        //

        return existe;
    }
    public static Lista invertir(Lista l1){
        Pila p1 = new Pila();
        Lista listaAux = new Lista();
        Cola colaAux = new Cola();
        int aux,i=1;
        int longLista= l1.longitud();

        while(i<=longLista){
            p1.apilar(l1.recuperar(i));
            i++;
        }
        i=1;

        while (!p1.esVacia()){
            listaAux.insertar(p1.obtenerTope(),i);
            p1.desapilar();
            i++;
        }
        return listaAux;
    }
  /*  public static Lista intercalar(Lista l1, Lista l2){
        // devuelva una lista nueva con los elementos de L1 y L2
        //intercalados. Por ejemplo, si L1=[1,3,5] y L2=[2,4,6,7] debe devolver [1,2,3,4,5,6,7]
        Lista l3 = new Lista();
        return l3;
    }*/
}
