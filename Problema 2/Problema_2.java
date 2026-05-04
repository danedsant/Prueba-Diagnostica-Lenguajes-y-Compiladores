import java.util.Scanner;

// Daniel Villalba Santamaria

//  Problema 2: Dada una cadena, validar si esta en notacion FEN (Forsyth-Edwards Notation)

public class Problema_2 {
    public static void main(String[] args) {

        boolean esValido = true;

        Scanner scanner = new Scanner(System.in);        
        System.out.print("Ingresa una cadena para validar si esta en notacion FEN: ");
        String entradaUsuario = scanner.nextLine();

        String[] FEN = entradaUsuario.split(" ");

        if (FEN.length == 6) {

            String campoPiezas      = FEN[0];
            String campoTurno       = FEN[1];
            String campoEnroque     = FEN[2];
            String campoPeonPaso    = FEN[3];
            String campoReloj       = FEN[4];
            String campoMovimientos = FEN[5];

            
            String[] filas = campoPiezas.split("/");

            if (filas.length == 8) {

                for (String fila : filas) {

                    int casillas = 0;
                    
                    for (char c : fila.toCharArray()) {
                        
                        if (c >= '1' && c <= '8') {
                            casillas += Character.getNumericValue(c); 

                        } else if (String.valueOf(c).matches("[pnbrqkPNBRQK]")) {
                            casillas += 1;

                        } else {
                            esValido = false;
                        }
                    }
                    
                    if (casillas != 8) {
                        System.out.println("Error: Una fila no suma 8 casillas.");
                        esValido = false;
                    }
                }
            } else {
                System.out.println("Error: No hay 8 filas en el tablero.");
                esValido = false;
            }

            // Validacion del Campo Turno
            if (!campoTurno.equals("w") && !campoTurno.equals("b")) {
                System.out.println("Error: Notacion Invalida en el campo de turno.");
                esValido = false;
            }

            // Validacion del campo Enroque
            if (!campoEnroque.equals("-") && !campoEnroque.matches("[KQkq]+")) {
                System.out.println("Error: Notacion Invalida en el campo de Enroque.");
                esValido = false;
            }

            // Validacion del Campo de Peon al Paso
            if (!campoPeonPaso.equals("-") && !campoPeonPaso.matches("[a-h][36]")) {
                System.out.println("Error: Notacion Invalida en el campo de Peon al Paso.");
                esValido = false;
            }

            // Validacion del Campo Reloj y Campo de Movimientos
            if (!campoReloj.matches("[0-9]+") || !campoMovimientos.matches("[1-9][0-9]*")) {

                System.out.println("Error: Notacion Invalida en los campos de reloj o movimientos.");
                esValido = false;
            }

        } else {

            System.out.println("Error: La cadena no tiene los 6 campos requeridos.");
            esValido = false;
        }

        
        if (esValido) {
            System.out.println("En efecto, la cadena ingresada SI es una notacion FEN valida.");
        } else {
            System.out.println("La cadena ingresada NO es una FEN valida.");
        }
        
        scanner.close();
    }
}


