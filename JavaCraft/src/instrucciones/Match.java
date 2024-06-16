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
public class Match extends Instruccion {

    private Instruccion condicionE;
    private LinkedList<Casos> c;
    private LinkedList<Instruccion> defaul;

    public Match(Instruccion condicionE, LinkedList<Casos> c, LinkedList<Instruccion> defaul, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicionE = condicionE;
        this.c = c;
        this.defaul = defaul;
    }

    public Match(Instruccion condicionE, LinkedList<Casos> c, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicionE = condicionE;
        this.c = c;
    }

    public Match(LinkedList<Instruccion> defaul, Instruccion condicionE, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicionE = condicionE;
        this.defaul = defaul;
    }

    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicionE.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.condicionE.tipo.getTipo() == tipoDato.VOID) {
            return new Errores("SEMANTICO", "Expresion invalida Debe debe de tener un Tipo osea debe ser un resultado.",
                    this.linea, this.col);
        }

        if (this.defaul == null) {
            var newTabla = new tablaSimbolos(tabla);

            for (var i : this.c) {
                var resultado = i.caso.interpretar(arbol, newTabla);

                if (resultado instanceof Errores) {
                    arbol.errores.add((Errores) resultado);
                }

                var prueba = this.condicionE;
                if (cond == resultado) {
                    var sol = i.interpretar(arbol, newTabla);
                    if (sol instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }
                    break;
                }
            }
        } else if (this.c == null) {
            var newTabla = new tablaSimbolos(tabla);
            for (var i : this.defaul) {
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
