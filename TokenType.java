public enum TokenType {

    // Palabras reservadas
    PROG,       // pf2025
    DECL,       // decl
    INICIO,     // inicio
    END,        // end
    IMPDIG,
    IMPCAD,
    LEERDIG,

    // Tipos
    TIPO,       // int, cad, booleano

    // Operadores
    MAS,        // +
    MENOS,      // -
    MUL,        // *
    DIV,        // /
    ASIG,       // :=
    IGUAL,      // =

    // Signos
    PC,         // ;
    COMA,       // ,
    PAREN,      // (
    TESIS,      // )

    // Otros
    ID,
    CENT,

    EOF,
    ERROR
}