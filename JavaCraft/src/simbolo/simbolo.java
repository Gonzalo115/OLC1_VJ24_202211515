/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import java.util.LinkedList;

/**
 *
 * @author 5gonz
 */
public class simbolo {

    private Tipo tipo;              //Tipo de Dato -> Entero, Decimal, Boleano, String, Character, Vector, Matrix
    public Tipo tipoD;              //En caso que es un vector indicar que tipo de datos tiene.
    public boolean mutable;         //Mutabilidad
    private String id;              //Identificador
    private Object valor;           //Valor para las varibales
    private int linea;              //Linea
    private int columna;            //Columna

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

    public simbolo(Tipo tipo, String id, boolean mutable, Object valor, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.mutable = mutable;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    public simbolo(Tipo tipo, Tipo tipoD, String id, boolean mutable, Object valor, int linea, int columna) {
        this.tipo = tipo;
        this.tipoD = tipoD;
        this.id = id;
        this.mutable = mutable;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipoD() {
        return tipoD;
    }

    public void setTipoD(Tipo tipoD) {
        this.tipoD = tipoD;
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

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

}
