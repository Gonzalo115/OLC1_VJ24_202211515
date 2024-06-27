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
public class For extends Instruccion {

    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;

    public For(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        //creamos un nuevo entorno
        var tabla_For = new tablaSimbolos(tabla, tabla.getNombre() + "_For");
        tablaSimbolos tabla_res = null;

        // asignacion/declaracion
        var res1 = this.asignacion.interpretar(arbol, tabla_For);
        if (res1 instanceof Errores) {
            return res1;
        }

        //validar la condicion -> Booleano
        var cond = this.condicion.interpretar(arbol, tabla_For);
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "La condicion debe ser bool",
                    this.linea, this.col);
        }

        while ((boolean) this.condicion.interpretar(arbol, tabla_For)) {
            //nuevo entorno
            var tabla_for_ins = new tablaSimbolos(tabla_For, tabla.getNombre() + "_ForInst");

            //ejecutar instrucciones
            for (var i : this.instrucciones) {

                if (i instanceof Break) {
                    return null;
                }
                if (i instanceof Continue) {
                    break;
                    //return null;
                }
                var resIns = i.interpretar(arbol, tabla_for_ins);

                if (resIns instanceof Errores) {
                    arbol.errores.add((Errores) resIns);
                }

                if (resIns instanceof Break) {
                    return null;
                }
                if (resIns instanceof Continue) {
                    break;
                    //return null;
                }
            }

            var act = this.actualizacion.interpretar(arbol, tabla_For);
            if (act instanceof Errores) {
                return act;
            }
            if (!(boolean) this.condicion.interpretar(arbol, tabla_For)) {
                tabla_res = tabla_for_ins;
            }
        }

        //arbol.addEntornos(tabla_For);
        arbol.addEntornos(tabla_res);
        return null;
    }
}
