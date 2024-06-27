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
public class Find extends Instruccion {

    private String identificador;
    private Instruccion expresion;

    public Find(String identificador, Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.identificador = identificador;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valor = tabla.getVariable(this.identificador);

        if (valor == null) {
            return new Errores("SEMANTICA", "Variable no existente", this.linea, this.col);
        }

        var buscarValor = this.expresion.interpretar(arbol, tabla);

        if (buscarValor instanceof Errores) {
            return buscarValor;
        }

        if (valor.getTipoD() == null) {
            return new Errores("SEMANTICA", "Variable que intentas accede como un vector/lista no es un vector su tipo de dato es " + valor.getTipo().getTipo(), this.linea, this.col);
        }

        if (valor.getTipoD().getTipo() != this.expresion.tipo.getTipo()) {
            return new Errores("SEMANTICO", "El tipo del vector " + valor.getTipo().getTipo() + " y el tipo de datol " + this.expresion.tipo.getTipo() + " No coicide. ",
                    this.linea, this.col);
        }

        boolean contains = false;
        if (valor.getTipo().getTipo() == tipoDato.VECTOR_1 && valor.getTipo().getTipo() == tipoDato.LISTA) {
            LinkedList<Object> Vector_List = (LinkedList<Object>) valor.getValor();
            contains = Vector_List.contains(buscarValor);
        } else {
            LinkedList<LinkedList<Object>> Vector_1 = (LinkedList<LinkedList<Object>>) valor.getValor();
            for (LinkedList<Object> Vector_2 : Vector_1) {
                contains = Vector_2.contains(buscarValor);
                if (contains) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }
}
