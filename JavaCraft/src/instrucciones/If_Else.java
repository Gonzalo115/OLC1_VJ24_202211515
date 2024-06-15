/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class If_Else extends Instruccion {

    private Instruccion condicion; //Condicion del bloque if
    private LinkedList<Instruccion> instruccionesT;//Si la condicion es verdadera
    private LinkedList<Instruccion> instruccionesF;//Si la condicion es falsa

    public If_Else(Instruccion condicion, LinkedList<Instruccion> instruccionesT, LinkedList<Instruccion> instruccionesF, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instruccionesT = instruccionesT;
        this.instruccionesF = instruccionesF;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        // ver que cond sea booleano
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "Expresion invalida Debe de ser un Boolean la expresion del If.",
                    this.linea, this.col);
        }

        var newTabla = new tablaSimbolos(tabla);
        if (toBoolean(cond)) {
            for (var i : this.instruccionesT) {
                if (i instanceof Break) {
                    return i;
                }
                var resultado = i.interpretar(arbol, newTabla);

                if (resultado instanceof Errores) {
                    arbol.errores.add((Errores) resultado);
                }

                if (resultado instanceof Break) {
                    return resultado;
                }
            }
        } else {
            for (var i : this.instruccionesF) {
                if (i instanceof Break) {
                    return i;
                }
                var resultado = i.interpretar(arbol, newTabla);

                if (resultado instanceof Errores) {
                    arbol.errores.add((Errores) resultado);
                }

                if (resultado instanceof Break) {
                    return resultado;
                }
            }

        }
        return null;
    }

    public static boolean toBoolean(Object obj) {
        if (obj instanceof String) {
            return Boolean.valueOf(((String) obj).toLowerCase());
        } else {
            return (boolean) obj;
        }
    }
}
