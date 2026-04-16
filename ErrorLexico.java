public class ErrorLexico {

    private String mensaje;
    private int linea;

    public ErrorLexico(String mensaje, int linea) {
        this.mensaje = mensaje;
        this.linea = linea;
    }

    public void mostrarError() {
        System.out.println("Error léxico en línea " + linea + ": " + mensaje);
    }
}