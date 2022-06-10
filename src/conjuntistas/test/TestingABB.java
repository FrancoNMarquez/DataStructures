package conjuntistas.test;

import conjuntistas.dinamicas.ArbolBB;

public class TestingABB {
    static String sOk = "\u001B[32m OK! \u001B[0m";
    static String sErr = " \u001B[31m ERROR \u001B[0m";
    public static final String NEGRO = "\u001B[30m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLANCO = "\u001B[37m";

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String RESET = "\u001B[0m";

    public static void main(String args[]) {
        System.out.println(ANSI_CYAN_BACKGROUND + ROJO + "**************************************************************");
        System.out.println(ANSI_CYAN_BACKGROUND + ROJO + "*                  Test Arbol Binario de Busqueda                       *");
        System.out.println(ANSI_CYAN_BACKGROUND + ROJO + "**************************************************************" + "\n\n" + RESET);
        ArbolBB a = new ArbolBB();
        ArbolBB b = new ArbolBB();
        System.out.println(ANSI_YELLOW_BACKGROUND + "--------------------------------------------------------------------------------------------------------"
                + "------------------------------------------------------------------------------------------" + RESET);
        System.out.println("\n\n");

        System.out.println("****************************************");
        System.out.println("*      Pruebas sobre arbol vacio       *");
        System.out.println("****************************************");
        System.out.println("Checkeo si es vacio " + ((a.esVacio()) ? sOk : sErr));
        System.out.println("Listar vacio: " + a.listar().toString());
        System.out.println("Intento vaciar arbol vacio ");
        a.vaciar();
        System.out.println("toString de arbol vacio: " + a.toString());
        System.out.println();
        System.out.println();
        System.out.println("****************************************");
        System.out.println("*          Insercion - altura          *");
        System.out.println("****************************************");
        System.out.println("Inserto el 10 en raiz " + ((a.insertar(10)) ? sOk : sErr));
        System.out.println("Inserto el 9 " + ((a.insertar(9)) ? sOk : sErr));
        System.out.println("Inserto el 7 " + ((a.insertar(7)) ? sOk : sErr));
        System.out.println("Inserto el 3 " + ((a.insertar(3)) ? sOk : sErr));
        System.out.println("Inserto el 15 " + ((a.insertar(15)) ? sOk : sErr));
        System.out.println("Inserto el 12 " + ((a.insertar(12)) ? sOk : sErr));
        System.out.println("Inserto el 20 " + ((a.insertar(20)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
                + "            10 \n"
                + "        /       \\    \n"
                + "      9           15  \n"
                + "   /           /    \\ \n"
                + " 7            12      20 \n"
                + " /                   \n"
                + "3                          \n");
        System.out.println("" + a.toString() + "\n\n");
        System.out.println("\n\n");
        System.out.println(ANSI_YELLOW_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);

        System.out.println("\n\n********************************");
        System.out.println("*      Test de clone           *");
        System.out.println("********************************\n");
        b = a.clone();
        System.out.println("" + b.toString() + "\n\n");
        System.out.println("Inserto el 25 en CLON" + ((b.insertar(25)) ? sOk : sErr));
        System.out.println("Inserto el 35 en CLON" + ((b.insertar(35)) ? sOk : sErr));
        System.out.println("\n clon toString()  deberia dar: \n"
                + "            10 \n"
                + "        /       \\    \n"
                + "      9           15  \n"
                + "   /            /    \\ \n"
                + "  7            12      20 \n"
                + " /                        \\\n"
                + "3                           25\n"
                + "                              \\\n"
                + "                               35\n"
                + "\n");
        System.out.println("" + b.toString() + "\n\n");
        System.out.println(VERDE + "ORIGINAL toString() - verifica que tenga igual estructura que antes \t\t\t ");
        System.out.println("\n toString()  deberia dar: \n"
                + "            10 \n"
                + "        /       \\    \n"
                + "      9           15  \n"
                + "   /           /    \\ \n"
                + " 7            12      20 \n"
                + " /                   \n"
                + "3                          \n");
        System.out.println("" + a.toString() + "\n\n");
        System.out.println("Vacio el CLON");
        b.vaciar();
        System.out.println("toString de arbol vacio (CLON)");
        System.out.println("" + b.toString() + "\n\n");
        System.out.println("\n\n");
        System.out.println(ANSI_YELLOW_BACKGROUND + "----------------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------" + RESET);
        System.out.println("\n\n**********************************");
        System.out.println("*      Test de Recorridos        *");
        System.out.println("**********************************\n");
        System.out.println("Listar (en Preorden).\n" + a.listar().toString());
        System.out.println("\n");
        System.out.println("Listar por rango.\n" + a.listarRango(7, 15).toString());
        System.out.println("\n\n");
        System.out.println(ANSI_YELLOW_BACKGROUND + "----------------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------" + RESET);
        System.out.println("\n\n**********************************");
        System.out.println("*      Test de Eliminar            *");
        System.out.println("**********************************\n");
        System.out.println("Eliminar el 7 " + ((a.eliminar(7)) ? sOk : sErr));
        System.out.println("Eliminar el 3 " + ((a.eliminar(3)) ? sOk : sErr));
        System.out.println("Eliminar el 15 " + ((a.eliminar(15)) ? sOk : sErr));
    }

}

