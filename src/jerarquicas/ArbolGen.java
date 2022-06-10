package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;

import java.util.List;

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


    //pertenece (TipoElemento):boolean
    public boolean pertenece(Object b) {
        return (obtenerNodo(this.raiz, b) != null);
    }

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
                    //recorro los hermanos, hasta terminar todos o encontrar el elemento
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
       public int nivel(Object b) {
        int niv;
        niv = buscarNivel(this.raiz, b, -1);
        return niv;
    }

    private int buscarNivel(NodoGen n, Object b, int niv) {
        if (n != null) {
            if (n.getElem().equals(b)) {
                niv++;
            } else {
                niv = buscarNivel(n.getHijoIzquierdo(), b, niv);
                if (niv == -1) {
                    niv = buscarNivel(n.getHermanoDerecho(), b, niv);
                } else {
                    niv++;
                }
            }
        }
        return niv;
    }

    //padre
    public Object padre(Object buscado){
        Object elPadre=null;
        if(!esVacio()){
            if (!this.raiz.getElem().equals(buscado)) {
                elPadre = padreAux(this.raiz, buscado);
            }
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
    private void listarPosordenAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() != null) {
                // Llamado recursivo con HEI
                listarPosordenAux(nodo.getHijoIzquierdo(), lista);

                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();

                while (hijo != null) {
                    // Llamado recursivo con HD. mirando desde el padre
                    listarPosordenAux(hijo, lista);

                    // avanzo sobre los hermanos mirando desde el padre
                    hijo = hijo.getHermanoDerecho();
                }
            }
            // inserto el elemento al final de la lista
            lista.insertar(nodo.getElem(), lista.longitud() + 1);

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
        int i = 1;
        if (this.raiz != null) {
            Cola cola = new Cola();
            NodoGen nodo, hijo;
            cola.poner(this.raiz);
            while (!cola.esVacia()) {
                nodo = (NodoGen) cola.obtenerFrente();
                lista.insertar(nodo.getElem(), i);
                i++;
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
    /*public boolean verificarPatron(Lista lisPatron){
        boolean existe=false;
        if(!this.esVacio()){
            existe=verificarPatronAux(this.raiz,lisPatron); // pasar posicion o eliminar en la lista y solo pregunto por la posicion 1, y si vuelvo lo pongo de nuevo
        }
        return existe;
    }*/

    public Lista frontera(){
        Lista ls = new Lista();
        if(!esVacio()){
            fronteraAux(this.raiz,ls);
        }
        return ls;
    }
    private void fronteraAux(NodoGen nodo, Lista ls){

        if(nodo!=null){
            if(nodo.getHijoIzquierdo()!=null){
                NodoGen sig = nodo.getHijoIzquierdo();
                while(sig!=null){
                    fronteraAux(sig,ls);
                    sig=sig.getHermanoDerecho();
                }
            }else{
                ls.insertar(nodo.getElem(),ls.longitud()+1);
            }
        }
    }
 /* public boolean verificarPatron(Lista lisPatron){
        boolean existe=false;
        if(!this.esVacio()){
            existe=verificarPatronAux(this.raiz,lisPatron); // pasar posicion o eliminar en la lista y solo pregunto por la posicion 1, y si vuelvo lo pongo de nuevo
        }
        return existe;
    }
    //private boolean verificarPatronAux(NodoGen nodo, Lista ls){

    //} */

    public Lista listaQueVerificaLaAltura() {
        Lista listaADevolver = new Lista();
        Lista listaClon = new Lista();
        if (!esVacio()) {
            verificarAux(this.raiz,listaClon,listaADevolver);
        }
        return listaADevolver;
    }
    private void verificarAux(NodoGen nodo,Lista caminoActual,Lista caminoMasLargo) {
        //retorno la lista mas larga
        //Si el camino actual es mas largo que el camino mas largo, lo clono y devuelvo el mas largo
    }

    public boolean sonFrontera(Lista ls) {
        boolean exito;
        if (ls.esVacia() || this.esVacio()) {
            exito = false;
        } else {
            exito = sonFronteraAux(this.raiz, ls);
        }
        return exito;
    }

    private boolean sonFronteraAux(NodoGen nodo, Lista ls) {
        boolean exito = false;
        //while(!ls.esVacia()){
        if (!ls.esVacia()) {
            if (nodo != null) {
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen sig = nodo.getHijoIzquierdo();
                    while (sig != null) {
                        exito = sonFronteraAux(sig, ls);
                        sig = sig.getHermanoDerecho();
                    }
                } else {
                    //es hoja
                    int pos = ls.localizar(nodo.getElem());
                    if (pos != -1) {
                        ls.eliminar(pos);
                    }

                }
            }
        }
        if (ls.esVacia()) {
            exito = true;
        }
        return exito;
    }
    public boolean verificarCamino(Lista lista){
        boolean correcto=false;
        if(!this.esVacio()){
            correcto=verificarCaminoAux(this.raiz,lista);
        }
        return correcto;
    }
    private boolean verificarCaminoAux(NodoGen nodo,Lista ls) {
        boolean correcto=false;
        if(nodo!=null){
            if(nodo.getElem().equals(ls.recuperar(1))){
                //SI el nodo en el que estoy es parte del camino
                ls.eliminar(1);
                if(!ls.esVacia()){
                    correcto=verificarCaminoAux(nodo.getHijoIzquierdo(),ls);
                }
            }else {
                NodoGen hermano = nodo.getHermanoDerecho();
                while(hermano!=null){
                    if(hermano.getElem().equals(ls.recuperar(1))){
                        verificarCaminoAux(hermano,ls);
                    }
                    hermano = hermano.getHermanoDerecho();
                }
            }
            if(ls.esVacia()){
                correcto=true;
            }
        }
        return correcto;
    }
    public Lista listarEntreNiveles(int niv1, int niv2){
        Lista ls = new Lista();
        return listarAux(this.raiz,ls,niv1,niv2,0);
    }
    private Lista listarAux(NodoGen nodo, Lista ls, int niv1, int niv2, int nivActual){
        if(nodo!=null){
            if(nodo.getHijoIzquierdo()!=null && nivActual>=niv1 && nivActual>=niv2){
                listarAux(nodo.getHijoIzquierdo(),ls,niv1,niv2,nivActual+1);
            }
            if(nivActual>=niv1 && nivActual>niv2){
                ls.insertar(nodo.getElem(),ls.longitud()+1);
            }
            if(nodo.getHijoIzquierdo()!=null&&nivActual<=niv2){
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo!=null){
                    listarAux(hijo,ls,niv1,niv2,nivActual+1);
                    hijo=hijo.getHermanoDerecho();
                }
            }
        }
        return ls;
    }

}

