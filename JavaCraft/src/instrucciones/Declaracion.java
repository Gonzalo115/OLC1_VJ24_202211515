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
public class Declaracion extends Instruccion {

    public String identificador;
    public Instruccion valor;
    public boolean mutable;

    public Declaracion(boolean mutable, String identificador, Tipo tipo, Instruccion valor, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.valor = valor;
        this.mutable = mutable;
    }

    public Declaracion(boolean mutable, String identificador, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.identificador = identificador;
        this.mutable = mutable;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        simbolo s = null;

        if (this.valor != null) {
            var valorInterpretado = this.valor.interpretar(arbol, tabla);
            if (valorInterpretado instanceof Errores) {
                return valorInterpretado;
            }
            if (this.valor.tipo.getTipo() != this.tipo.getTipo()) {
                return new Errores("SEMANTICO", "El tipo de variable " + this.tipo.getTipo() + " y el tipo de dato " + this.valor.tipo.getTipo() + " No coicide. ", this.linea, this.col);
            }
            //Tipo tipo, String tipoD, String id, boolean mutable, Object valor, int linea, int columna

            s = new simbolo(this.tipo, this.identificador, this.mutable, valorInterpretado, this.linea, this.col);

        } else {
            Object valorInterpretado;
            switch (this.tipo.getTipo()) {
                case tipoDato.ENTERO:
                    valorInterpretado = 0;
                    break;
                case tipoDato.DECIMAL:
                    valorInterpretado = 0.0;
                    break;
                case tipoDato.BOOLEANO:
                    valorInterpretado = true;
                    break;
                case tipoDato.CARACTER:
                    valorInterpretado = '\u0000';
                    break;
                case tipoDato.CADENA:
                    valorInterpretado = "";
                    break;
                default:
                    return new Errores("SEMANTICO", "El tipo definido por la funcion no existe", this.linea, this.col);
            }

            s = new simbolo(this.tipo, this.identificador, this.mutable, valorInterpretado, this.linea, this.col);

        }

        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente", this.linea, this.col);
        }

        return null;
    }
}
