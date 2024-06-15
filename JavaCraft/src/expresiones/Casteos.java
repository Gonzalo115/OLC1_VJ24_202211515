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
public class Casteos extends Instruccion {

    public Tipo tipo_Cas;
    public Instruccion valor;

    public Casteos(Tipo tipo_Cas, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.tipo_Cas = tipo_Cas;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        Object cas = this.valor.interpretar(arbol, tabla);

        if (cas instanceof Errores) {
            return cas;
        }

        return switch (this.tipo_Cas.getTipo()) {
            case tipoDato.ENTERO ->
                this.Entero_D_C(cas);
            case tipoDato.DECIMAL ->
                this.Decimal_E(cas);
            case tipoDato.CARACTER ->
                this.Caracter_E_D(cas);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.col);
        };
    }

    public Object Entero_D_C(Object cas) {
        try {
            switch (this.valor.tipo.getTipo()) {
                case tipoDato.DECIMAL -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return ((Double) cas).intValue();
                }
                case tipoDato.CARACTER -> {
                    this.tipo.setTipo(tipoDato.ENTERO);
                    return (int) cas.toString().charAt(0);
                }
                default -> {
                    return new Errores("SEMANTICO", "El tipo de dato " + this.valor.tipo.getTipo() + " de la variable no puede ser casteado a " + this.tipo_Cas.getTipo() + ".", this.linea, this.col);
                }
            }
        } catch (ClassCastException e) {
            return new Errores("SEMANTICO", "Error de casteo: " + e.getMessage(), this.linea, this.col);
        }
    }

    public Object Decimal_E(Object cas) {
        try {
            switch (this.valor.tipo.getTipo()) {
                case tipoDato.ENTERO -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return ((Integer) cas).doubleValue();
                }
                case tipoDato.CARACTER -> {
                    this.tipo.setTipo(tipoDato.DECIMAL);
                    return (double) (int) cas.toString().charAt(0);
                }
                default -> {
                    return new Errores("SEMANTICO", "El tipo de dato " + this.valor.tipo.getTipo() + " de la variable no puede ser casteado a " + this.tipo_Cas.getTipo() + ".", this.linea, this.col);
                }
            }
        } catch (ClassCastException e) {
            return new Errores("SEMANTICO", "Error de casteo: " + e.getMessage(), this.linea, this.col);
        }
    }

    public Object Caracter_E_D(Object cas) {
        try {
            switch (this.valor.tipo.getTipo()) {
                case tipoDato.ENTERO -> {
                    this.tipo.setTipo(tipoDato.CARACTER);
                    return (char) ((Integer) cas).intValue();
                }
                default -> {
                    return new Errores("SEMANTICO", "El tipo de dato " + this.valor.tipo.getTipo() + " de la variable no puede ser casteado a " + this.tipo_Cas.getTipo() + ".", this.linea, this.col);
                }
            }
        } catch (ClassCastException e) {
            return new Errores("SEMANTICO", "Error de casteo: " + e.getMessage(), this.linea, this.col);
        }
    }
}
