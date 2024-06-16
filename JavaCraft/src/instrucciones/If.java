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
public class If extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;
    private LinkedList<If> If_anidados;
    private LinkedList<Instruccion> instruccionesF;//Si la condicion es falsa

    public boolean si_cumplio = false;

    public If(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    public If(LinkedList<Instruccion> instrucciones, LinkedList<If> If_anidados, Instruccion condicion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.instrucciones = instrucciones;
        this.If_anidados = If_anidados;
        this.condicion = condicion;
    }

    public If(Instruccion condicion, LinkedList<Instruccion> instrucciones, LinkedList<Instruccion> instruccionesF, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
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

        if (this.instruccionesF == null && this.If_anidados == null) {
            var newTabla = new tablaSimbolos(tabla);
            if (toBoolean(cond)) {
                si_cumplio = true;
                for (var i : this.instrucciones) {
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
                    if (resultado instanceof Continue) {
                        return resultado;
                    }
                }
                return null;

            }
        } else if (this.instruccionesF == null) {
            var newTabla = new tablaSimbolos(tabla);
            if (toBoolean(cond)) {
                si_cumplio = true;
                for (var i : this.instrucciones) {
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
                    if (resultado instanceof Continue) {
                        return resultado;
                    }
                }
                return null;
            } else {
                for (var i : this.If_anidados) {
                    var resultado = i.interpretar(arbol, newTabla);

                    if (resultado instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }

                    if (i.si_cumplio) {
                        return null;
                    }
                }
            }
        } else {
            var newTabla = new tablaSimbolos(tabla);
            if (toBoolean(cond)) {
                si_cumplio = true;
                for (var i : this.instrucciones) {
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
                    if (resultado instanceof Continue) {
                        return resultado;
                    }
                }
                return null;
            } else {
                si_cumplio = true;
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
                    if (resultado instanceof Continue) {
                        return resultado;
                    }
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
