package lineales.estaticas;

public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola(){
        this.arreglo= new Object[this.TAMANIO];
        this.frente=0;
        this.fin=0;
    }

    public boolean sacar(){
        boolean exito = true;

        if(this.esVacia()){ //si la cola esta vacia, reporta error
            exito=false;
        }else{ //al menos hay 1 elemento: avanza frente (de manera circular)
            this.arreglo[this.frente]=null;
            this.frente=(this.frente+1)%this.TAMANIO;
        }
        return exito;
    }

    public boolean poner(Object elem){
    boolean exito;

        if((this.fin+1)%TAMANIO==this.frente){
            //Si Esta lleno, no puedo poner mas elementos
            exito=false;
        }else{
            //Si no esta lleno, agrego un elemento a la cola
            this.arreglo[this.fin]=elem;
            this.fin=(this.fin+1)%TAMANIO;
            exito=true;
        }
       return exito;
    }
    public Object obtenerFrente(){
        Object devolver;
        if(!esVacia()){
            devolver=this.arreglo[frente];
        }else{
        devolver=null;
        }
    return devolver;
    }
    public boolean esVacia(){
    return (frente==fin);
    }
    public void vaciar(){
    for(int i=frente;frente<(fin%TAMANIO);i++){ //frente a fin%tamanio
        this.arreglo[i]=null;
    }
    frente=0;
    fin=0;
    }
    public Cola clone(){
        Cola clon = new Cola();
        clon.frente=this.frente;
        clon.fin=this.fin;

        for (int i = this.frente; i != this.fin; i = (i + 1) % this.TAMANIO) {
            clon.arreglo[i]=this.arreglo[i];
        }
        return clon;
    }

    /*
    Otra forma, usando el .clone()
    *   public Cola clone() {
        Cola copia = new Cola();
        copia.frente = this.frente;
        copia.fin = this.fin;
        copia.arreglo = this.arreglo.clone();
        return copia;
    }*/
    public String toString() {
        //Sirve para retornar un string con los elementos de la cola
        String cadena;
        int i;
        cadena = "[";
        if(esVacia()){
            cadena = "[Cola vacia";
        }else{
            for (i = this.frente; i != this.fin; i = (i + 1) % this.TAMANIO) {
                cadena = cadena  + this.arreglo[i].toString()+ ".";
            }

        }
        cadena = cadena + "]";
        return cadena;

    }
}
