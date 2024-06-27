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
public class AppendLista extends Instruccion {

    private String identificador;
    private Instruccion expresion;

    public AppendLista(String identificador, Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.LISTA), linea, col);
        this.identificador = identificador;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        var valor = tabla.getVariable(this.identificador);

        if (valor == null) {
            return new Errores("SEMANTICA", "Variable no existente", this.linea, this.col);
        }

        var append_value = this.expresion.interpretar(arbol, tabla);

        if (append_value instanceof Errores) {
            return append_value;
        }

        if (valor.getTipo().getTipo() != tipoDato.LISTA) {
            return new Errores("SEMANTICA", "Variable que intentas accede como una lista no es una lista su tipo de dato es " + valor.getTipo().getTipo(), this.linea, this.col);
        }

        if (valor.getTipoD().getTipo() != this.expresion.tipo.getTipo()) {
            return new Errores("SEMANTICO", "El tipo de la Lista " + valor.getTipo().getTipo() + " y el tipo del dato " + this.expresion.tipo.getTipo() + " que se desea agregar no coicide no coicide. ",
                    this.linea, this.col);
        }

        LinkedList<Object> new_lista = (LinkedList<Object>) valor.getValor();
        
        new_lista.add(append_value);

        valor.setValor(new_lista);
        return null;
    }

}
