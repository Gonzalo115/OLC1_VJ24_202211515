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
public class AsignacionVector extends Instruccion {

    private String id;
    private Instruccion pos1;
    private Instruccion pos2;
    private Instruccion expresion;

    public AsignacionVector(String id, Instruccion pos1, Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.pos1 = pos1;
        this.expresion = expresion;
    }

    public AsignacionVector(String id, Instruccion pos1, Instruccion pos2, Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        var valor = tabla.getVariable(this.id);

        if (valor == null) {
            return new Errores("SEMANTICA", "Variable no existente", this.linea, this.col);
        }

        if (!valor.mutable) {
            return new Errores("SEMANTICO", "Esta Tratando de Asignar un Valor a una variable CONST", this.linea, this.col);
        }

        var newValor = this.expresion.interpretar(arbol, tabla);
        if (newValor instanceof Errores) {
            return newValor;
        }

        if (valor.getTipoD().getTipo() != this.expresion.tipo.getTipo()) {
            return new Errores("SEMANTICO", "El tipo del vector " + valor.getTipo().getTipo() + " y el tipo de dato " + this.expresion.tipo.getTipo() + " No coicide. ",
                    this.linea, this.col);
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

                LinkedList<Object> new_vector = (LinkedList<Object>) valor.getValor();

                if ((int) pos_a >= 0 && (int) pos_a < new_vector.size()) {

                    new_vector.set((int) pos_a, newValor);

                    valor.setValor(new_vector);
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

                LinkedList<LinkedList<Object>> new_vector = (LinkedList<LinkedList<Object>>) valor.getValor();

                if ((int) pos_a >= 0 && (int) pos_a < new_vector.size()) {

                    LinkedList<Object> new_Vector2 = new_vector.get((int) pos_a);
                    if ((int) pos_b >= 0 && (int) pos_b < new_Vector2.size()) {
                        
                        new_Vector2.set((int) pos_b, newValor);
                        new_vector.set((int) pos_a, new_Vector2);

                        valor.setValor(new_vector);

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

        return null;
    }

}
