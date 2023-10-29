package conjuntistas.dinamicas;

public class NodoAVL {
    private Comparable clave;
   private Object elem;
   private int altura;
   private NodoAVL izquierdo;
   private NodoAVL derecho;

    public NodoAVL(Comparable clave, Object elem) {
        this.clave = clave;
        this.elem = elem;
        this.izquierdo=null;
        this.derecho=null;
        this.altura=0;
    }

    public NodoAVL(Comparable clave, Object elem, NodoAVL izquierdo, NodoAVL derecho) {
        this.clave = clave;
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.altura=0;
    }

    public Comparable getClave() {
        return clave;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura(){
        int alturaDerecho = (this.derecho == null) ? -1 : this.derecho.getAltura();
        int alturaIzquierdo = (this.izquierdo == null) ? -1 : this.izquierdo.getAltura();
        this.altura = Math.max(alturaIzquierdo, alturaDerecho) + 1;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }
}
