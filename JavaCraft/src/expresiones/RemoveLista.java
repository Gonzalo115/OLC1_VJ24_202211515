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
public class RemoveLista extends Instruccion {

    private String identificador;
    private Instruccion posicion;

    public RemoveLista(String identificador, Instruccion posicion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.identificador = identificador;
        this.posicion = posicion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valor = tabla.getVariable(this.identificador);

        if (valor == null) {
            return new Errores("SEMANTICA", "Variable no existente", this.linea, this.col);
        }

        var pos = this.posicion.interpretar(arbol, tabla);

        if (pos instanceof Errores) {
            return pos;
        }

        if (valor.getTipoD() == null) {
            return new Errores("SEMANTICA", "Variable que intentas accede como un lista no es un vector su tipo de dato es " + valor.getTipo().getTipo(), this.linea, this.col);
        }

        if (this.posicion.tipo.getTipo() != tipoDato.ENTERO) {
            return new Errores("SEMANTICA", "El tipo de Dato de la ubicacion debe de ser tipo ENTERO", this.linea, this.col);
        }

        LinkedList<Object> new_lista = (LinkedList<Object>) valor.getValor();

        if ((int) pos >= 0 && (int) pos < new_lista.size()) {
            Object value = new_lista.get((int) pos);
            new_lista.remove((int) pos);
            valor.setValor(new_lista);
            this.tipo.setTipo(valor.getTipoD().getTipo());
            return value;
        } else {
            return new Errores("SEMANTICA", "El posicion en el lista " + this.identificador + " no existe.", this.linea, this.col);
        }
    }

}
