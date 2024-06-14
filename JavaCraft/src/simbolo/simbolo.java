/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

/**
 *
 * @author 5gonz
 */
public class simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;
    public boolean mutable;

    public simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }

    public simbolo(Tipo tipo, String id, Object valor) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }

    public simbolo(Tipo tipo, String id, boolean mutable, Object valor) {
        this.tipo = tipo;
        this.id = id;
        this.mutable = mutable;
        this.valor = valor;
    }
    
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public boolean getMutable() {
        return mutable;
    }

    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }
    
   
}
