/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import abstracto.Instruccion;
import excepciones.Errores;
import instrucciones.Atributos;
import instrucciones.Funcion;
import instrucciones.Metodo;
import instrucciones.Struct;
import java.util.HashMap;
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
    public HashMap<String, Struct> Structs;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.consola = "";
        this.tablaGlobal = new tablaSimbolos();
        this.errores = new LinkedList<>();
        this.funciones = new LinkedList<>();
        this.Structs = new HashMap<>();
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
            } else if (i instanceof Funcion funcion) {
                if (funcion.id.endsWith(id)) {
                    return i;
                }
            }
        }
        return null;
    }

    public boolean setStruct(Struct Struct) {
        Struct busqueda = (Struct) this.Structs.get(Struct.id.toLowerCase());
        if (busqueda == null) {
            this.Structs.put(Struct.id.toLowerCase(), Struct);
            return true;
        }
        return false;
    }

    public Struct getStruct(String id) {
        Struct busqueda = (Struct) this.Structs.get(id.toLowerCase());
        if (busqueda != null) {
            return busqueda;
        }
        return null;
    }

    public void Print(String valor) {
        this.consola += valor + "\n";
    }
}
