/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.Condicion;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class Casos extends Instruccion {
    public Condicion caso; 
    public LinkedList<Instruccion> ejecucion;
    
    public Casos(Condicion caso, LinkedList<Instruccion> ejecucion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.caso = caso;
        this.ejecucion = ejecucion;
    }
    
    

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

            for (var i : this.ejecucion) {
                if (i instanceof Break) {
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

        return null;
    }



}
