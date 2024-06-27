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

    public Length(Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.expresion = expresion;
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
            return list.size();
        } else {
            return new Errores("SEMANTICA", "Para la funcion Length se necesita una Cadena, lista o vector", this.linea, this.col);
        }

    }

}
