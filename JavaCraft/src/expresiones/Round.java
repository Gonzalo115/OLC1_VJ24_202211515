/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;

/**
 *
 * @author 5gonz
 */
public class Round extends Instruccion {

    private Instruccion expresion;

    public Round(Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var num = this.expresion.interpretar(arbol, tabla);

        if (this.expresion.tipo.getTipo() == tipoDato.DECIMAL) {
            return Math.round((double) num);
        } else if (this.expresion.tipo.getTipo() == tipoDato.ENTERO) {
            return num;
        } else {
            return new Errores("SEMANTICA", "Para Round se necesita un valor de tipo Decimal o Entero", this.linea, this.col);
        }

    }

}
