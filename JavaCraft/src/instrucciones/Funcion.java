/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import expresiones.ReturnValue;
import java.util.HashMap;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class Funcion extends Instruccion {

    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;

    public Funcion(String id, LinkedList<Instruccion> instrucciones, LinkedList<HashMap> parametros, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.id = id;
        this.instrucciones = instrucciones;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        for (var i : this.instrucciones) {

            var resultado = i.interpretar(arbol, tabla);

            if (resultado instanceof Errores) {
                arbol.errores.add((Errores) resultado);
            }

            if (resultado instanceof ReturnValue returnValor) {
                if(returnValor.expresiones == null){
                    return resultado;
                }
                
                if (this.tipo.getTipo() == returnValor.tipo.getTipo()) {
                    return resultado;
                } else {
                    return new Errores("SEMANTICO", "El tipo de valor que se intenta devolver y el tipo de la funcion no coincide.", this.linea, this.col);
                }
            }

            if (resultado instanceof Continue || resultado instanceof Break) {
                    return new Errores("SEMANTICO", "Mal manejor de Break o Continue.", this.linea, this.col);

            }
        }
        arbol.addEntornos(tabla);
        return null;
    }
}
