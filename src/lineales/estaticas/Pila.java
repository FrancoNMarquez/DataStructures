package lineales.estaticas;

public class Pila {
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    //Constructor de la pila
    //Cuuando la pila esta vacia, tope = -1
    public Pila() {
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    public boolean apilar(Object nuevoElem) {
        boolean exito;
        if (this.tope + 1 >= this.TAMANIO) //Error : pila llena
        {
            exito = false;
        } else {
            //pone el elemento en el tope de la pila e incrementa tope
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }
    //Elimina el tope de la pila

    public boolean desapilar() {
        boolean exito;
        //Si la pila esta vacia devuelvo False
        if (this.tope == -1) {
            exito = false;
        } else {
            this.arreglo[tope] = null;
            tope--;
            exito = true;
        }
        return exito;
    }
    //Devuelve el objeto que esta en el tope de la pila
    public Object obtenerTope() {

        Object mostrar = null;
        if (this.tope != -1) { //Si la pila no esta vacia muestra el tope, sino devuelve null
            mostrar = this.arreglo[this.tope];
        }
        return mostrar;
    }
    //Verifica si la pila esta vacia
    public boolean esVacia() {
        return (tope == -1);

    }
    //vacia la pila
    public void vaciar() {
        //Apunta a null y deja que todos los elementos queden sin ser apuntados y los busca el garbage collector

        for (int i = 0; i <= tope; i++) {
            arreglo[i] = null;
        }
        tope = -1;
    }
    //crea una pila nueva, y copia la pila actual. Devuelve la pila clonada.
    @Override
    public Pila clone() {
        Pila clon = new Pila();
        int i = 0;
        clon.tope = this.tope;
        if (!esVacia()) {
            while (i <= tope) {
                clon.arreglo[i] = this.arreglo[i];
                i++;
            }
        }
        return clon;
    }

    public String toString() {
        //imprime la pila por pantalla
        String cadena = "";
        //menor o menor igual
        for (int i = 0; i <= tope; i++) {
            cadena = cadena + "["+ arreglo[i] + "]";
        }
        return cadena;
    }
}
