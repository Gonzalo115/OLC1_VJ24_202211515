/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class Length extends Instruccion {

    private Instruccion expresion;
    private Instruccion pos;

    public Length(Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.expresion = expresion;
    }

    public Length(Instruccion expresion, Instruccion pos, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.expresion = expresion;
        this.pos = pos;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var exp = this.expresion.interpretar(arbol, tabla);

        if (this.expresion.tipo.getTipo() == tipoDato.CADENA) {
            return exp.toString().length();
        } else if (this.expresion.tipo.getTipo() == tipoDato.LISTA) {
            LinkedList<Object> list = (LinkedList<Object>) exp;
            return list.size();
        } else if (this.expresion.tipo.getTipo() == tipoDato.VECTOR_1) {
            LinkedList<Object> list = (LinkedList<Object>) exp;
            return list.size();
        } else if (this.expresion.tipo.getTipo() == tipoDato.VECTOR_2) {
            LinkedList<Object> list = (LinkedList<Object>) exp;

            if (pos != null) {
                var posV = this.pos.interpretar(arbol, tabla);
                if (posV instanceof Errores) {
                    return posV;
                }
                if (this.pos.tipo.getTipo() != tipoDato.ENTERO) {
                    return new Errores("SEMANTICA", "Para ubicarte en una posicion del arreglo necesitas un ENTERO", this.linea, this.col);
                }
                LinkedList<Object> list_list = (LinkedList<Object>) list.get((int) posV);
                
                return list_list.size();

            }

            return list.size();
        } else {
            return new Errores("SEMANTICA", "Para la funcion Length se necesita una Cadena, lista o vector", this.linea, this.col);
        }

    }

}
