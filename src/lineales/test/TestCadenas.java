package lineales.test;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

public class TestCadenas {
    public static void main(String[] args) {

    }
    /*public static Cola generar(Cola c1){
        //c1  a1#a2#a3#….#an,
        //Si c1 es : AB#C#DEF , entonces la operación
        //generar devolverá una Cola con el siguiente formato: ABBAAB#CCC#DEFFEDDEF.
        boolean bandera=false;
        char charAux;
        Cola clon= c1.clone();
        Pila pilaAux = new Pila();
        Cola colaAux = new Cola();
        Cola c2 = new Cola();
        while(!clon.esVacia()){
            charAux=(char)clon.obtenerFrente();
            clon.sacar();
            if(charAux=='#'){
                bandera=true;
                while ()
                //dos for aca para desapilar?

            }
            if(!bandera){
                //no es un #
                colaAux.poner(charAux);
                pilaAux.apilar(charAux);
            }
            bandera=false;
        }
        return c2;
    }*/
}
