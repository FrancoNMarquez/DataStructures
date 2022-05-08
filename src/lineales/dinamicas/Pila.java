package lineales.dinamicas;

public class Pila {
    private Nodo tope;

    //Constructor
    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        //crea un nuevo nodo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);

        //actualiza el tope para que apunte al nodo nuevo
        this.tope = nuevo;
        //nunca hay error de pila llena, entonces devuelve true
        return true;

        //this.tope= new Nodo(elem,this.tope) Hago todo junto (Forma 2)
        //nuevo = new Nodo(elem) creo sin elem (Forma 3)
        //nuevo.setEnlace(this.tope)
        //this.tope=nuevo
    }

    public boolean desapilar() {
        //elimina el elemento en el tope de la pila, y el anteultimo, pasa a ser el nuevo tope
        boolean exito = false; //En caso de no poder desapilar por estar la pila vacia, devuelve False

        if (this.tope != null) {
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;
    }
    //Devuelve el elemento en el tope de la pila
    public Object obtenerTope() {
        Object elemTope = null;
        if (this.tope != null) {
            elemTope = this.tope.getElem();
        }
        return elemTope;
    }
    //Verifica que sea vacia
    public boolean esVacia() {
        return (tope == null);
    }
    //vacia la pila
    public void vaciar() {
        this.tope = null;
    }
    //crea una pila nueva, y copia la pila actual. Devuelve la pila clonada.
    public Pila clone() {

        Pila clon = new Pila();
        Nodo aux = this.tope;
        Nodo auxClon;
        if (!esVacia()) {
            Nodo nuevo = new Nodo(aux.getElem(), null);
            clon.tope = nuevo;
            auxClon = clon.tope;

            aux = aux.getEnlace();
            while (aux != null) {
                nuevo = new Nodo(aux.getElem(), null);
                auxClon.setEnlace(nuevo);
                auxClon=auxClon.getEnlace();
                aux = aux.getEnlace();
            }
        }
        return clon;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        //imprime la pila por pantalla
        String s;
        if (this.tope == null) {
            s = "Pila vacia";
        } else {
            //se ubica para recorrer la pila
            Nodo aux = this.tope;
            s = "[";

            while (aux != null) {
                //agrega el texto del elem y avanza
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ",";
                }
            }
            s += "]";
        }
        return s;
    }

}
