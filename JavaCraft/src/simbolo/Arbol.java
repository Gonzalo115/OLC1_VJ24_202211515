/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import abstracto.Instruccion;
import excepciones.Errores;
import instrucciones.Metodo;
import java.util.LinkedList;

/**
 *
 * @author 5gonz
 */
public class Arbol {

    private LinkedList<Instruccion> instrucciones;
    private String consola;
    public tablaSimbolos tablaGlobal;
    public LinkedList<Errores> errores;
    public LinkedList<tablaSimbolos> entornos = new LinkedList<>();
    public LinkedList<Instruccion> funciones;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.consola = "";
        this.tablaGlobal = new tablaSimbolos();
        this.errores = new LinkedList<>();
        this.funciones = new LinkedList<>();
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public tablaSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public void setTablaGlobal(tablaSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }

    public LinkedList<tablaSimbolos> getEntornos() {
        return entornos;
    }

    public void setEntornos(LinkedList<tablaSimbolos> entornos) {
        this.entornos = entornos;
    }

    public void addEntornos(tablaSimbolos tablaEntorno) {
        this.entornos.add(tablaEntorno);
    }

    public LinkedList<Instruccion> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<Instruccion> funciones) {
        this.funciones = funciones;
    }

    public void addFunciones(Instruccion funcion) {
        this.funciones.add(funcion);
    }

    public Instruccion getFuncion(String id) {
        for (var i : this.funciones) {
            if (i instanceof Metodo metodo) {
                if (metodo.id.equalsIgnoreCase(id)) {
                    return i;
                }
            }
        }
        return null;
    }

    public void Print(String valor) {
        this.consola += valor + "\n";
    }
}
