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
public class While extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public While(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        var tabla_While = new tablaSimbolos(tabla, tabla.getNombre() + "_While");
        tablaSimbolos tabla_res = null;

        var cond = this.condicion.interpretar(arbol, tabla_While);
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "La condicion debe ser bool",
                    this.linea, this.col);
        }

        while ((boolean) this.condicion.interpretar(arbol, tabla_While)) {

            var tabla_While_ins = new tablaSimbolos(tabla_While, tabla_While.getNombre() + "_WhileInst");

            for (var i : this.instrucciones) {
                if (i instanceof Break) {
                    return null;
                }
                if (i instanceof Continue) {
                    break;
                    //return null;
                }
                var resIns = i.interpretar(arbol, tabla_While_ins);
                if (resIns instanceof Break) {
                    return null;
                }
                if (resIns instanceof Continue) {
                    break;
                    //return null;
                }
            }

            if (!(boolean) this.condicion.interpretar(arbol, tabla_While_ins)) {
                tabla_res = tabla_While_ins;
            }
        }

        arbol.addEntornos(tabla_While);
        arbol.addEntornos(tabla_res);
        return null;
    }
}
