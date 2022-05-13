package conjuntistas.estaticas;
//Heap Minimo
public class ArbolHeap {
    private final int TAMANIO = 10;
    private Comparable[] heap;
    private int ultimo;

    public ArbolHeap() {
        this.ultimo = 0;
        this.heap = new Comparable[TAMANIO];
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            //Estructura vacia
            exito = false;
        } else {
            //saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            //Reestablece la propiedad de heap minimo
            hacerBajar(1);
            exito = true;
        }
        return exito;

    }

    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                //Temp tiene al menos un hijo(izq) y lo considera menor

                if (posH < this.ultimo) {
                    //HijoMenor tiene hermano derecho

                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        //El hijo derecho es el menor de los dos
                        posH++;
                    }
                }
                //compra al hijo menor con el padre
                if (this.heap[posH].compareTo(temp) < 0) {
                    //el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    //el padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                //el temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }

    public boolean esVacio() {
        return (this.ultimo == 0);
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = false;

        if (ultimo + 1 < TAMANIO) { //si no esta llena
            exito = true;
            ultimo++;
            heap[ultimo] = elemento; //lo inserto
            hacerSubir(ultimo); //Lo posiciono en donde debe
        }
        return exito;
    }

    private void hacerSubir(int posHijo) {
        //metodo para hacer subir el ultimo vaalor ingresado hacia la posicion que debe estar
        int posP; //posicion del padre
        Comparable temp = this.heap[posHijo];
        boolean exito = true;
        while (exito) {
            posP = posHijo / 2; //Como los hijos de un nodo estan en la posicion 2k y 2k+1
                                //su padre esta en la posicion k/2 , con division entera
            if (posP >= 1) {
                if (this.heap[posP].compareTo(temp) < 0) { //Si el padre es mayor al hijo, se intercambian
                    //intercambio
                    this.heap[posHijo] = this.heap[posP];// intercambio
                    this.heap[posP] = temp;
                    posHijo = posP;
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
    }
    public Comparable recuperarCima(){
        Comparable aux=null;
        if(this.ultimo!=0){
            aux=this.heap[1];//El primer elemento del arreglo es la Cima
        }
        return aux;
    }
    @Override
    public String toString() {
        String cad = "Arbol Heap Vacio";
        if (this.ultimo != 0) {
            cad = "";
            for (int i = 1; i <= this.ultimo; i++) {
                cad += this.heap[i].toString() + " :";
                if (2 * i <= this.ultimo) { //si estoy en una posicion Izquierda
                    cad += " HI->" + this.heap[2 * i].toString();
                }
                if (2 * i + 1 <= this.ultimo) { // si estoy en una posicion Derecha
                    cad += " HD->" + this.heap[2 * i + 1].toString();
                }
                cad += "\n";
            }
        }
        return cad;
    }
    @Override
    public ArbolHeap clone() {
        ArbolHeap n = new ArbolHeap();
        n.heap = this.heap.clone();
        n.ultimo = this.ultimo;
        return n;
    }
}

