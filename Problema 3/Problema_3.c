#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Daniel Villalba Santamaria 27.506.542

// Problema 3: Detectar palabras reservadas en un programa en C y traducirlas al español

struct palabrasReservadas{
	char palabra[20];
	char traduccion[20];
};

struct palabrasReservadas lista[] = {
 {"int", "entero"},
 {"float", "flotante"},
 {"while", "mientras" },
 {"return","retornar"},
 {"if","si"},
 {"break","romper"},
 {"long","largo"},
 {"short","corto"},
 {"void","vacio"},
 {"double","doble precision"},
 {"char","caracter"},
 {"case","caso"},
 {"default","por defecto"},
 {"do","hacer"},
 {"for","para"},
 {"goto","ir a"},
 {"switch","cambiar"},
 {"auto","automatico"},
 {"const","constante"},
 {"extern","externo"},
 {"static","estatico"},
 {"volatile","volatil"},
 {"enum","enumeracion"},
 {"sizeof","tamaño de"},
 {"struct","estructura"},
 {"typedef","definir tipo"},
 {"union","union"},
 {"unsigned","sin signo"},
 {"else","si no"},
 {"continue","continuar"},
 {"register","registro"},
};

int main() {
	
    FILE *archivo = fopen("codigo.c", "r");
    
    if(archivo == NULL){
		printf("No se pudo abrir el codigo con el programa en C \n");
		return 1;
	}
	
	fseek(archivo, 0, SEEK_END);
	
	long tamano_archivo = ftell(archivo);
	
	rewind(archivo);
	
	
	char *codigoMemoria = malloc((tamano_archivo + 1) * sizeof(char));
	
	
	fread(codigoMemoria, sizeof(char), tamano_archivo, archivo);
	
	codigoMemoria[tamano_archivo] = '\0'; 
	
	fclose(archivo);
	
	char *puntero = codigoMemoria;
	
	char buffer[50];
	
	int cantidadPalabras = sizeof(lista) / sizeof(lista[0]);

    
    while (*puntero != '\0') {
        
        if (isalpha(*puntero)) {
            int i = 0;
            
            
            while (isalpha(*puntero)) {
                buffer[i] = *puntero;
                i++;
                puntero++;
            }
            buffer[i] = '\0'; 
            
            
            for (int j = 0; j < cantidadPalabras; j++) {
                if (strcmp(buffer, lista[j].palabra) == 0) {
                    printf("El programa tiene la palabra: %s\n", lista[j].traduccion);
                }
            }
        } 
        else {
            puntero++;
        }
    }

    free(codigoMemoria);
    return 0;
}

