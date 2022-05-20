package conjuntistas.dinamicas;

import jerarquicas.NodoArbol;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;

public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB(){
        this.raiz=null;
    }
    //pertenece
    public  boolean pertenece(Comparable elem){
        boolean exito=false;
        if(this.raiz!=null){
            exito=perteneceAux(this.raiz,elem);
        }
        return exito;
    }
    private boolean perteneceAux(NodoABB nodo, Comparable elem){
        boolean exito=false;
        if(nodo!=null){
            Comparable aComparar = nodo.getElem();

            if(aComparar.compareTo(elem)==0){
                exito=true;
            }else if (aComparar.compareTo(elem)<0){
                exito=perteneceAux(nodo.getIzquierdo(),elem);
            }else{
                exito=perteneceAux(nodo.getDerecho(),elem);
            }}
        return exito;
    }
    //insertar
    public boolean insertar(Comparable elemento){
        boolean exito=true;
        if(this.raiz==null){
            this.raiz= new NodoABB(elemento,null,null);
        }else{
            exito=insertarAux(this.raiz,elemento);
        }
        return exito;
    }
    private boolean insertarAux(NodoABB n, Comparable elemento){
        //precondicion: n no es nulo
        Comparable comparar = n.getElem();
        boolean exito=true;
        if((elemento.compareTo(comparar)==0)){
            exito=false;
        }else if(elemento.compareTo(comparar)<0){
            if(n.getIzquierdo()!=null){
                exito=insertarAux(n.getIzquierdo(),elemento);
            }else{
                n.setIzquierdo(new NodoABB(elemento,null,null));
            }
        }else{
            if(n.getDerecho()!=null){
                exito=insertarAux(n.getDerecho(),elemento);
            }else{
                n.setDerecho(new NodoABB(elemento,null,null));
            }
        }
        return exito;
    }
    //eliminar
    public boolean eliminar(Comparable elemento){
        boolean exito=false;
        if(this.raiz!=null){
            exito=eliminarAux(this.raiz,elemento);
        }
        return exito;
    }
    private boolean eliminarAux(NodoABB nodo, Comparable elem){
        boolean exito = false;
        if(nodo!=null){
            if(nodo.getElem().compareTo(elem)<0){
                exito=eliminarAux(nodo.getIzquierdo(),elem);
            }else if(nodo.getElem().compareTo(elem)>0){
                exito=eliminarAux(nodo.getDerecho(),elem);
            }else{
                //encontre el elemento a eliminar
                if(nodo.getDerecho()==null&&nodo.getIzquierdo()==null){
                    eliminarCaso1();
                }else if((nodo.getDerecho()==null&&nodo.getIzquierdo()!=null)||(nodo.getDerecho()!=null&&nodo.getIzquierdo()==null)){
                    //tiene 1 solo hijo
                    eliminarCaso2();
                }else{
                    eliminarCaso3();
                }

            }
        }
        return exito;
    }
    private void eliminarCaso1(){
    //eliminar una hoja
    }
    private void eliminarCaso2(){
    //eliminar nodo con 1 solo hijo
    }
    private void eliminarCaso3(){
    //eliminar nodo con 2 hijos
    }
    //listar //usar inorden
    public Lista listar(){
        Lista ls = new Lista();
        if(!this.esVacio()){
            listarAux(this.raiz,ls);
        }
        return ls;
    }
    private void  listarAux(NodoABB nodo, Lista lis){
        if(nodo!=null){
            NodoABB izq = nodo.getIzquierdo();
            NodoABB der = nodo.getDerecho();
            listarAux(izq,lis);

            lis.insertar(nodo.getElem(),lis.longitud()+1);

            listarAux(der,lis);

        }
    }
    //listarRango
    public Lista listarRango(Comparable min , Comparable max){
        Lista ls = new Lista();
        if(!this.esVacio()){
            listarRangoAux(this.raiz,ls);
        }
        return ls;
    }
    private void listarRangoAux(NodoABB nodo, Lista ls){

    }
    public Comparable minimoElem(){
        Comparable minimo;
        return minimo=minimoAux(this.raiz);
    }
    private Comparable minimoAux(NodoABB nodo){
        Comparable minimo=null;
        if(nodo!=null){
            if(nodo.getIzquierdo()==null){
                //Si no tiene HI
                minimo=nodo.getElem();
            }else{
                minimo=minimoAux(nodo.getIzquierdo());
            }
        }
        return minimo;
    }
    public Comparable maximoElem(){
        Comparable maximo;
        return maximo=maximoAux(this.raiz);
    }
    private Comparable maximoAux(NodoABB nodo){
        Comparable maximo=null;
        if(nodo!=null){
            if(nodo.getDerecho()==null){
                //Si no tiene HD
                maximo=nodo.getElem();
            }else{
                maximo=maximoAux(nodo.getDerecho());
            }
        }
        return maximo;
    }

    public boolean esVacio(){
        return raiz==null;
    }

    public String toString(){
        //preorden
        String cadena="";
        if(this.raiz!=null){
            cadena=toStringAux(this.raiz);
        }
        return cadena;
    }
    private String toStringAux(NodoABB nodo){
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
}
//
