//Realiza el análisis léxico, transformando el código fuente
//en una lista de tokens
import java.util.*;

public class Lexer {
    //se usa para registrar identificadores
    private SymbolTable tablaSimbolos = new SymbolTable();

    private static final Set<String> tipos = Set.of(
            "int", "cad", "booleano"
    );
    //este metodo recorre el programa fuente, identifica tokens y maneja los comentarios
    public List<Token> analizar(List<String> lineas) {

        List<Token> tokens = new ArrayList<>();
        int numLinea = 1;
        boolean enComentario = false;

        for (String linea : lineas) {

            // Manejo de comentarios /* */
            if (linea.contains("/*")) {
                enComentario = true;
            }

            if (enComentario) {
                if (linea.contains("*/")) {
                    enComentario = false;
                }
                numLinea++;
                continue;
            }

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

            for (String palabra : palabras) {

                if (palabra.isEmpty())
                    continue;

                Token token = reconocerToken(palabra, numLinea);

                if (token.getTipo() == TokenType.ERROR) {
                    ErrorLexico error = new ErrorLexico(
                            "Símbolo no reconocido: " + palabra,
                            numLinea
                    );
                    error.mostrarError();
                    System.exit(1);
                }

                tokens.add(token);
            }

            numLinea++;
        }

        tokens.add(new Token(TokenType.EOF, "EOF", numLinea));
        return tokens;
    }
    //este método clasifica cada lexema según el tipo
    private Token reconocerToken(String lexema, int linea) {

        switch (lexema) {

            case "pf2025":
                return new Token(TokenType.PROG, lexema, linea);

            case "decl":
                return new Token(TokenType.DECL, lexema, linea);

            case "inicio":
                return new Token(TokenType.INICIO, lexema, linea);

            case "end":
                return new Token(TokenType.END, lexema, linea);

            case "impdig":
                return new Token(TokenType.IMPDIG, lexema, linea);

            case "impcad":
                return new Token(TokenType.IMPCAD, lexema, linea);

            case "leerdig":
                return new Token(TokenType.LEERDIG, lexema, linea);

            case "+":
                return new Token(TokenType.MAS, lexema, linea);

            case "-":
                return new Token(TokenType.MENOS, lexema, linea);

            case "*":
                return new Token(TokenType.MUL, lexema, linea);

            case "/":
                return new Token(TokenType.DIV, lexema, linea);

            case ":=":
                return new Token(TokenType.ASIG, lexema, linea);

            case ";":
                return new Token(TokenType.PC, lexema, linea);

            case ",":
                return new Token(TokenType.COMA, lexema, linea);

            case "(":
                return new Token(TokenType.PAREN, lexema, linea);

            case ")":
                return new Token(TokenType.TESIS, lexema, linea);
        }

        if (tipos.contains(lexema))
            return new Token(TokenType.TIPO, lexema, linea);

        if (lexema.matches("[0-9]+"))
            return new Token(TokenType.CENT, lexema, linea);

        if (lexema.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            tablaSimbolos.agregar(lexema);
            return new Token(TokenType.ID, lexema, linea);
        }

        return new Token(TokenType.ERROR, lexema, linea);
    }
    //devuelve la tabla de símbolos construida
    public SymbolTable getTablaSimbolos() {
        return tablaSimbolos;
    }
}