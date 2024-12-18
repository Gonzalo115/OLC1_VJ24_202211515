/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class AsignacionVC extends Instruccion {

    private String id;
    private Instruccion exp;

    public AsignacionVC(String id, Instruccion exp, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("SEMANTICO", "Variable no exitente", this.linea, this.col);
        }

        if (!variable.mutable) {
            return new Errores("SEMANTICO", "Esta Tratando de Asignar un Valor a una variable CONST", this.linea, this.col);
        }


        var newValor = this.exp.interpretar(arbol, tabla);
        if (newValor instanceof Errores) {
            return newValor;
        }


        if (variable.getTipo().getTipo() != this.exp.tipo.getTipo()) {
            return new Errores("SEMANTICO", "El tipo de variable "+variable.getTipo().getTipo()+" y el tipo de dato "+this.exp.tipo.getTipo()+" No coicide. ",
                    this.linea, this.col);
        }


        variable.setValor(newValor);
        return null;
    }

}
