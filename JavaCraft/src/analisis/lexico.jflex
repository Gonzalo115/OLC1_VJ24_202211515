package analisis;

//importaciones
import java_cup.runtime.Symbol;

%%

//codigo de usuario
%{

%}

%init{
    yyline = 1;
    yycolumn = 1;
%init}

//caracteristicas de jflex
%cup
%class scanner
%public
%line
%char
%column
%full
%debug
%ignorecase

// Estados léxicos
%state COMENT_MULTI
%state COMENT_SIMPLE


//simbolos del sistema
PAR1="("
PAR2=")"
DOSPUNTOS=":"
FINCADENA=";"
MAS="+"
MENOS="-"
ASTERISCO="*"
DOBLE_ASTERISCO="**"
PORCENTUAL="%"
COMENTARIO_LINEA= ([//][^\n]*)
COMENTARIO="/\\*[\\s\\S]*?\\*/"
BARRA="/"
BLANCOS=[\ \r\t\f\n]+
IGUAL="="
MENOR="<"
MAYOR=">"
NEGACION="!"
PIPE="|"
AMPERSAND="&"
CIRCUNFLEJO="^"



ENTERO=[0-9]+
DECIMAL=[0-9]+"."[0-9]+
CADENA = [\"]((\\n)|(\\\")|(\\\\)|(\\t)|(\\\')|([^\"]))*[\"]
BOOLEANO = ((true)|(false))
CARACTER= ('[^\ \r\t\f\n]')

ID=[a-zA-z][a-zA-Z0-9_]*

//palabras reservadas
// IMPRIMIR="println"
// INT="int"
// DOUBLE="double"
// BOOL="bool"
// CHAR = "char"
// STRING="String"

%%

//Comentario Multilinea
<YYINITIAL> "/*"                {yybegin(COMENT_MULTI);}
<COMENT_MULTI> "*/"             {yybegin(YYINITIAL);}
<COMENT_MULTI> .                {}
<COMENT_MULTI> [ \t\r\n\f]      {}

//Comentario una linea
<YYINITIAL> "//"                {yybegin(COMENT_SIMPLE);}
<COMENT_SIMPLE> [^"\n"]         {}
<COMENT_SIMPLE> "\n"            {yybegin(YYINITIAL);}

//Palabras Reservadas
<YYINITIAL> "println" {return new Symbol(sym.IMPRIMIR, yyline, yycolumn,yytext());}
<YYINITIAL> "int" {return new Symbol(sym.INT, yyline, yycolumn,yytext());}
<YYINITIAL> "double" {return new Symbol(sym.DOUBLE, yyline, yycolumn,yytext());}
<YYINITIAL> "bool" {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}
<YYINITIAL> "char" {return new Symbol(sym.CHAR, yyline, yycolumn,yytext());}
<YYINITIAL> "String" {return new Symbol(sym.STRINGG, yyline, yycolumn,yytext());}
<YYINITIAL> "var" {return new Symbol(sym.VAR, yyline, yycolumn,yytext());}
<YYINITIAL> "const" {return new Symbol(sym.CONST, yyline, yycolumn,yytext());}

<YYINITIAL> {ID} {return new Symbol(sym.ID, yyline, yycolumn,yytext());}

//Estructura de las variables
<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOLEANO} {return new Symbol(sym.BOOLEANO, yyline, yycolumn,yytext());}
<YYINITIAL> {CARACTER} {
    String caracter = yytext();
    caracter = caracter.substring(1, caracter.length()-1);
    return new Symbol(sym.CARACTER, yyline, yycolumn,caracter);
    }
<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    System.out.println(cadena);
    cadena = cadena.replace('\"','"');
    System.out.println(cadena);
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);

    }


//Simbolos del sistema
<YYINITIAL> {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn,yytext());}
<YYINITIAL> {DOSPUNTOS} {return new Symbol(sym.DOSPUNTOS, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR1} {return new Symbol(sym.PAR1, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR2} {return new Symbol(sym.PAR2, yyline, yycolumn,yytext());}
<YYINITIAL> {MAS} {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> {BARRA} {return new Symbol(sym.BARRA, yyline, yycolumn,yytext());}
<YYINITIAL> {PORCENTUAL} {return new Symbol(sym.PORCENTUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {DOBLE_ASTERISCO} {return new Symbol(sym.DOBLE_ASTERISCO, yyline, yycolumn,yytext());}
<YYINITIAL> {ASTERISCO} {return new Symbol(sym.ASTERISCO, yyline, yycolumn,yytext());}
<YYINITIAL> {IGUAL} {return new Symbol(sym.IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOR} {return new Symbol(sym.MENOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYOR} {return new Symbol(sym.MAYOR, yyline, yycolumn,yytext());}
<YYINITIAL> {NEGACION} {return new Symbol(sym.NEGACION, yyline, yycolumn,yytext());}
<YYINITIAL> {PIPE} {return new Symbol(sym.PIPE, yyline, yycolumn,yytext());}
<YYINITIAL> {AMPERSAND} {return new Symbol(sym.AMPERSAND, yyline, yycolumn,yytext());}
<YYINITIAL> {CIRCUNFLEJO} {return new Symbol(sym.CIRCUNFLEJO, yyline, yycolumn,yytext());}

//Ignorar Espacios en blanco
<YYINITIAL> {BLANCOS} {}