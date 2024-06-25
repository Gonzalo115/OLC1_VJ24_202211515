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
public class AccesoVector extends Instruccion {

    private String id;
    private Instruccion pos1;
    private Instruccion pos2;

    public AccesoVector(String id, Instruccion pos1, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.pos1 = pos1;
    }

    public AccesoVector(String id, Instruccion pos1, Instruccion pos2, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        Object valor_a_retornar = null;

        var valor = tabla.getVariable(this.id);
        if (valor == null) {
            return new Errores("SEMANTICA", "Variable no existente", this.linea, this.col);
        }

        if (valor.getTipo().getTipo() == tipoDato.VECTOR_1) {

            if (this.pos2 == null) {
                var pos_a = this.pos1.interpretar(arbol, tabla);

                if (pos_a instanceof Errores) {
                    return pos_a;
                }

                if (this.pos1.tipo.getTipo() != tipoDato.ENTERO) {
                    return new Errores("SEMANTICA", "El tipo de Dato de la ubicacion debe de ser tipo ENTERO", this.linea, this.col);
                }

                LinkedList<Object> vector = (LinkedList<Object>) valor.getValor();

                if ((int) pos_a >= 0 && (int) pos_a < vector.size()) {
                    valor_a_retornar = vector.get((int) pos_a);
                } else {
                    return new Errores("SEMANTICA", "El posicion en el vector " + this.id + " no existe.", this.linea, this.col);
                }
            } else {
                return new Errores("SEMANTICA", "Estas dando una segunda ubicacion a el vector " + valor.getId() + " de una dimension.", this.linea, this.col);
            }

        } else if (valor.getTipo().getTipo() == tipoDato.VECTOR_2) {

            if (this.pos2 != null) {
                var pos_a = this.pos1.interpretar(arbol, tabla);
                var pos_b = this.pos2.interpretar(arbol, tabla);

                if (pos_a instanceof Errores) {
                    return pos_a;
                }

                if (this.pos1.tipo.getTipo() != tipoDato.ENTERO) {
                    return new Errores("SEMANTICA", "El tipo de Dato de la ubicacion debe de ser tipo ENTERO", this.linea, this.col);
                }

                if (pos_b instanceof Errores) {
                    return pos_b;
                }

                if (this.pos2.tipo.getTipo() != tipoDato.ENTERO) {
                    return new Errores("SEMANTICA", "El tipo de Dato de la ubicacion debe de ser tipo ENTERO", this.linea, this.col);
                }

                LinkedList<LinkedList<Object>> vec_1 = (LinkedList<LinkedList<Object>>) valor.getValor();

                if ((int) pos_a >= 0 && (int) pos_a < vec_1.size()) {

                    LinkedList<Object> vec_2 = vec_1.get((int) pos_a);
                    if ((int) pos_b >= 0 && (int) pos_b < vec_2.size()) {
                        valor_a_retornar = vec_2.get((int) pos_b);
                    } else {
                        return new Errores("SEMANTICA", "La posicion en el vector " + this.id + " en la segunda dimension no existe.", this.linea, this.col);
                    }

                } else {
                    return new Errores("SEMANTICA", "La posicion en el vector " + this.id + " en la primera dimension no existe.", this.linea, this.col);
                }

            } else {
                return new Errores("SEMANTICA", "Estas dando ubicaciones menos para el vector " + valor.getId() + " de dos dimensiones.", this.linea, this.col);
            }
        } else {
            return new Errores("SEMANTICA", "Variable que intentas accede como un vector no es un vector su tipo de dato es " + valor.getTipo().getTipo(), this.linea, this.col);

        }

        this.tipo.setTipo(valor.getTipoD().getTipo());
        return valor_a_retornar;
    }

}
