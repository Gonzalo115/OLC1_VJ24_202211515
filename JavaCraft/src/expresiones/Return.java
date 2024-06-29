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
public class Return extends Instruccion {

    public Instruccion expresiones;

    public Return(int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
    }

    public Return(Instruccion expresiones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.expresiones = expresiones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        if (this.expresiones != null) {
            var retornar = this.expresiones.interpretar(arbol, tabla);
            if (retornar instanceof Errores) {
                return retornar;
            }
            return new ReturnValue(retornar, this.expresiones.tipo, this.linea, this.col);
        } else {
            return new ReturnValue(new Tipo(tipoDato.VOID), this.linea, this.col);
        }
    }
}
