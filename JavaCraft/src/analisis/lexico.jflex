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

//simbolos del sistema
PAR1="("
PAR2=")"
FINCADENA=";"


MAS="+"
MENOS="-"
ASTERISCO="*"
DOBLE_ASTERISCO="**"
PORCENTUAL="%"

// COMENTARIO_LINEA = [//](.)*
// COMENTARIO = [/\*][\s\S]*[\*/]
BARRA="/"

BLANCOS=[\ \r\t\f\n]+


ENTERO=[0-9]+
DECIMAL=[0-9]+"."[0-9]+
CADENA = [\"]([^\"])*[\"]

//palabras reservadas
IMPRIMIR="imprimir"

%%
<YYINITIAL> {IMPRIMIR} {return new Symbol(sym.IMPRIMIR, yyline, yycolumn,yytext());}

<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}

<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);
    }

<YYINITIAL> {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR1} {return new Symbol(sym.PAR1, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR2} {return new Symbol(sym.PAR2, yyline, yycolumn,yytext());}

// <YYINITIAL> {COMENTARIO_LINEA} {}
// <YYINITIAL> {COMENTARIO} {}



<YYINITIAL> {MAS} {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> {BARRA} {return new Symbol(sym.BARRA, yyline, yycolumn,yytext());}
<YYINITIAL> {PORCENTUAL} {return new Symbol(sym.PORCENTUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {DOBLE_ASTERISCO} {return new Symbol(sym.DOBLE_ASTERISCO, yyline, yycolumn,yytext());}
<YYINITIAL> {ASTERISCO} {return new Symbol(sym.ASTERISCO, yyline, yycolumn,yytext());}


<YYINITIAL> {BLANCOS} {}