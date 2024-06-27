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
public class ToString extends Instruccion {

    private Instruccion expresion;

    public ToString(Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDato.CADENA), linea, col);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var exp = this.expresion.interpretar(arbol, tabla);

        if (this.expresion.tipo.getTipo() == tipoDato.ENTERO) {
            return exp.toString();
        } else if (this.expresion.tipo.getTipo() == tipoDato.DECIMAL) {
            return  exp.toString();
        } else if (this.expresion.tipo.getTipo() == tipoDato.CARACTER) {
            return exp.toString();
        } else if (this.expresion.tipo.getTipo() == tipoDato.BOOLEANO) {
            return exp.toString();
        } else if (this.expresion.tipo.getTipo() == tipoDato.STRUCT) {
            return exp.toString();
        } else {
            return new Errores("SEMANTICA", "La funcion ToString solo permite cambiar a string a enteros, decimal, caracter, booleanos y struct", this.linea, this.col);
        }
    }

}
