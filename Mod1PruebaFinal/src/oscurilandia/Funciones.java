package oscurilandia;

import java.util.Scanner;

public class Funciones {

	// Verifica que el n�mero ingresado por el usuario sea un n�mero v�lido

    public static int pedirNumero(Scanner scanner) {
        int entero;
        
        while(!scanner.hasNextInt()) {
            System.err.print("Por favor, ingrese un n�mero v�lido: ");
            scanner.next();
        }
        
        entero = scanner.nextInt();
        
        if(scanner.hasNextLine())
            scanner.nextLine();

        return entero;
    }
    
    // Verifica que el n�mero ingresado por el usuario sea un n�mero v�lido y que sea mayor o igual al l�mite inferior

    public static int pedirNumero(Scanner scanner, int limiteInferior) {
        int entero;
        
        do {
            entero = pedirNumero(scanner);
            
            if(entero < limiteInferior)
                System.err.print("Por favor, ingrese un n�mero mayor o igual a " + 
                        limiteInferior + ": ");
        } while(entero < limiteInferior);
        
        return entero;
    }
    
    // Verifica que el n�mero ingresado por el usuario sea un n�mero v�lido y se encuentre entre
    // los valores limiteInferior y limiteSuperior (incluidos)

    public static int pedirNumero(Scanner scanner, int limiteInferior, int limiteSuperior) {
        int entero;
        
        do {
            entero = pedirNumero(scanner);
            
            if(entero < limiteInferior || entero > limiteSuperior)
                System.err.print("Por favor, ingrese un n�mero entre " + limiteInferior + 
                        " y " + limiteSuperior + ": ");
        } while(entero < limiteInferior || entero > limiteSuperior);
        
        return entero;
    }
    
}