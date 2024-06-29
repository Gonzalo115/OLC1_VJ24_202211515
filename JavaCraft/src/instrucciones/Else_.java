/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.Condicion;
import expresiones.ReturnValue;

import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class Else_ extends Instruccion {

    public Condicion condicion;
    public LinkedList<Instruccion> instrucciones;

    public Else_(Condicion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        for (var i : this.instrucciones) {
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
            if (resultado instanceof Continue) {
                return resultado;
            }

            if (resultado instanceof ReturnValue) {
                return resultado;
            }
        }

        return null;
    }

}
