/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.ReturnValue;
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

        var tablaMatch = new tablaSimbolos(tabla, tabla.getNombre() + "_Match");
        tablaSimbolos tabla_res = null;

        if (this.defaul == null) {

            for (var i : this.c) {
                var resultado = i.caso.interpretar(arbol, tablaMatch);

                if (resultado instanceof Errores) {
                    arbol.errores.add((Errores) resultado);
                }

                if (cond == resultado) {
                    var tabla_match_int = new tablaSimbolos(tabla, tabla.getNombre() + "_MatchInst");
                    tabla_res = tabla_match_int;
                    var sol = i.interpretar(arbol, tabla_match_int);
                    if (sol instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }

                    if (sol instanceof Continue) {
                        return sol;
                    }

                    if (sol instanceof Break) {
                        return sol;
                    }

                    if (sol instanceof ReturnValue) {
                        return sol;
                    }
                    break;
                }
            }
        } else if (this.c == null) {
            var tabla_match_int = new tablaSimbolos(tabla, tabla.getNombre() + "_MatchInst");
            tabla_res = tabla_match_int;
            for (var i : this.defaul) {
                if (i instanceof Break) {
                    return i;
                }
                if (i instanceof Continue) {
                    return i;
                }
                var resultado = i.interpretar(arbol, tabla_match_int);

                if (resultado instanceof Errores) {
                    arbol.errores.add((Errores) resultado);
                }

                if (resultado instanceof Break) {
                    return resultado;
                }

                if (resultado instanceof Continue) {
                    return resultado;
                }

                if (resultado instanceof ReturnValue) {
                    return resultado;
                }
            }
        } else {
            boolean caso_existe = false;
            for (var i : this.c) {
                var resultado = i.caso.interpretar(arbol, tablaMatch);

                if (resultado instanceof Errores) {
                    arbol.errores.add((Errores) resultado);
                }

                var prueba = this.condicionE;
                if (cond == resultado) {
                    var tabla_match_int = new tablaSimbolos(tabla, tabla.getNombre() + "_MatchInst");
                    tabla_res = tabla_match_int;
                    var sol = i.interpretar(arbol, tabla_match_int);
                    if (sol instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }

                    if (sol instanceof Continue) {
                        return sol;
                    }

                    if (sol instanceof Break) {
                        return sol;
                    }

                    if (sol instanceof ReturnValue) {
                        return sol;
                    }

                    caso_existe = true;
                    break;
                }
            }

            if (!caso_existe) {
                var tabla_match_int = new tablaSimbolos(tabla, tabla.getNombre() + "_MatchInst");
                tabla_res = tabla_match_int;
                for (var i : this.defaul) {
                    if (i instanceof Break) {
                        return i;
                    }
                    if (i instanceof Continue) {
                        return i;
                    }

                    var resultado = i.interpretar(arbol, tabla_match_int);

                    if (resultado instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }

                    if (resultado instanceof Break) {
                        return resultado;
                    }

                    if (resultado instanceof Continue) {
                        return resultado;
                    }

                    if (resultado instanceof ReturnValue) {
                        return resultado;
                    }
                }
            }

        }

        arbol.addEntornos(tablaMatch);
        arbol.addEntornos(tabla_res);

        return null;
    }
}
