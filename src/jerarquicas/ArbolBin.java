package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin (){
        raiz=null;
    }
 public boolean insertar(Object elemNuevo, Object elemPadre,char lugar){
        //Inserta elemNuevo como hijo del primer nodo encontrado en peorden
        //igual a elemPadre, como hijo izquierdo(I) o derecho(D), segun
        //lo indique el parametro lugar
        boolean exito=true;
        if(this.raiz==null){
            //si el arbol esta vacio, poenmos el elem nuevo en la raiz
            this.raiz=new NodoArbol(elemNuevo,null,null);
        }else {
            NodoArbol nodoPadre = obtenerNodo(this.raiz,elemPadre);
            if(nodoPadre!=null){
                if(lugar=='I' && nodoPadre.getIzquierdo()==null){
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo,null,null));
                }else{
                    if(lugar=='D'&& nodoPadre.getDerecho()==null){
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo,null,null));
                    }else {
                        exito=false;
                    }
                }
            }else{
                exito=false;
            }
        }
        return exito;
    }
    private NodoArbol obtenerNodo(NodoArbol n , Object buscado){
        //Metodo PRIVADO que busca un elemento y devuelve el nodo que
        //Lo contiene. Si no se encuentra buscado devuelve null

        NodoArbol resultado=null;
        if(n!=null){
            if(n.getElem().equals(buscado)){
                resultado=n;
        }else {
            //no es el buscado, busca primero en el HI
            resultado=obtenerNodo(n.getIzquierdo(),buscado);
            //Si no lo encuentro, busco en el derecho
            if(resultado==null)
            resultado=obtenerNodo(n.getDerecho(),buscado);
        }
        }
        return resultado;
    }
    public boolean esVacio(){
        return this.raiz==null;
    }
    public void vaciar(){
        this.raiz=null;
    }
    public int altura(){
        // Devuelve la altura del árbol, es decir la longitud del camino más largo desde la raíz hasta una hoja
       // (Nota: un árbol vacío tiene altura -1 y una hoja tiene altura 0).
        int laAltura;
        if(esVacio()){
            laAltura=-1;
        }else{
            laAltura=alturaAux(this.raiz);
        }
        return laAltura;
    }
    private int alturaAux(NodoArbol nodo){
        //Solo entra a este metodo, si el arbol no esta vacio
        int alturaDer=0;
        int alturaIzq=0;
        int alturaMax;

        if(nodo.getIzquierdo()==null&&nodo.getDerecho()==null){
            //es hoja
            alturaMax=0;
        }else{
            //Si no es hoja
            if(nodo.getIzquierdo()!=null){
                //Si tiene HI bajo
                alturaIzq=alturaAux(nodo.getIzquierdo())+1;
            }
            if(nodo.getDerecho()!=null){
                //Si tiene HD bajo
                alturaDer=alturaAux(nodo.getDerecho())+1;
            }
        }
        if (alturaDer>alturaIzq){
            alturaMax=alturaDer;
        }else{
            alturaMax=alturaIzq;
        }
        return alturaMax;
    }
    public int nivel(Object elem){
        // Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en el árbol devuelve -1
        int elNivel;
        return elNivel=nivelAux(this.raiz,elem,0);
    }
    private int nivelAux(NodoArbol nodo, Object elem, int nivel){
        // Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en el árbol devuelve -1.
        int elNivel=-1;
        if(nodo!=null){
            if(nodo.getElem()==elem){
                //Encontre el nodo
                elNivel=nivel;
            }else {
                //Sigo bajando
                elNivel=nivelAux(nodo.getIzquierdo(),elem,nivel+1);
                if(elNivel==-1){
                    elNivel=nivelAux(nodo.getDerecho(),elem,nivel+1);
                }
             }
        }
    return elNivel;
    }
    public Object padre(Object elemento) {
        NodoArbol padre = null;
        Object retornar=null;
        if (this.raiz == null || this.raiz.getElem().equals(elemento)) {
            //si el arbol esta vacio o el elemento es la raiz
           retornar = null;
        } else if (this.raiz != elemento) {
            padre = padreAux(this.raiz, elemento);
            if(padre!=null){
                retornar=padre.getElem();
            }
        }
        return retornar;
    }
    private NodoArbol padreAux(NodoArbol nodo, Object elemento) {
        NodoArbol res = null;
        if (nodo != null) {
            NodoArbol izq = nodo.getIzquierdo();
            NodoArbol der = nodo.getDerecho();
            if ((izq != null && izq.getElem().equals(elemento)) || (der != null && der.getElem().equals(elemento))) {
                //pregunto por los 2 hijos del nodo en el que estoy parado
                res = nodo;
            } else {
                //llamo con el HI
                res = padreAux(nodo.getIzquierdo(), elemento);
                if (res == null) { // si no encontre por la rama izquierda, llamo con HD
                    res = padreAux(nodo.getDerecho(), elemento);
                }
            }
        }
        return res;
    }
    public Lista listarPreorden(){
        //Retorna una lista con los elementos del arbol en PREORDEN
        Lista lis= new Lista();
        listarPreordenAux(this.raiz,lis);
        return lis;
    }
    private void listarPreordenAux(NodoArbol nodo,Lista lis){
        //Metodo recursivo PRIVADO porque su parametro es un nodoArbol
        if(nodo!=null){
            //Visita el elemento en el nodo
            lis.insertar(nodo.getElem(),lis.longitud()+1); //(1)

            //Recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(),lis); //(2)
            listarPreordenAux(nodo.getDerecho(),lis); //(3)
        }
    }
    public Lista listarInorden(){
        Lista lis = new Lista();
        listarInordenAux(this.raiz,lis);
        return lis;
    }
    private void listarInordenAux(NodoArbol nodo , Lista lis){
        /*1. recorrer hijo_izquierdo(nodo) en inorden
             2. visitar la raíz del subárbol (nodo)
                 3. recorrer hijo_derecho(nodo) en inorden*/
        if(nodo!=null){
            NodoArbol izq = nodo.getIzquierdo();
            NodoArbol der = nodo.getDerecho();
            listarInordenAux(izq,lis);

            lis.insertar(nodo.getElem(),lis.longitud()+1);

            listarInordenAux(der,lis);

        }
    }
    public Lista listarPosorden(){
        Lista lis = new Lista();
        listarPosordenAux(this.raiz,lis);
        return lis;
    }
    private void listarPosordenAux(NodoArbol nodo,Lista lis){

        if(nodo!=null){
            NodoArbol izq = nodo.getIzquierdo();
            NodoArbol der = nodo.getDerecho();
            listarPosordenAux(izq,lis);
            listarPosordenAux(der,lis);
            lis.insertar(nodo.getElem(),lis.longitud()+1);
        }

    }
    public Lista listarPorNiveles(){
        Cola queue = new Cola();
        Lista lis = new Lista();
        if(!esVacio()){
            NodoArbol nodo = this.raiz; //new NodoArbol(this.raiz.getElem(),null,null);

            queue.poner(nodo);
            while(!queue.esVacia()){
                nodo= (NodoArbol) queue.obtenerFrente();
                queue.sacar();
                lis.insertar(nodo.getElem(), lis.longitud()+1);
                if(nodo.getIzquierdo()!=null){
                    queue.poner(nodo.getIzquierdo());
                }
                if(nodo.getDerecho()!=null){
                    queue.poner(nodo.getDerecho());
                }
            }
        }

        return lis;
    }
     public ArbolBin clone(){
        ArbolBin clon = new ArbolBin();
        if(!esVacio()){
            clon.raiz = cloneRecursivo(this.raiz);
        }
        return clon;
     }
    private NodoArbol cloneRecursivo(NodoArbol nodoNuevo) {
        NodoArbol nodoAux = null;
        if (nodoNuevo != null) {
            nodoAux = new NodoArbol(nodoNuevo.getElem(), cloneRecursivo(nodoNuevo.getIzquierdo()), cloneRecursivo(nodoNuevo.getDerecho()));

        }
        return nodoAux;
    }
   public String toString(){
        //preorden
        String cadena="";
        if(this.raiz!=null){
            cadena=toStringAux(this.raiz);
        }
        return cadena;
    }
    private String toStringAux(NodoArbol nodo){
        String laCadena="";
        if(nodo!=null){
            laCadena+=" Nodo: "+nodo.getElem();
            if(nodo.getIzquierdo()!=null){
                laCadena+=" HI: "+nodo.getIzquierdo().getElem();
            }else{
                laCadena+=" HI: -";
            }
            laCadena+=" HD: ";
            if(nodo.getDerecho()!=null){
                laCadena+=nodo.getDerecho().getElem();
            }else{
                laCadena+="-";
            }
            laCadena+="\n"+toStringAux(nodo.getIzquierdo())+toStringAux(nodo.getDerecho());
        }
        return laCadena;
    }
    public Lista frontera(){
       // devuelve una lista con la secuencia formada por los elementos
        //almacenados en las hojas del árbol binario, tomadas de izquierda a derecha.
        Lista l1=new Lista();
        if(!esVacio()){
            fronteraRecursivo(this.raiz,l1);
        }
        return l1;
    }
    private void fronteraRecursivo(NodoArbol nodo, Lista l1){
        if(nodo!=null){
            if((nodo.getIzquierdo()==null)&&(nodo.getDerecho()==null)){
                //es hoja
                l1.insertar(nodo.getElem(),l1.longitud()+1);
            }
            fronteraRecursivo(nodo.getIzquierdo(),l1);
            fronteraRecursivo(nodo.getDerecho(),l1);
        }
    }
    public boolean verificarPatron(Lista lisPatron) {
        /**
         * Recibe una lista por parametro y retorna true en caso de que coincida
         * con al menos un camino del arbol, caso contrario retorna false.
         */
        boolean exito;
        if (lisPatron.esVacia() && this.raiz == null) {
            exito = true;
        } else {
            exito = verificarPatronRecursivo(lisPatron, this.raiz, 1);
        }
        return exito;
    }
    private boolean verificarPatronRecursivo(Lista lista, NodoArbol nodo, int pos) {
        boolean controlIzq = false, control = true;

        if (pos <= lista.longitud()) {
            if (nodo != null) {
                if (nodo.getElem().equals(lista.recuperar(pos))) {
                    control = true;
                    controlIzq = verificarPatronRecursivo(lista, nodo.getIzquierdo(), pos + 1);
                } else {
                    control = false;
                }

                if (!controlIzq && control) {
                    control = verificarPatronRecursivo(lista, nodo.getDerecho(), pos + 1);
                }
            } else {
                control = false;
            }
        }
        return control;
    }

    }

