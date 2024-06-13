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
        var cond_Izq = this.condicion1.interpretar(arbol, tabla);
        if (cond_Izq instanceof Errores) {
            return cond_Izq;
        }

        var cond_Der = this.condicion2.interpretar(arbol, tabla);
        if (cond_Der instanceof Errores) {
            return cond_Der;
        }

        return switch (relacional) {
            case IGUAL ->
                this.igual(cond_Izq, cond_Der);
            case NOIGUAL ->
                this.no_igual(cond_Izq, cond_Der);
            case MENOR ->
                this.menor(cond_Izq, cond_Der);
            case MAYOR ->
                this.mayor(cond_Izq, cond_Der);
            case IGUAL_MENOR ->
                this.igual_menor(cond_Izq, cond_Der);
            case IGUAL_MAYOR ->
                this.igual_mayor(cond_Izq, cond_Der);
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object igual(Object cond_Izq, Object cond_Der) {
        var comparandoA = this.condicion1.tipo.getTipo();
        var comparandoB = this.condicion2.tipo.getTipo();

        return switch (comparandoA) {
            case tipoDato.ENTERO ->
                switch (comparandoB) {
                    case tipoDato.ENTERO ->
                        (int) cond_Izq == (int) cond_Der;
                    case tipoDato.DECIMAL ->
                        (int) cond_Izq == (double) cond_Der;
                    case tipoDato.CARACTER ->
                        (int) cond_Izq == (int) cond_Der.toString().charAt(0);
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '=' los tipos de datos ENTERO y " + comparandoB, this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparandoB) {
                    case tipoDato.ENTERO ->
                        (double) cond_Izq == (int) cond_Der;
                    case tipoDato.DECIMAL ->
                        (double) cond_Izq == (double) cond_Der;
                    case tipoDato.CARACTER ->
                        (double) cond_Izq == (int) cond_Der.toString().charAt(0);
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '=' los tipos de datos DECIMAL y " + comparandoB, this.linea, this.col);
                };
            case tipoDato.BOOLEANO ->
                switch (comparandoB) {
                    case tipoDato.BOOLEANO ->
                
                        toBoolean(cond_Izq) == toBoolean(cond_Der);
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '=' los tipos de dato BOOLEANO y " + comparandoB, this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparandoB) {
                    case tipoDato.ENTERO ->
                        (int) cond_Izq == (int) cond_Der;
                    case tipoDato.DECIMAL ->
                        (int) cond_Izq == (double) cond_Der;
                    case tipoDato.CARACTER ->
                        (int) cond_Izq.toString().charAt(0) == (int) cond_Der.toString().charAt(0);
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '=' los tipos de dato CARACTER y " + comparandoB, this.linea, this.col);
                };
            case tipoDato.CADENA ->
                switch (comparandoB) {
                    case tipoDato.CADENA ->
                        cond_Izq.toString().equalsIgnoreCase(cond_Der.toString());
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '=' los tipos de dato CADENA y " + comparandoB, this.linea, this.col);

                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object no_igual(Object cond_Izq, Object cond_Der) {
        var comparandoA = this.condicion1.tipo.getTipo();
        var comparandoB = this.condicion2.tipo.getTipo();

        return switch (comparandoA) {
            case tipoDato.ENTERO ->
                switch (comparandoB) {
                    case tipoDato.ENTERO ->
                        (int) cond_Izq != (int) cond_Der;
                    case tipoDato.DECIMAL ->
                        (int) cond_Izq != (double) cond_Der;
                    case tipoDato.CARACTER ->
                        (int) cond_Izq != (int) cond_Der.toString().charAt(0);
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '!=' los tipos de datos ENTERO y " + comparandoB, this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparandoB) {
                    case tipoDato.ENTERO ->
                        (double) cond_Izq != (int) cond_Der;
                    case tipoDato.DECIMAL ->
                        (double) cond_Izq != (double) cond_Der;
                    case tipoDato.CARACTER ->
                        (double) cond_Izq != (int) cond_Der.toString().charAt(0);
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '!=' los tipos de datos DECIMAL y " + comparandoB, this.linea, this.col);
                };
            case tipoDato.BOOLEANO ->
                switch (comparandoB) {
                    case tipoDato.BOOLEANO ->
                        toBoolean(cond_Izq) != toBoolean(cond_Der);
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '!=' los tipos de dato BOOLEANO y " + comparandoB, this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparandoB) {
                    case tipoDato.ENTERO ->
                        (int) cond_Izq.toString().charAt(0) != (int) cond_Der;
                    case tipoDato.DECIMAL ->
                        (int) cond_Izq.toString().charAt(0) != (double) cond_Der;
                    case tipoDato.CARACTER ->
                        (int) cond_Izq.toString().charAt(0) != (int) cond_Der.toString().charAt(0);
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '!=' los tipos de dato CARACTER y " + comparandoB, this.linea, this.col);
                };
            case tipoDato.CADENA ->
                switch (comparandoB) {
                    case tipoDato.CADENA ->
                        !cond_Izq.toString().equalsIgnoreCase(cond_Der.toString());
                    default ->
                        new Errores("SEMANTICO", "No se pueden comparar '!=' los tipos de dato CADENA y " + comparandoB, this.linea, this.col);

                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object menor(Object cond_Izq, Object cond_Der) {
        var comparandoA = this.condicion1.tipo.getTipo();
        var comparandoB = this.condicion2.tipo.getTipo();

        switch (comparandoA) {
            case tipoDato.ENTERO -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (int) cond_Izq < (int) cond_Der;
                    }
                    case tipoDato.DECIMAL -> {
                        return (int) cond_Izq < (double) cond_Der;
                    }
                    case tipoDato.CARACTER -> {
                        return (int) cond_Izq < (int) cond_Der.toString().charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<' los tipos de datos ENTERO y " + comparandoB, this.linea, this.col);
                    }
                }

            }
            case tipoDato.DECIMAL -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (double) cond_Izq < (int) cond_Der;
                    }
                    case tipoDato.DECIMAL -> {
                        return (double) cond_Izq < (double) cond_Der;
                    }
                    case tipoDato.CARACTER -> {
                        return (double) cond_Izq < (int) cond_Der.toString().charAt(0);

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<' los tipos de datos DECIMAL y " + comparandoB, this.linea, this.col);

                    }
                }
            }
            case tipoDato.BOOLEANO -> {
                switch (comparandoB) {
                    case tipoDato.BOOLEANO -> {

                        int int_Izq = 0;
                        int int_Der = 0;
                        
                        if (toBoolean(cond_Izq)== true) {
                            int_Izq = 1;

                        } else if (toBoolean(cond_Izq)== false) {
                            int_Der = 0;
                        } else {
                            return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                        }

                        if (toBoolean(cond_Der)== true) {
                            int_Izq = 1;
                        } else if (toBoolean(cond_Der)== false) {
                            int_Der = 0;
                        } else {
                            return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                        }

                        return int_Izq < int_Der;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<' los tipos de dato BOOLEANO y " + comparandoB, this.linea, this.col);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (int) cond_Izq.toString().charAt(0) < (int) cond_Der;

                    }
                    case tipoDato.DECIMAL -> {
                        return (int) cond_Izq.toString().charAt(0) < (double) cond_Der;

                    }
                    case tipoDato.CARACTER -> {
                        return (int) cond_Izq.toString().charAt(0) < (int) cond_Der.toString().charAt(0);

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<' los tipos de dato CARACTER y " + comparandoB, this.linea, this.col);

                    }
                }
            }
            case tipoDato.CADENA -> {
                switch (comparandoB) {
                    case tipoDato.CADENA -> {
                        String S_cond_Izq = cond_Izq.toString();
                        String S_cond_Der = cond_Der.toString();

                        return S_cond_Izq.length() < S_cond_Der.length();

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<' los tipos de dato CADENA y " + comparandoB, this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }

    public Object mayor(Object cond_Izq, Object cond_Der) {
        var comparandoA = this.condicion1.tipo.getTipo();
        var comparandoB = this.condicion2.tipo.getTipo();

        switch (comparandoA) {
            case tipoDato.ENTERO -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (int) cond_Izq > (int) cond_Der;
                    }
                    case tipoDato.DECIMAL -> {
                        return (int) cond_Izq > (double) cond_Der;
                    }
                    case tipoDato.CARACTER -> {
                        return (int) cond_Izq > (int) cond_Der.toString().charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>' los tipos de datos ENTERO y " + comparandoB, this.linea, this.col);
                    }
                }

            }
            case tipoDato.DECIMAL -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (double) cond_Izq > (int) cond_Der;
                    }
                    case tipoDato.DECIMAL -> {
                        return (double) cond_Izq > (double) cond_Der;
                    }
                    case tipoDato.CARACTER -> {
                        return (double) cond_Izq > (int) cond_Der.toString().charAt(0);

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>' los tipos de datos DECIMAL y " + comparandoB, this.linea, this.col);

                    }
                }
            }
            case tipoDato.BOOLEANO -> {
                switch (comparandoB) {
                    case tipoDato.BOOLEANO -> {

                        int int_Izq = 0;
                        int int_Der = 0;
                        
                        if (toBoolean(cond_Izq)== true) {
                            int_Izq = 1;

                        } else if (toBoolean(cond_Izq)== false) {
                            int_Der = 0;
                        } else {
                            return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                        }

                        if (toBoolean(cond_Der)== true) {
                            int_Izq = 1;
                        } else if (toBoolean(cond_Der)== false) {
                            int_Der = 0;
                        } else {
                            return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                        }

                        return int_Izq > int_Der;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>' los tipos de dato BOOLEANO y " + comparandoB, this.linea, this.col);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (int) cond_Izq.toString().charAt(0) > (int) cond_Der;

                    }
                    case tipoDato.DECIMAL -> {
                        return (int) cond_Izq.toString().charAt(0) > (double) cond_Der;

                    }
                    case tipoDato.CARACTER -> {
                        return (int) cond_Izq.toString().charAt(0) > (int) cond_Der.toString().charAt(0);

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>' los tipos de dato CARACTER y " + comparandoB, this.linea, this.col);

                    }
                }
            }
            case tipoDato.CADENA -> {
                switch (comparandoB) {
                    case tipoDato.CADENA -> {
                        String S_cond_Izq = cond_Izq.toString();
                        String S_cond_Der = cond_Der.toString();

                        return S_cond_Izq.length() > S_cond_Der.length();

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>' los tipos de dato CADENA y " + comparandoB, this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }

    public Object igual_menor(Object cond_Izq, Object cond_Der) {
        var comparandoA = this.condicion1.tipo.getTipo();
        var comparandoB = this.condicion2.tipo.getTipo();

        switch (comparandoA) {
            case tipoDato.ENTERO -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (int) cond_Izq <= (int) cond_Der;
                    }
                    case tipoDato.DECIMAL -> {
                        return (int) cond_Izq <= (double) cond_Der;
                    }
                    case tipoDato.CARACTER -> {
                        return (int) cond_Izq <= (int) cond_Der.toString().charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<=' los tipos de datos ENTERO y " + comparandoB, this.linea, this.col);
                    }
                }

            }
            case tipoDato.DECIMAL -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (double) cond_Izq <= (int) cond_Der;
                    }
                    case tipoDato.DECIMAL -> {
                        return (double) cond_Izq <= (double) cond_Der;
                    }
                    case tipoDato.CARACTER -> {
                        return (double) cond_Izq <= (int) cond_Der.toString().charAt(0);

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<=' los tipos de datos DECIMAL y " + comparandoB, this.linea, this.col);

                    }
                }
            }
            case tipoDato.BOOLEANO -> {
                switch (comparandoB) {
                    case tipoDato.BOOLEANO -> {

                        int int_Izq = 0;
                        int int_Der = 0;
                        
                        if (toBoolean(cond_Izq)== true) {
                            int_Izq = 1;

                        } else if (toBoolean(cond_Izq)== false) {
                            int_Der = 0;
                        } else {
                            return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                        }

                        if (toBoolean(cond_Der)== true) {
                            int_Izq = 1;
                        } else if (toBoolean(cond_Der)== false) {
                            int_Der = 0;
                        } else {
                            return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                        }

                        return int_Izq <= int_Der;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<=' los tipos de dato BOOLEANO y " + comparandoB, this.linea, this.col);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (int) cond_Izq.toString().charAt(0) <= (int) cond_Der;

                    }
                    case tipoDato.DECIMAL -> {
                        return (int) cond_Izq.toString().charAt(0) <= (double) cond_Der;

                    }
                    case tipoDato.CARACTER -> {
                        return (int) cond_Izq.toString().charAt(0) <= (int) cond_Der.toString().charAt(0);

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<=' los tipos de dato CARACTER y " + comparandoB, this.linea, this.col);

                    }
                }
            }
            case tipoDato.CADENA -> {
                switch (comparandoB) {
                    case tipoDato.CADENA -> {
                        String S_cond_Izq = cond_Izq.toString();
                        String S_cond_Der = cond_Der.toString();

                        return S_cond_Izq.length() <= S_cond_Der.length();

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '<=' los tipos de dato CADENA y " + comparandoB, this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }

    public Object igual_mayor(Object cond_Izq, Object cond_Der) {
        var comparandoA = this.condicion1.tipo.getTipo();
        var comparandoB = this.condicion2.tipo.getTipo();
        switch (comparandoA) {
            case tipoDato.ENTERO -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (int) cond_Izq >= (int) cond_Der;
                    }
                    case tipoDato.DECIMAL -> {
                        return (int) cond_Izq >= (double) cond_Der;
                    }
                    case tipoDato.CARACTER -> {
                        return (int) cond_Izq >= (int) cond_Der.toString().charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>=' los tipos de datos ENTERO y " + comparandoB, this.linea, this.col);
                    }
                }

            }
            case tipoDato.DECIMAL -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (double) cond_Izq >= (int) cond_Der;
                    }
                    case tipoDato.DECIMAL -> {
                        return (double) cond_Izq >= (double) cond_Der;
                    }
                    case tipoDato.CARACTER -> {
                        return (double) cond_Izq >= (int) cond_Der.toString().charAt(0);

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>=' los tipos de datos DECIMAL y " + comparandoB, this.linea, this.col);

                    }
                }
            }
            case tipoDato.BOOLEANO -> {
                switch (comparandoB) {
                    case tipoDato.BOOLEANO -> {

                        int int_Izq = 0;
                        int int_Der = 0;
                        
                        if (toBoolean(cond_Izq)== true) {
                            int_Izq = 1;

                        } else if (toBoolean(cond_Izq)== false) {
                            int_Der = 0;
                        } else {
                            return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                        }

                        if (toBoolean(cond_Der)== true) {
                            int_Izq = 1;
                        } else if (toBoolean(cond_Der)== false) {
                            int_Der = 0;
                        } else {
                            return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                        }

                        return int_Izq >= int_Der;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>=' los tipos de dato BOOLEANO y " + comparandoB, this.linea, this.col);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (comparandoB) {
                    case tipoDato.ENTERO -> {
                        return (int) cond_Izq.toString().charAt(0) >= (int) cond_Der;

                    }
                    case tipoDato.DECIMAL -> {
                        return (int) cond_Izq.toString().charAt(0) >= (double) cond_Der;

                    }
                    case tipoDato.CARACTER -> {
                        return (int) cond_Izq.toString().charAt(0) >= (int) cond_Der.toString().charAt(0);

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>=' los tipos de dato CARACTER y " + comparandoB, this.linea, this.col);

                    }
                }
            }
            case tipoDato.CADENA -> {
                switch (comparandoB) {
                    case tipoDato.CADENA -> {
                        String S_cond_Izq = cond_Izq.toString();
                        String S_cond_Der = cond_Der.toString();

                        return S_cond_Izq.length() >= S_cond_Der.length();

                    }
                    default -> {
                        return new Errores("SEMANTICO", "No se pueden comparar '>=' los tipos de dato CADENA y " + comparandoB, this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }

    public static boolean toBoolean(Object obj) {
        if (obj instanceof String) {
            return Boolean.valueOf(((String) obj).toLowerCase());
        } else {
            return (boolean) obj;
        }
    }
}
