public class Ejercicio7_37Prueba {

    public static void main(String[] args) {
        if (args.length > 0) {
            for (String s : args) {
                if ("-a".equals(s)) {
                    mensajeDeAyuda();
                    System.exit(0);
                }
            }
            System.out.println("Opcion invalida, use \"-a\" para ayuda.");
            System.exit(0);
        }
        else {
            correr();
        }
    }
    public static void correr() {
        Ejercicio7_37 s = new Ejercicio7_37();
        s.correr();
    }

    public static void mensajeDeAyuda() {
        System.out.println("Mensaje de ayuda de prueba");
    }
}