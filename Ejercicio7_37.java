//7.37 (Simulador de computadora) a En este problema usted creará su propia computadora. 
//     No, no soldará componentes, sino que utilizará la poderosa técnica de la simulación 
//     basada en softwarepara crear un e modelo de softwareorientae do a objetos de Simpletron, 
//     la computadora del ejercicio 7.36. Su simulador Simpletron convertirá la computadora que 
//     usted utiliza en Simpletron, y será capaz de ejecutar, probar y depurar los programas LMS 
//     que escribió en el ejercicio 7.36. 

import java.util.Scanner;

public class Ejercicio7_37 extends CodigosDeOperacionLMS 
{
    private int[] memoria = new int[100];
    private int acumulador;
    private int contadorDeInstrucciones;
    private int registroDeInstruccion;
    private int codigoDeOperacion;
    private int operando;
    private boolean correr = true;
    
    public void correr() 
    {
        mensajeDeBienvenida();
        ejecutar();
    }

    private void mensajeDeBienvenida() 
    {
        System.out.println("\n***                 Bienvenido a Simpletron!                ***");
        System.out.println("*** Por favor, introduzca en su programa una instruccion    ***");
        System.out.println("*** (o palabra de datos) a la vez. Yo le mostrare           ***");
        System.out.println("*** el numero de ubicacion y un signo de interrogacion (?)  ***");
        System.out.println("*** Entonces usted escribira la palabra para esa ubicacion. ***");
        System.out.println("*** Teclee -9999 para dejar de introducir su programa.      ***\n");
    }

    private void ejecutar() 
    {
        Scanner introductorDeCodigo = new Scanner(System.in);
        int entradaDeInstruccion = 0;
        int punteroDeMemoria = 0;

        do 
        {
            System.out.printf("%02d ? ", punteroDeMemoria);
            entradaDeInstruccion = introductorDeCodigo.nextInt();
            memoria[punteroDeMemoria] = entradaDeInstruccion;
            punteroDeMemoria++;
        }

        while (entradaDeInstruccion != -9999);

        System.out.printf("\n%s\n%s\n\n", "*** Se completo la carga del programa ***", "*** Empieza la ejecucion del programa ***");

        while (correr) 
        {
            codigoDeCarga();
            operaciones(codigoDeOperacion, operando);
        }

        System.exit(0);
    }

    // Se determina que operación se ejecuta y a que ubicación de memoria 
    // necesita acceder para completar esa operación.
    private void codigoDeCarga() 
    {
        registroDeInstruccion = memoria[contadorDeInstrucciones];
        
        codigoDeOperacion = registroDeInstruccion / 100;
        operando = registroDeInstruccion % 100;
    }

    private void operaciones(int codigoDeOperacion, int operando) 
    {
        boolean bif = false;

        switch (codigoDeOperacion) 
        { 
            case LEE:
            Scanner leer = new Scanner(System.in);
            System.out.print("Escriba un entero: ");
            int numero = leer.nextInt();
            memoria[operando] = numero;
            break;
 
            case ESCRIBE:
            System.out.println(memoria[operando]);
            break;

            case CARGA:
            acumulador = memoria[operando];
            break;

            case ALMACENA:
            memoria[operando] = acumulador;
            break;
            
            case SUMA:
            acumulador += memoria[operando];
            break;

            case RESTA:
            acumulador -= memoria[operando];
            break;

            case DIVIDE:
            if (memoria[operando] == 0) 
            {
                System.out.printf("\n%s\n%s\n", "*** Intento de dividir entre cero ***", "*** La ejecucion de Simpletron se termino en forma anormal ***");
                System.exit(-1);
            }
            else 
            {
                acumulador /= memoria[operando];
                break;
            }

            case MULTIPLICA:
            acumulador *= memoria[operando];
            break;


            case BIFURCA:
            contadorDeInstrucciones = operando;
            bif = true;
            break;


            case BIFURCANEG:
            if (acumulador < 0) 
            {
                contadorDeInstrucciones = operando;
                bif = true;
            }
            break;


            case BIFURCACERO:
            if (acumulador == 0) 
            {
                contadorDeInstrucciones = operando;
                bif = true;
            }
            break;

            case ALTO:
            System.out.println("\n*** Termino la ejecucion de Simpletron ***\n");
            correr = false;
            vaciadoDeMemoria();
            break;

        } 

        if (!bif) 
        {
            contadorDeInstrucciones++;
        }
    } 

    private void vaciadoDeMemoria() 
    {

        int decenas, unidades;
        System.out.println("REGISTROS:");
        System.out.printf("acumulador\t\t\t+%04d\n",acumulador);
        System.out.printf("contadorDeInstrucciones\t\t%02d\n",contadorDeInstrucciones);
        System.out.printf("registroDeInstruccion\t\t+%04d\n",registroDeInstruccion);
        System.out.printf("codigoDeOperacion\t\t%02d\n",codigoDeOperacion);
        System.out.printf("operando\t\t\t%02d\n",operando);
        System.out.println("\nMEMORIA:");
        System.out.printf("\t   %02d\t   %02d\t   %02d\t   %02d\t   %02d\t   %02d\t   %02d\t   %02d\t   %02d\t   %02d\n", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        for (decenas = 0; decenas < 100; decenas += 10) 
        {
            System.out.printf("%02d\t", decenas);
            for (unidades = 0; unidades < 10; unidades++) 
            {
                System.out.printf("+%04d\t", memoria[decenas + unidades]);
            }
            System.out.println();
        }
    }
}