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
public class Logicos extends Instruccion {

    private Instruccion expresion1;
    private Instruccion expresion2;
    private OperadoresLogicos logico;
    private Instruccion logicoUnico;

    public Logicos(Instruccion logicoUnico, OperadoresLogicos logico, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.logicoUnico = logicoUnico;
        this.logico = logico;
    }

    public Logicos(Instruccion expresion1, Instruccion expresion2, OperadoresLogicos logico, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.expresion1 = expresion1;
        this.expresion2 = expresion2;
        this.logico = logico;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {

        Object expre_Izq = null, expre_Der = null, expre_Unico = null;

        if (this.logicoUnico != null) {
            expre_Unico = this.logicoUnico.interpretar(arbol, tabla);
            if (expre_Unico instanceof Errores) {
                return expre_Unico;
            }
        } else {
            expre_Izq = this.expresion1.interpretar(arbol, tabla);
            if (expre_Izq instanceof Errores) {
                return expre_Izq;
            }
            expre_Der = this.expresion2.interpretar(arbol, tabla);
            if (expre_Der instanceof Errores) {
                return expre_Der;
            }
        }

        return switch (logico) {
            case OR ->
                this.or(expre_Izq, expre_Der);
            case AND ->
                this.and(expre_Izq, expre_Der);
            case XOR ->
                this.xor(expre_Izq, expre_Der);
            case NOT ->
                this.not(expre_Unico);
            default ->
                new Errores("SEMANTICO", "Logico Invalido", this.linea, this.col);
        };
    }

    public Object or(Object op1, Object op2) {
        var tipo1 = this.expresion1.tipo.getTipo();
        var tipo2 = this.expresion2.tipo.getTipo();

        if (tipo1 == tipoDato.BOOLEANO && tipo2 == tipoDato.BOOLEANO) {
            return toBoolean(op1) || toBoolean(op2);
        } else {
            return new Errores("SEMANTICO", "Logico Invalido", this.linea, this.col);
        }
    }

    public Object and(Object op1, Object op2) {
        var tipo1 = this.expresion1.tipo.getTipo();
        var tipo2 = this.expresion2.tipo.getTipo();

        if (tipo1 == tipoDato.BOOLEANO && tipo2 == tipoDato.BOOLEANO) {
            return toBoolean(op1) && toBoolean(op2);
        } else {
            return new Errores("SEMANTICO", "Logico Invalido", this.linea, this.col);
        }
    }

    public Object xor(Object op1, Object op2) {
        var tipo1 = this.expresion1.tipo.getTipo();
        var tipo2 = this.expresion2.tipo.getTipo();

        if (tipo1 == tipoDato.BOOLEANO && tipo2 == tipoDato.BOOLEANO) {
            if (toBoolean(op1) == true && toBoolean(op2) == true) {
                return false;
            } else if (toBoolean(op1) == false && toBoolean(op2) == false) {
                return false;
            } else {
                return true;
            }
        } else {
            return new Errores("SEMANTICO", "Logico Invalido", this.linea, this.col);
        }
    }

    public Object not(Object op) {
        var tipo = this.logicoUnico.tipo.getTipo();

        if (tipo == tipoDato.BOOLEANO) {
            if (toBoolean(op) == true) {
                return false;
            } else {
                return true;
            }
        } else {
            return new Errores("SEMANTICO", "Logico Invalido", this.linea, this.col);
        }
    }

    public static boolean toBoolean(Object obj) {
        if (obj instanceof String) {
            return Boolean.valueOf(((String) obj).toLowerCase());
        }
        else {
            return (boolean) obj;
        }
    }
}
