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
public class Metodo extends Instruccion {

    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;

    public Metodo(String id, LinkedList<Instruccion> instrucciones, LinkedList<HashMap> parametros, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.instrucciones = instrucciones;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        for (var i : this.instrucciones) {

            if(i == null){
                continue;
            }
            
            var resultado = i.interpretar(arbol, tabla);
            if (resultado instanceof Errores) {
                arbol.errores.add((Errores) resultado);
            }

            if (resultado instanceof ReturnValue returnValor) {

                if (returnValor.tipo.getTipo() != tipoDato.VOID) {
                    return new Errores("SEMANTICO", "Un metodo no puede devolver un valor.", this.linea, this.col);
                }else{
                    return null;
                }
            }
        }
        arbol.addEntornos(tabla);
        return null;
    }
}
