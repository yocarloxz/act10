import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class ValidadorConcurrencia extends Thread {
    private String contraseña;
    private static final String ARCHIVO_REGISTRO = "registro_validaciones.txt";

    public ValidadorConcurrencia(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public void run() {
        // Expresión regular para validar la contraseña
        String regex = "^(?=.*[A-Z].*[A-Z])(?=.*[a-z].*[a-z].*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        boolean esValida = pattern.matcher(contraseña).matches();

        String resultado = esValida ?
                "La contraseña \"" + contraseña + "\" es válida." :
                "La contraseña \"" + contraseña + "\" no es válida.";

        // Imprimir el resultado en consola
        System.out.println(resultado);

        // Escribir el resultado en el archivo de registro
        escribirEnArchivo(resultado);
    }

    private void escribirEnArchivo(String resultado) {
        // Usar una expresión Lambda para escribir en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_REGISTRO, true))) {
            writer.write(resultado);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de registro: " + e.getMessage());
        }
    }
}
