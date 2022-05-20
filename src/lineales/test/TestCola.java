package lineales.test;

import lineales.estaticas.Cola;
//import lineales.conjuntistas.dinamicas.Cola;
public class TestCola {
    public static void main(String[] args) {
        Cola queue = new Cola();
        Cola queue2 = new Cola();
        for(int i=1;i<=10;i++){
            queue.poner(i);
        }
        System.out.println(queue.toString());
        if(queue.poner(11)){
            System.out.println("entre");
        }else{
            System.out.println("error");
        }
        /*queue.sacar();
        queue.sacar();
        queue.sacar();
        queue.sacar();

        queue.poner(7777);
        queue.poner(10);
        queue.poner(88);
        queue.poner(999);
        System.out.println(queue.toString());*/
        queue2=queue.clone();
        System.out.println("esta es la pila 2 :"+queue2.toString());

    }
}
