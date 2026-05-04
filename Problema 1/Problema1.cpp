#include <iostream>
#include <cmath>
#include <chrono>
#include <fstream>
#include <iomanip>

using namespace std;
using namespace std::chrono;

//  Daniel Villalba Santamaria

//  Problema 1: Dado numero entero n no negativo

//  a) Generar coeficientes y mostrar resultado del polinomio: (x+1)^n 
//  b) Mostrar paso a paso para x dado: f(x) = (x+1)^n
//  c) Medir tiempo y guardarlo en archivo.txt

int main() {
	
    int n;
    double x;

    cout << "Ingrese un entero 'n' no negativo: ";
    cin >> n;

    cout << "Ingrese el valor de x: ";
    cin >> x;

    auto start = high_resolution_clock::now();

    double* coeficiente = new double[n + 1];

    
    for (int i = 0; i <= n; i++) {
        coeficiente[i] = 0;
    }
    
    coeficiente[0] = 1;


    for (int i = 1; i <= n; i++) {
        for (int j = i; j > 0; j--) {
            coeficiente[j] = coeficiente[j] + coeficiente[j - 1];
        }
    }

    cout << fixed << setprecision(0); 
    cout << "\nResultado del Polinomio (x+1)^" << n << ": \n";
    
    for (int i = 0; i <= n; i++) {
    	
        int potencia = n - i;
		 
        if (coeficiente[i] != 0) {
            if (i > 0) cout << " + ";
            
            
            if (coeficiente[i] != 1 || potencia == 0) cout << coeficiente[i];
            
            if (potencia > 0) cout << "x";
            if (potencia > 1) cout << "^" << potencia;
        }
    }
    
    cout << "\n\n";

    cout << "Calculo paso a paso para f(x) = (x+1)^" << n << ", donde X = " << x << "\n\n";
    double resultadoFinal = 0;

    for (int i = 0; i <= n; i++) {
    	
        int potencia = n - i;
        double valorX = pow(x, potencia); 
        double termino = coeficiente[i] * valorX;

        cout << "Paso " << (i + 1) << " (grado " << potencia << "): " << coeficiente[i] << " * (" << x << "^" << potencia << ")";
        cout << ": " << coeficiente[i] << " * " << valorX << " = " << termino << "\n\n";
        
        resultadoFinal += termino;
    }

    cout << "Resultado de f(" << x << ") = " << resultadoFinal << "\n";

    auto stop = high_resolution_clock::now();
    
    auto duration = duration_cast<milliseconds>(stop - start);

    cout << "\nTiempo de ejecucion: " << duration.count() << " ms" << endl;


    ofstream archivo("resultados_c++.txt");
    if (archivo.is_open()) {
        archivo << "Resultado en C++\n";
        archivo << "Grado del polinomio n = " << n << "\n";
        archivo << "Valor de x = " << x << "\n";
        archivo << fixed << setprecision(0); 
        archivo << "Resultado de f(" << x << ") = " << resultadoFinal << "\n";
        archivo << "Tiempo: " << duration.count() << " ms \n";
        cout << "se guardo 'resultados_c++.txt'\n";
        archivo.close();
    } else {
        cout << "\nNo se pudo abrir o crear el archivo txt.\n";
    }

    delete[] coeficiente;

    return 0;
}