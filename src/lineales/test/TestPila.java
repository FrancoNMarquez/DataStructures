package lineales.test;
import lineales.dinamicas.Pila;
import java.util.Scanner;
public class TestPila {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pila unaPila = new Pila();
        Pila clon = new Pila();
        /*String opcion = sc.nextLine();*/

        unaPila.apilar(3);
        unaPila.apilar(6);
        unaPila.apilar(7);
        unaPila.apilar(2);
        unaPila.apilar(11);
        unaPila.apilar(44);

        System.out.println(unaPila.toString());

        unaPila.vaciar();

        unaPila.apilar(33);
        unaPila.apilar(63);
        unaPila.apilar(37);
        unaPila.apilar(23);
        unaPila.apilar(131);
        unaPila.apilar(22);

        unaPila.desapilar();
        unaPila.desapilar();
        unaPila.desapilar();
        unaPila.desapilar();
        unaPila.desapilar();
        System.out.println(unaPila.toString());
        clon=unaPila.clone();

        System.out.println("Pila clon");
        System.out.println(clon.toString());

    }
}
