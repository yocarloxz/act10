import java.util.Scanner;

public class AplicacionConcurrencia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese una contraseña para validar (o 'salir' para terminar): ");
            String contraseña = scanner.nextLine();

            if (contraseña.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            // Crear y iniciar un nuevo hilo para validar la contraseña
            ValidadorConcurrencia validador = new ValidadorConcurrencia(contraseña);
            validador.start();

            // Opcional: Esperar a que el hilo termine antes de solicitar otra entrada
            try {
                validador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
