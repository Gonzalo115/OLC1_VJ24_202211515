/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author 5gonz
 */
public class Incremento extends Instruccion {

    private String id;

    public Incremento(String id, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        //variable exista
        var variable = tabla.getVariable(id);

        if (variable == null) {
            return new Errores("SEMANTICO", "Variable no exitente",
                    this.linea, this.col);
        }

        if (!variable.mutable) {
            return new Errores("SEMANTICO", "Esta Tratando de Asignar un Nuevo Valor a una variable CONST", this.linea, this.col);
        }

        //validar tipos
        if (variable.getTipo().getTipo() != tipoDato.ENTERO && variable.getTipo().getTipo() != tipoDato.DECIMAL) {
            return new Errores("SEMANTICO", "El tipo " + variable.getTipo().getTipo() + " no se puede incrementar.",
                    this.linea, this.col);
        }

        var newValor = variable.getValor();

        if (variable.getTipo().getTipo() == tipoDato.ENTERO) {
            newValor = (int) newValor + 1;
        }

        if (variable.getTipo().getTipo() == tipoDato.DECIMAL) {
            newValor = (double) newValor + 1;
        }

        //this.tipo.setTipo(variable.getTipo().getTipo());
        variable.setValor(newValor);
        return null;
    }

}
