/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import instrucciones.Break;
import instrucciones.Break;
import instrucciones.Continue;
import instrucciones.Continue;
import instrucciones.Else_;
import instrucciones.Else_;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class If extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instruccionesT;
    private LinkedList<Else_> If_anidados;
    private LinkedList<Instruccion> instruccionesF;//Si la condicion es falsa

    public If(Instruccion condicion, LinkedList<Instruccion> instruccionesT, LinkedList<Else_> If_anidados, LinkedList<Instruccion> instruccionesF, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instruccionesT = instruccionesT;
        this.If_anidados = If_anidados;
        this.instruccionesF = instruccionesF;
    }

    public If(Instruccion condicion, LinkedList<Instruccion> instruccionesT, LinkedList<Else_> If_anidados, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instruccionesT = instruccionesT;
        this.If_anidados = If_anidados;
    }

    public If(LinkedList<Instruccion> instruccionesT, Instruccion condicion, LinkedList<Instruccion> instruccionesF, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instruccionesT = instruccionesT;
        this.instruccionesF = instruccionesF;
    }

    public If(Instruccion condicion, LinkedList<Instruccion> instruccionesT, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instruccionesT = instruccionesT;
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

        if (this.If_anidados == null && this.instruccionesF == null) { // if(----){------}
            if ((boolean) cond) {
                for (var i : this.instruccionesT) {
                    if (i instanceof Break) {
                        return i;
                    }
                    if (i instanceof Continue) {
                        return i;
                    }
                    var resultado = i.interpretar(arbol, tabla);

                    if (resultado instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }

                    if (resultado instanceof Break) {
                        return resultado;
                    }
                }
            }

        } else if (this.If_anidados == null) { //if(----){----}else{----}
            if ((boolean) cond) {
                for (var i : this.instruccionesT) {
                    if (i instanceof Break) {
                        return i;
                    }
                    if (i instanceof Continue) {
                        return i;
                    }
                    var resultado = i.interpretar(arbol, tabla);

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
                    if (i instanceof Continue) {
                        return i;
                    }
                    var resultado = i.interpretar(arbol, tabla);

                    if (resultado instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }

                    if (resultado instanceof Break) {
                        return resultado;
                    }
                }
            }
        } else if (this.instruccionesF == null) { // if(----){----}else if(----){----}      
            if ((boolean) cond) {
                for (var i : this.instruccionesT) {
                    if (i instanceof Break) {
                        return i;
                    }
                    if (i instanceof Continue) {
                        return i;
                    }
                    var resultado = i.interpretar(arbol, tabla);

                    if (resultado instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }

                    if (resultado instanceof Break) {
                        return resultado;
                    }
                }
            } else {
                for (var i : this.If_anidados) {
                    var condF = i.condicion.interpretar(arbol, tabla);

                    if (condF instanceof Errores) {
                        arbol.errores.add((Errores) condF);
                    }

                    if ((boolean) condF) {
                        var sol = i.interpretar(arbol, tabla);
                        if (sol instanceof Errores) {
                            arbol.errores.add((Errores) condF);
                        }
                        if (sol instanceof Break) {
                            return sol;
                        }
                        if (sol instanceof Continue) {
                            return sol;
                        }
                        break;
                    }
                }
            }
        } else { //if(----){----}else if(----){----}else{----}
            boolean encontrado = false;
            if ((boolean) cond) {
                for (var i : this.instruccionesT) {
                    if (i instanceof Break) {
                        return i;
                    }
                    if (i instanceof Continue) {
                        return i;
                    }
                    var resultado = i.interpretar(arbol, tabla);

                    if (resultado instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }

                    if (resultado instanceof Break) {
                        return resultado;
                    }
                }
            } else {
                for (var i : this.If_anidados) {
                    var condF = i.condicion.interpretar(arbol, tabla);

                    if (condF instanceof Errores) {
                        arbol.errores.add((Errores) condF);
                    }

                    if ((boolean) condF) {
                        var sol = i.interpretar(arbol, tabla);
                        if (sol instanceof Errores) {
                            arbol.errores.add((Errores) condF);
                        }
                        if (sol instanceof Break) {
                            return sol;
                        }
                        if (sol instanceof Continue) {
                            return sol;
                        }
                        encontrado = true;
                        break;
                    }
                }
            }
            if (!encontrado) {
                for (var i : this.instruccionesF) {
                    if (i instanceof Break) {
                        return i;
                    }
                    if (i instanceof Continue) {
                        return i;
                    }
                    var resultado = i.interpretar(arbol, tabla);

                    if (resultado instanceof Errores) {
                        arbol.errores.add((Errores) resultado);
                    }

                    if (resultado instanceof Break) {
                        return resultado;
                    }
                }
            }
        }

        return null;
    }
}
