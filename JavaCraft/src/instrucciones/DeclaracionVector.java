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
public class DeclaracionVector extends Instruccion {

    public String identificador;
    public Tipo tipoD;
    public LinkedList<Instruccion> vector1;
    public LinkedList<LinkedList<Instruccion>> vector2;
    public boolean mutable;

    public DeclaracionVector(String identificador, LinkedList<Instruccion> vector1, boolean mutable, Tipo tipoD, int linea, int col) {
        super(new Tipo(tipoDato.VECTOR_1), linea, col);
        this.identificador = identificador;
        this.tipoD = tipoD;
        this.vector1 = vector1;
        this.mutable = mutable;
    }

    public DeclaracionVector(boolean mutable, String identificador, LinkedList<LinkedList<Instruccion>> vector2, Tipo tipoD, int linea, int col) {
        super(new Tipo(tipoDato.VECTOR_2), linea, col);
        this.identificador = identificador;
        this.tipoD = tipoD;
        this.vector2 = vector2;
        this.mutable = mutable;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        simbolo s = null;

        if (this.vector1 != null) {

            LinkedList<Object> vectorUni = new LinkedList<>();

            for (Instruccion elemento : this.vector1) {
                var valorInterpretado = elemento.interpretar(arbol, tabla);
                if (valorInterpretado instanceof Errores) {
                    return valorInterpretado;
                }
                if (elemento.tipo.getTipo() != this.tipoD.getTipo()) {
                    return new Errores("SEMANTICO", "El tipo del elemento que se trata de guardar en el vetor " + this.identificador + " de tipo " + this.tipoD.getTipo() + " no puede almacenar elementos de tipo " + elemento.tipo.getTipo(), this.linea, this.col);
                }
                vectorUni.add(valorInterpretado);
            }
            s = new simbolo(this.tipo, this.tipoD, this.identificador, this.mutable, vectorUni, this.linea, this.col);
        } else {
            LinkedList<LinkedList<Object>> vectorBi = new LinkedList<>();
            for (LinkedList<Instruccion> lista : this.vector2) {
                LinkedList<Object> vector = new LinkedList<>();
                for (Instruccion elemento : lista) {
                    var valorInterpretado = elemento.interpretar(arbol, tabla);
                    if (valorInterpretado instanceof Errores) {
                        return valorInterpretado;
                    }
                    if (elemento.tipo.getTipo() != this.tipoD.getTipo()) {
                        return new Errores("SEMANTICO", "El tipo del elemento que se trata de guardar en el vetor " + this.identificador + " de tipo " + this.tipoD.getTipo() + " no puede almacenar elementos de tipo " + elemento.tipo.getTipo(), this.linea, this.col);
                    }
                    vector.add(valorInterpretado);
                }
                vectorBi.add(vector);
            }
            s = new simbolo(this.tipo, this.tipoD, this.identificador, this.mutable, vectorBi, this.linea, this.col);
        }

        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente", this.linea, this.col);
        }

        return null;
    }
}
