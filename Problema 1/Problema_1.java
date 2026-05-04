
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.DecimalFormat;

// Daniel Villalba Santamaria

//  Problema 1: Dado numero entero n no negativo
//  a) Generar coeficientes y mostrar resultado del polinomio: (x+1)^n 
//  b) Mostrar paso a paso para x dado: f(x) = (x+1)^n
//  c) Medir tiempo y guardarlo en archivo.txt

public class Problema_1 {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Ingrese un entero 'n' no negativo: ");
        int n = sc.nextInt();

        System.out.print("Ingrese el valor de x: ");
        double x = sc.nextDouble();

        long start = System.nanoTime();

        double[] coeficiente = new double[n + 1];

        for (int i = 0; i <= n; i++) {
            coeficiente[i] = 0;
        }
        
        coeficiente[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = i; j > 0; j--) {
                coeficiente[j] = coeficiente[j] + coeficiente[j - 1];
            }
        }

        DecimalFormat df = new DecimalFormat("#");

        System.out.println("\nResultado del Polinomio (x+1)^" + n + ": \n");
        
        for (int i = 0; i <= n; i++) {
            int potencia = n - i; 
            if (coeficiente[i] != 0) {
                if (i > 0) System.out.print(" + ");
                
                if (coeficiente[i] != 1 || potencia == 0) System.out.print(df.format(coeficiente[i]));
                
                if (potencia > 0) System.out.print("x");
                if (potencia > 1) System.out.print("^" + potencia);
            }
        }
        
        System.out.println("\n\n");


        System.out.println("Calculo paso a paso para f(x) = (x+1)^" + n + ", donde X = " + df.format(x) + "\n");
        double resultadoFinal = 0;

        for (int i = 0; i <= n; i++) {

            int potencia = n - i;
            double valorX = Math.pow(x, potencia);
            double termino = coeficiente[i] * valorX; 

            System.out.print("Paso " + (i + 1) + " (grado " + potencia + "): " + df.format(coeficiente[i]) + " * (" + df.format(x) + "^" + potencia + ")");
            
            System.out.println(": " + df.format(coeficiente[i]) + " * " + df.format(valorX) + " = " + df.format(termino) + "\n");
            
            resultadoFinal += termino;
        }

        System.out.println("Resultado de f(" + df.format(x) + ") = " + df.format(resultadoFinal));

        long stop = System.nanoTime();

        long duracion = (stop - start) / 1000000; 

        System.out.println("\nTiempo de ejecucion: " + duracion + " ms");

        try (PrintWriter archivo = new PrintWriter(new FileWriter("resultados_java.txt"))) {
            archivo.println("Resultado en Java");
            archivo.println("Grado del polinomio n = " + n);
            archivo.println("Valor de x = " + df.format(x));
            archivo.println("Resultado de f(" + df.format(x) + ") = " + df.format(resultadoFinal));
            archivo.println("Tiempo: " + duracion + " ms ");
            
            System.out.println("se guardo 'resultados_java.txt'");
        } catch (IOException e) {
            System.out.println("\nNo se pudo abrir o crear el archivo.txt");
        }

        sc.close();
        
    }
}

