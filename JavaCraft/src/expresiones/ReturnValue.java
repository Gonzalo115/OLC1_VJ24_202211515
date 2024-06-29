/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class ReturnValue extends Instruccion {

    public Object expresiones;

    public ReturnValue(Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
    }

    public ReturnValue(Object expresiones, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.expresiones = expresiones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return expresiones;
    }
}