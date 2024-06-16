package analisis;

//importaciones
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import excepciones.Errores;

%%

//codigo de usuario
%{
public LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
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

BLANCOS=[\ \r\t\f\n]+
ENTERO=[0-9]+
DECIMAL=[0-9]+"."[0-9]+
CADENA = [\"]((\\n)|(\\\")|(\\\\)|(\\t)|(\\\')|([^\"]))*[\"]
BOOLEANO = ((true)|(false)) 
CARACTER= ('[^\ \r\t\f\n]')
ID=[a-zA-z][a-zA-Z0-9_]*


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
<YYINITIAL> "println"   {return new Symbol(sym.IMPRIMIR, yyline, yycolumn,yytext());}
<YYINITIAL> "int"       {return new Symbol(sym.INT, yyline, yycolumn,yytext());}
<YYINITIAL> "double"    {return new Symbol(sym.DOUBLE, yyline, yycolumn,yytext());}
<YYINITIAL> "bool"      {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}
<YYINITIAL> "char"      {return new Symbol(sym.CHAR, yyline, yycolumn,yytext());}
<YYINITIAL> "String"    {return new Symbol(sym.STRINGG, yyline, yycolumn,yytext());}
<YYINITIAL> "var"       {return new Symbol(sym.VAR, yyline, yycolumn,yytext());}
<YYINITIAL> "const"     {return new Symbol(sym.CONST, yyline, yycolumn,yytext());}
<YYINITIAL> "else"      {return new Symbol(sym.ELSE, yyline, yycolumn,yytext());}
<YYINITIAL> "if"        {return new Symbol(sym.IF, yyline, yycolumn,yytext());}
<YYINITIAL> "for"       {return new Symbol(sym.FOR, yyline, yycolumn,yytext());}
<YYINITIAL> "while"     {return new Symbol(sym.WHILE, yyline, yycolumn,yytext());}
<YYINITIAL> "do"        {return new Symbol(sym.DO, yyline, yycolumn,yytext());}
<YYINITIAL> "break"     {return new Symbol(sym.BREAK, yyline, yycolumn,yytext());}



//Estructura de las variables
<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOLEANO} {return new Symbol(sym.BOOLEANO, yyline, yycolumn,yytext());}


//Simbolos del sistema
<YYINITIAL> ";"     {return new Symbol(sym.FINCADENA, yyline, yycolumn,yytext());}
<YYINITIAL> ":"     {return new Symbol(sym.DOSPUNTOS, yyline, yycolumn,yytext());}
<YYINITIAL> "("     {return new Symbol(sym.PAR1, yyline, yycolumn,yytext());}
<YYINITIAL> ")"     {return new Symbol(sym.PAR2, yyline, yycolumn,yytext());}
<YYINITIAL> "++"    {return new Symbol(sym.MAS_MAS, yyline, yycolumn,yytext());}
<YYINITIAL> "+"     {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> "--"    {return new Symbol(sym.MENOS_MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> "-"     {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> "/"     {return new Symbol(sym.DIVISION, yyline, yycolumn,yytext());}
<YYINITIAL> "%"     {return new Symbol(sym.PORCENTUAL, yyline, yycolumn,yytext());}
<YYINITIAL> "**"    {return new Symbol(sym.DOBLE_ASTERISCO, yyline, yycolumn,yytext());}
<YYINITIAL> "*"     {return new Symbol(sym.ASTERISCO, yyline, yycolumn,yytext());}
<YYINITIAL> "=="    {return new Symbol(sym.IGUAL_IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> "="     {return new Symbol(sym.IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> "<="    {return new Symbol(sym.MENOR_IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> "<"     {return new Symbol(sym.MENOR, yyline, yycolumn,yytext());}
<YYINITIAL> ">="    {return new Symbol(sym.MAYOR_IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> ">"     {return new Symbol(sym.MAYOR, yyline, yycolumn,yytext());}
<YYINITIAL> "!="    {return new Symbol(sym.NOT_IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> "!"     {return new Symbol(sym.NOT, yyline, yycolumn,yytext());}
<YYINITIAL> "||"    {return new Symbol(sym.OR, yyline, yycolumn,yytext());}
<YYINITIAL> "&&"    {return new Symbol(sym.AND, yyline, yycolumn,yytext());}
<YYINITIAL> "^"     {return new Symbol(sym.XOR, yyline, yycolumn,yytext());}
<YYINITIAL> "{"     {return new Symbol(sym.LLAVE1, yyline, yycolumn,yytext());}
<YYINITIAL> "}"     {return new Symbol(sym.LLAVE2, yyline, yycolumn,yytext());}


<YYINITIAL> {ID} {return new Symbol(sym.ID, yyline, yycolumn,yytext());}

<YYINITIAL> {CARACTER} {
    String caracter = yytext();
    caracter = caracter.substring(1, caracter.length()-1);
    return new Symbol(sym.CARACTER, yyline, yycolumn,caracter);
    }

<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);

    }

//Ignorar Espacios en blanco
<YYINITIAL> {BLANCOS} {}

<YYINITIAL> . {
                listaErrores.add(new Errores("LEXICO","El caracter "+
                yytext()+" NO pertenece al lenguaje", yyline, yycolumn));
}