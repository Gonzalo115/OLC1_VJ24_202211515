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
public class DoWhile extends Instruccion {

    private LinkedList<Instruccion> instrucciones;
    private Instruccion condicion;

    public DoWhile(LinkedList<Instruccion> instrucciones, Instruccion condicion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.instrucciones = instrucciones;
        this.condicion = condicion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        //Entorno Do While
        var tabla_DoWhile = new tablaSimbolos(tabla, tabla.getNombre() + "_DoWhile");
        tablaSimbolos tabla_res = null;

        //validar la condicion -> Booleano
        var cond = this.condicion.interpretar(arbol, tabla_DoWhile);
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "La condicion debe ser bool",
                    this.linea, this.col);
        }

        do {
            //nuevo entorno
            var tabla_DoWhile_ins = new tablaSimbolos(tabla_DoWhile, tabla.getNombre() + "_DoWhileInst");

            //ejecutar instrucciones
            for (var i : this.instrucciones) {
                if (i instanceof Break) {
                    return null;
                }
                if (i instanceof Continue) {
                    break;
                    //return null;
                }
                var resIns = i.interpretar(arbol, tabla_DoWhile_ins);
                if (resIns instanceof Break) {
                    return null;
                }
                if (resIns instanceof Continue) {
                    break;
                    //return null;
                }
            }

            if (!(boolean) this.condicion.interpretar(arbol, tabla_DoWhile)) {
                tabla_res = tabla_DoWhile_ins;
            }

        } while ((boolean) this.condicion.interpretar(arbol, tabla_DoWhile));

        arbol.addEntornos(tabla_DoWhile);
        arbol.addEntornos(tabla_res);
        return null;
    }
}
