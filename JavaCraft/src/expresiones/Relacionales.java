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
public class Relacionales extends Instruccion {

    private Instruccion condicion1;
    private Instruccion condicion2;
    private OperadoresRelacionales relacional;

    public Relacionales(Instruccion condicion1, Instruccion condicion2, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.condicion1 = condicion1;
        this.condicion2 = condicion2;
        this.relacional = relacional;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIzq = this.condicion1.interpretar(arbol, tabla);
        if (condIzq instanceof Errores) {
            return condIzq;
        }

        var condDer = this.condicion2.interpretar(arbol, tabla);
        if (condDer instanceof Errores) {
            return condDer;
        }

        return switch (relacional) {
            case IGUAL ->
                this.equals(condIzq, condDer);

            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object equals(Object comp1, Object comp2) {
        var comparando1 = this.condicion1.tipo.getTipo();
        var comparando2 = this.condicion2.tipo.getTipo();

        return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) comp1 == (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 == (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 == (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 == (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CADENA ->
                switch (comparando2) {
                    case tipoDato.CADENA ->
                        comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
}
