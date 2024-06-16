/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class Condicion extends Instruccion {

    public Instruccion condicion;

    public Condicion(Instruccion condicion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condicionR = this.condicion.interpretar(arbol, tabla);
        if (condicionR instanceof Errores) {
            return condicionR;
        }

        // ver que no se VOID
        if (this.condicion.tipo.getTipo() == tipoDato.VOID) {
            return new Errores("SEMANTICO", "Expresion invalida Debe debe de tener un Tipo osea debe ser un resultado.",
                    this.linea, this.col);
        }

        return condicionR;

    }

}
