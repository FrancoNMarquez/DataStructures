package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen(){
        this.raiz=null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;

        if (this.raiz == null) {
            //Si el arbol esta vacio ponemos el elemento nuevo en la raiz
            this.raiz = new NodoGen(elemNuevo,null,null);
        } else {
            //Debemos buscar si existe el padre
            NodoGen padre = obtenerNodo(this.raiz, elemPadre);
            if (padre != null) {
                //Existe el padre
                NodoGen nodoNuevo = new NodoGen(elemNuevo,null,null); //PREGUNTAR ACA QUIEN ES EL HIJO Y EL PADRE
                NodoGen nodoIzquierdo = padre.getHijoIzquierdo();
                //Si el padre no tiene hijo izq , inserto el nuevo como hijo izquierdo
                if (nodoIzquierdo == null) {
                    padre.setHijoIzquierdo(nodoNuevo);
                } else {
                    //Si tiene hijo izquierdo, debo insertarlo al final de sus hermanos derechos
                    while (nodoIzquierdo.getHermanoDerecho() != null) {
                        nodoIzquierdo = nodoIzquierdo.getHermanoDerecho();
                    }
                    nodoIzquierdo.setHermanoDerecho(nodoNuevo);
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }
    //Metodo que me devuelve el nodo del elemento buscado que envio por parametro
    private NodoGen obtenerNodo(NodoGen nodo, Object elemento) {
        NodoGen res = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                res = nodo;
            } else {
                res = obtenerNodo(nodo.getHijoIzquierdo(), elemento);
                if (res == null) {
                    res = obtenerNodo(nodo.getHermanoDerecho(), elemento);
                }
            }
        }
        return res;
    }
    //pertenece
    public boolean pertenece(Object buscado){
        boolean existe=false;
        if((this.raiz!=null)&&(buscado!=null)){
            existe=perteneceAux(this.raiz,buscado);
        }

        return existe;
    }
    private boolean perteneceAux(NodoGen nodo, Object buscado){
        boolean pertenece=false;

        if(nodo!=null){
            if(nodo.getElem().equals(buscado)){
                //si es el elem buscado
                pertenece = true;
            }else{
                //pregunto si tiene hijos
                if(nodo.getHijoIzquierdo()!=null){
                  pertenece=perteneceAux(nodo.getHijoIzquierdo(),buscado);
                }else{
                    //si no tiene hijos, pregunto si tiene hermanos
                    if(nodo.getHermanoDerecho()!=null){
                        nodo=nodo.getHermanoDerecho();
                        while ((nodo!=null)||(!pertenece)){
                            pertenece=perteneceAux(nodo.getHermanoDerecho(),buscado);
                            nodo=nodo.getHermanoDerecho();
                        }
                    }
                }

            }
        }

        return  pertenece;
    }
    /*  version 2021
    private boolean perteneceAux(NodoGen nodoActual, Object buscado){

        boolean existe=false;
        if(nodoActual.getElem().equals(buscado)){
            existe=true;
        }else{
            for(NodoGen hijo = nodoActual.getHijoIzquierdo(); !existe && hijo != null; hijo = hijo.getHermanoDerecho()){
            existe=this.perteneceAux(hijo, buscado);
        }
        }
        return existe;
    }*/

    //ancestros
    public Lista ancestros(Object buscado){
        Lista ls = new Lista();
        if(this.raiz!=null){
            ancestrosAux(this.raiz,ls,buscado);
        }
        return ls;
    }
    private boolean ancestrosAux(NodoGen nodo, Lista lista, Object elemento) {
        boolean encontrado=false;

        if(nodo!=null){
            if(nodo.getElem().equals(elemento)){
                encontrado=true;
            }else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !encontrado) {
                    //recorro los hermanos , hasta terminar todos o encontrar el elemento
                    encontrado = ancestrosAux(hijo, lista, elemento);
                    hijo = hijo.getHermanoDerecho();
                }
                if (encontrado) {
                    lista.insertar(nodo.getElem(), 1);
                }
            }
        }
        return encontrado;
    }
    public boolean esVacio(){
        return this.raiz==null;
    }
    //altura
    public int altura(){
        // Devuelve la altura del árbol, es decir la longitud del camino más largo desde la raíz hasta una hoja
        int laAltura=-1;
        if(!this.esVacio()){
            laAltura=alturaAux(this.raiz,0);
        }
        return laAltura;
    }
    private int alturaAux(NodoGen nodo,int alturaHijo){
        int alturaMax =-1;
        int alturaHermano=0;
        if(nodo!=null){
            if(nodo.getHijoIzquierdo()==null){
                //es hoja
                alturaHijo=0;
            }else{
                //si no es hoja
                alturaHijo= alturaAux(nodo.getHijoIzquierdo(),alturaHijo+1)+1;
            }
           NodoGen hermano=nodo.getHermanoDerecho();
            //creo un nodo para recorrer a los hermanos
            while (hermano != null){
                alturaHermano= alturaAux(hermano,alturaHijo);
                hermano=hermano.getHermanoDerecho();
            }

            if(alturaHermano>alturaHijo){ //si el hermano tiene mayor altura, la actualizo
                alturaHijo = alturaHermano;
            }
            if(alturaHijo>alturaMax){
                alturaMax=alturaHijo;
            }
            //math.max
           // alturaMax=(alturaActual>alturaMax) ? alturaActual : alturaMax+1 ;

        }
      return alturaMax;
    }
   /* version 2021
    public int altura() {
        return alturaAux(this.raiz, -1);
    }

    private int alturaAux(NodoGen nodo, int altura) {
        if (nodo != null) {
            int n = alturaAux(nodo.getHijoIzquierdo(), altura + 1);
            int m = alturaAux(nodo.getHermanoDerecho(), altura);
            altura = (n > m) ? n : m;
        }
        return altura;
    }*/

    //nivel
    public int nivel(Object buscado){
        // Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en el árbol devuelve -1
        int elNivel=-1;
        if(!esVacio()){
            elNivel=nivelAux(this.raiz,buscado,0,false);
        }
        return  elNivel;
    }
    private int nivelAux(NodoGen nodo, Object buscado, int nivel,boolean encontrado){
        int nivelMax = -1;
        int nivelHijo=0;
        int nivelHermano=0;
        if(nodo!=null){
            if(nodo.getElem().equals(buscado)){
                encontrado = true;
                nivelMax=0;
            }else{
                if(nodo.getHijoIzquierdo()!=null){
                    nivelHijo= nivelAux(nodo.getHijoIzquierdo(),buscado,nivel+1,encontrado)+1;
                }
            }
            NodoGen hermano = nodo.getHermanoDerecho();
            while(!encontrado && hermano!=null){
                nivelHermano= nivelAux(hermano,buscado,nivel,encontrado);
                hermano=hermano.getHermanoDerecho();
            }
        }
        nivelMax = Math.max(nivelHermano,nivelHijo);

        return nivelMax;
    }
    //padre
    public Object padre(Object buscado){
        Object elPadre=null;
        if(!esVacio()){
            elPadre=padreAux(this.raiz,buscado);
        }
        return elPadre;
    }
    private Object padreAux(NodoGen nodoActual, Object elemento) {
        Object res = null;
        if (nodoActual != null) {
            NodoGen sig = nodoActual.getHijoIzquierdo();
            while (sig != null && !sig.getElem().equals(elemento)) {
                sig = sig.getHermanoDerecho();
            }
            if (sig != null) {
                res = nodoActual.getElem();
            } else {
                res = padreAux(nodoActual.getHijoIzquierdo(), elemento);
                if (res == null) {
                    res = padreAux(nodoActual.getHermanoDerecho(), elemento);
                }
            }
        }
        return res;
    }
    //listar
    //clone
    public void vaciar(){
        this.raiz=null;
    }
    public Lista listarInorden(){
        Lista salida = new Lista();
        listarInordenAux(this.raiz,salida);
        return salida;
    }
    private void listarInordenAux(NodoGen n, Lista ls){
        if(n!=null){
            //llamado recursivo con primer hijo de n
            if(n.getHijoIzquierdo() != null){
                listarInordenAux(n.getHijoIzquierdo(),ls);

            }
            //visita del nodo n
            ls.insertar(n.getElem(), ls.longitud()+1);

            //llamados recursivos con los otros hijos de n
            if(n.getHijoIzquierdo() != null){
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo!=null){
                    listarInordenAux(hijo,ls);
                    hijo=hijo.getHermanoDerecho();
                }
            }
        }
    }
    public Lista listarPosorden(){
        Lista salida = new Lista();
        listarPosordenAux(this.raiz,salida);
        return salida;
    }
    private void listarPosordenAux(NodoGen n, Lista ls) {
        if (n != null) {
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }

            }
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), ls);

            }
            //visita del nodo n
            ls.insertar(n.getElem(), ls.longitud() + 1);

        }
    }
    public Lista listarPreorden() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarPreOrdenAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarPreOrdenAux(NodoGen nodo, Lista lista) {

        if (nodo != null) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            NodoGen hijo = nodo.getHijoIzquierdo();

            while (hijo != null) {
                listarPreOrdenAux(hijo, lista);

                hijo = hijo.getHermanoDerecho();
            }

        }
    }
    public Lista listarPorNiveles() {
        Lista lista = new Lista();
        if (this.raiz != null) {
            Cola cola = new Cola();
            NodoGen nodo, hijo;
            cola.poner(this.raiz);
            while (!cola.esVacia()) {
                nodo = (NodoGen) cola.obtenerFrente();
                lista.insertar(nodo.getElem(), 1);
                cola.sacar();
                hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    cola.poner(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
        return lista;
    }
    public String toString(){
        return toStringAux(this.raiz);
    }
    private String toStringAux(NodoGen n){
        String s="";
        if(n!=null){
            //visita del nodo n
            s += n.getElem().toString()+ " -->";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo!=null){
                s+= hijo.getElem().toString() + " ";
                hijo=hijo.getHermanoDerecho();
            }
            //comienza recorrido de los hijos de n llamando recursivamente
            //para que cada hijo agregue su subcadena a la general

            hijo = n.getHijoIzquierdo();
            while (hijo!=null){
                s+= "\n" + toStringAux(hijo) + " ";
                hijo= hijo.getHermanoDerecho();
            }
        }
        return s;
    }
    public ArbolGen clone(){
        ArbolGen clon = new ArbolGen();
        if(!esVacio()){
            clon.raiz=cloneAux(this.raiz);
        }
        return clon;
    }
    private NodoGen cloneAux(NodoGen nodo) {
        NodoGen nuevo = new NodoGen(nodo.getElem(),null,null);
        if (nodo.getHijoIzquierdo() != null) {
            nuevo.setHijoIzquierdo(cloneAux(nodo.getHijoIzquierdo()));
        }
        if (nodo.getHermanoDerecho() != null) {
            nuevo.setHermanoDerecho(cloneAux(nodo.getHermanoDerecho()));
        }
        return nuevo;
    }
}
