package lineales.dinamicas;

public class Cola {
    Nodo frente;
    Nodo fin;

    public Cola(){
        frente=null;
        fin=null;
    }

    public boolean sacar(){
        boolean exito=true;

        if(this.frente==null){
            //la cola esta vacia reporta error
            exito=false;
        }else{
            //al menos hay un elemento
            //quita el primer elemento y actualiza frente (y fin se queda vacia)
            this.frente=this.frente.getEnlace();
            if(this.frente==null)
                this.fin=null;
        }
        return  exito;
    }
    public boolean poner(Object elem){
        boolean exito=true;
        Nodo nodoNuevo= new Nodo(elem,null);;
        if(esVacia()){
            this.frente=nodoNuevo;
            this.fin=nodoNuevo;
        }else{
            //El nodo nuevo apunta a null , y seteo que el fin, pase a apuntar al nodo nuevo
            this.fin.setEnlace(nodoNuevo);
            this.fin=nodoNuevo; // ahora Fin apunta a nodo Nuevo
        }
        return exito;
    }
    public boolean esVacia(){
        return ((this.frente==null)&&(this.fin==null));
    }
    public void vaciar(){
        this.frente=null;
        this.fin=null;
    }
    public Object obtenerFrente(){
    Object elemTope;
    if(esVacia()){
        elemTope=null;
    }else{
        elemTope=this.frente.getElem();
    }
    return elemTope;
    }
    public Cola clone(){
        Cola clon = new Cola();
        Nodo aux=this.frente;
        //Fin hace de auxClon
        if(!esVacia()){
            Nodo nuevo = new Nodo(aux.getElem(),null);
            clon.frente=nuevo;
            clon.fin=nuevo;

            aux=aux.getEnlace();
            while(aux!=null){
                nuevo= new Nodo(aux.getElem(),null);
                clon.fin.setEnlace(nuevo);
                clon.fin=nuevo;
                aux=aux.getEnlace();
            }

        }
        return clon;
    }
    public String toString(){
        String cadena;
        if(esVacia()){
            cadena="Cola vacia";
        }else{
            Nodo aux = frente;
            cadena="[";

            while(aux!=null){
                cadena+=aux.getElem().toString();
                aux=aux.getEnlace();
                if(aux!=null){
                    cadena+=",";
                }
            }
            cadena+="]";
        }
    return cadena;
    }
}
