import java.util.*;

public class Lexer {

    private SymbolTable tablaSimbolos = new SymbolTable();

    private static final Set<String> reservadas = Set.of(
            "pf2025","decl","inicio","end",
            "impdig","impcad","leerdig"
    );

    private static final Set<String> tipos = Set.of(
            "int","cad","booleano"
    );

    public List<Token> analizar(List<String> lineas) {

        List<Token> tokens = new ArrayList<>();

        int numLinea = 1;

        for(String linea : lineas){
            linea = linea
                    .replace(";", " ; ")
                    .replace(",", " , ")
                    .replace("(", " ( ")
                    .replace(")", " ) ")
                    .replace("+", " + ")
                    .replace("-", " - ")
                    .replace("*", " * ")
                    .replace("/", " / ")
                    .replace(":=", " := ");

    String[] palabras = linea.split("\\s+");
            

            for(String palabra : palabras){

                if(palabra.isEmpty())
                    continue;

                Token token = reconocerToken(palabra,numLinea);

                tokens.add(token);
            }

            numLinea++;
        }

        tokens.add(new Token(TokenType.EOF,"EOF",numLinea));

        return tokens;
    }

   private Token reconocerToken(String lexema,int linea){

    if(lexema.equals("pf2025"))
        return new Token(TokenType.PROG, lexema, linea);

    if(lexema.equals("decl"))
        return new Token(TokenType.DECL, lexema, linea);

    if(lexema.equals("inicio"))
        return new Token(TokenType.INICIO, lexema, linea);

    if(lexema.equals("end"))
        return new Token(TokenType.END, lexema, linea);

    if(lexema.equals("impdig"))
        return new Token(TokenType.IMPDIG, lexema, linea);

    if(lexema.equals("impcad"))
        return new Token(TokenType.IMPCAD, lexema, linea);

    if(lexema.equals("leerdig"))
        return new Token(TokenType.LEERDIG, lexema, linea);

    if(tipos.contains(lexema))
        return new Token(TokenType.TIPO, lexema, linea);

    if(lexema.matches("[0-9]+"))
        return new Token(TokenType.CENT, lexema, linea);

    if(lexema.matches("[a-zA-Z][a-zA-Z0-9]*")){
        tablaSimbolos.agregar(lexema);
        return new Token(TokenType.ID, lexema, linea);
    }
    switch(lexema){

    case "+":
        return new Token(TokenType.MAS,lexema,linea);

    case "-":
        return new Token(TokenType.MENOS,lexema,linea);

    case "*":
        return new Token(TokenType.MUL,lexema,linea);

    case "/":
        return new Token(TokenType.DIV,lexema,linea);

    case ":=":
        return new Token(TokenType.ASIG,lexema,linea);

    case ";":
        return new Token(TokenType.PC,lexema,linea);

    case ",":
        return new Token(TokenType.COMA,lexema,linea);

    case "(":
        return new Token(TokenType.PAREN,lexema,linea);

    case ")":
        return new Token(TokenType.TESIS,lexema,linea);
}

    return new Token(TokenType.ERROR, lexema, linea);
}
}