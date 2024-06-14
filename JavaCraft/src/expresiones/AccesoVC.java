/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

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
public class AccesoVC extends Instruccion {

    private String id;

    public AccesoVC(String id, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valor = tabla.getVariable(this.id);
        if (valor == null) {
            return new Errores("SEMANTICA", "Variable no existente",
                    this.linea, this.col);
        }
        this.tipo.setTipo(valor.getTipo().getTipo());
        return valor.getValor();
    }

}