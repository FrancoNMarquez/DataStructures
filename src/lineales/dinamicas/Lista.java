package lineales.dinamicas;
public class Lista {
    private Nodo cabecera;
    private int longitud;
    public Lista(){
        cabecera=null;
        longitud=0; //Hace falta?
    }
    public boolean insertar(Object nuevoElem, int pos){
        //inserta el elemento nuevo en la posicion pos
        //detecta y reporta error posicion invalida
        boolean exito=true;
        if(pos<1||pos>this.longitud+1){
            exito=false;
        }else{
            if(pos==1){ // crea un nuevo nodo y se enlaza en la cabecera
                this.cabecera= new Nodo(nuevoElem,this.cabecera);
                longitud++;
            }else{ //avanza hasta el elemento en posicion pos-1
                Nodo aux = this.cabecera;
                int i=1;
                while (i<pos-1){
                    aux=aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(nuevoElem,aux.getEnlace());
                aux.setEnlace(nuevo);
                longitud++;
            }
        }
        //Nunca hay error de lista llena, entonces devuelve true
        return exito;
    }
    public boolean eliminar(int pos){
        //Probar en posiciones invalidas, negativos y mayor a longitud y en 0
        boolean exito;
        if((esVacia())||((pos<=0)||(pos>this.longitud))){ //lista vacia
            exito=false;
        }else{
            if(pos==1){ //Eliminar el primer elemento de la fila
                this.cabecera=this.cabecera.getEnlace();
                exito=true;
                longitud--;
            }else{ //Me muevo hasta la posicion antes del que quiero eliminar
                Nodo aux=this.cabecera;
                int i=1;
               while (i<pos-1){
                   aux=aux.getEnlace();
                   i++;
               }
            //el anterior a eliminar, apunta al proximo a eliminar
                aux.setEnlace(aux.getEnlace().getEnlace());
            longitud--;
            exito=true;
        }
            }
        return exito;
        }
    public void vaciar(){
        this.cabecera=null;
        this.longitud=0;
    }
    public boolean esVacia(){
        return (this.cabecera==null);
    }
    public int longitud(){
        return this.longitud;
    }
    public Object recuperar(int pos){
        Object devolver=null;
        Nodo aux = this.cabecera;
        if((pos<0)||(pos>longitud)){
            devolver=null;
        }else{
            int i=1;
            while ((i!=pos)&&(aux!=null)){
                aux=aux.getEnlace();
                i++;
            }//Me muevo hasta aux=pos
            if(aux!=null){
                devolver=aux.getElem();
            }
        }
        return devolver;
    }
    public int localizar(Object elem){
        int pos=-1; // En caso de nunca actualizar, devuelvo -1
        int i=1; //Iterativa para recorrer el arreglo
        Nodo aux=this.cabecera; //Nodo para recorrer el arreglo
        while((i<longitud)&&(!aux.getElem().equals(elem))){
            aux=aux.getEnlace();
            i++;
            //Aumento la iterativa y muevo el nodo.
        }
        // En caso de encontrar el elemento, guardo su posicion.
        if(aux!=null){
            if(aux.getElem().equals(elem)){
                pos=i;
            }
        }

        return pos;
    }
    public Lista clone() {
        //Es de orden (N) porque la recorro nodo por nodo
        Lista clon = new Lista();
        Nodo auxOriginal=this.cabecera;
        Nodo auxClon;
        clon.longitud=this.longitud;
        if(!esVacia()){
            Nodo nuevo = new Nodo(auxOriginal.getElem(),null);
            clon.cabecera=nuevo;
            auxClon=clon.cabecera;

            auxOriginal=auxOriginal.getEnlace();
            while(auxOriginal!=null){
                nuevo= new Nodo(auxOriginal.getElem(),null);
                auxClon.setEnlace(nuevo);
                auxClon=nuevo;
                auxOriginal=auxOriginal.getEnlace();
            }
        }

        return clon;
    }
    public String toString(){
        String cadena="[";
        Nodo aux=this.cabecera;

        if(esVacia()){
            cadena="[Lista Vacia";
        }else{
            while(aux!=null){
                cadena += aux.getElem().toString() ;
                if(aux.getEnlace()!=null){
                    cadena+=",";
                }
                aux=aux.getEnlace();
            }
        }
        cadena+="]";
        return cadena;
    }
    public void invertir(){
        Nodo actual,previo,temporal ;
        actual = cabecera;
        previo=null;
        temporal=null;

        while (actual!=null){
            temporal=actual.getEnlace();
            actual.setEnlace(previo);
            previo=actual;
            actual=temporal;
        }

        cabecera=previo;
    }
 public void eliminarApariciones(Object x) {
     //tengo que ir recorriendo la lista
     //Me paro en un elemento y pregunto por el siguiente
     //Si el siguiente es el que quiero eliminar get.enlace.getEnlace
     Nodo aux = cabecera;
     int longLista = longitud, i=1;
     //uasr un while solo para el if de aca abajo
     while((aux!=null)&&(aux.getElem().equals(x))){
         //Borrar todos los primeros, no solo el primero
         //Caso especial Eliminar el primer elemento
         cabecera = aux.getEnlace();
         aux = cabecera;
     }
     if(!esVacia()&& (!aux.getElem().equals(x))){
         do {
             if ((aux.getEnlace() != null) && (aux.getEnlace().getElem().equals(x))) {
                 aux.setEnlace(aux.getEnlace().getEnlace());
             } else {
                 aux = aux.getEnlace();

             }
             i++;
         } while (aux != null);

     }
 }
 public Lista obtenerMultiplos(int num){
     Lista l1 = new Lista();
     Nodo clon = this.cabecera;
     Nodo aux = this.cabecera;
     int i=1;
     while(aux!=null){
         if(i%num==0){
            clon.setEnlace(aux);
         }
         aux=aux.getEnlace();
     }
     return l1;
 }
}

