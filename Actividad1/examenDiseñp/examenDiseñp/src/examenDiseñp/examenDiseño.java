package examenDiseñp;
import java.util.*;

public class examenDiseño {

    // Constantes
    public static final int DIAS_SEMANA = 5;
    public static final int NUM_ESTUDIANTES = 4;

    // Datos de trabajo
    private static final char[][] asistencia = new char[NUM_ESTUDIANTES][DIAS_SEMANA];
    private static final String[] nombres = new String[NUM_ESTUDIANTES]; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        inicializarNombres(sc);           
        
        
        registrarAsistencia(sc);

        int opcion;
        do {
            opcion = menu(sc);
            switch (opcion) {
                case 1 -> verAsistenciaIndividual(sc);
                case 2 -> verResumenGeneral();
                case 3 -> registrarAsistencia(sc);   
                case 4 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
        sc.close();
    }

   
    private static void registrarAsistencia(Scanner sc) {
        System.out.println("\n== Registro de asistencia (P=presente, A=ausente) ==");
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            System.out.println("Estudiante: " + etiquetaEst(i));
            for (int d = 0; d < DIAS_SEMANA; d++) {
                asistencia[i][d] = leerPA(sc, "  Día " + (d + 1) + " (P/A): ");
            }
        }
        System.out.println("Registro completado.\n");
    }

    private static char leerPA(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().toUpperCase(Locale.ROOT);
            if (s.length() == 1 && (s.charAt(0) == 'P' || s.charAt(0) == 'A')) {
                return s.charAt(0);
            }
            System.out.println("  Valor inválido. Debe ser 'P' o 'A'.");
        }
    }

    private static int menu(Scanner sc) {
        System.out.println("""
            ===== Menú =====
            1. Ver asistencia individual
            2. Ver resumen general
            3. Volver a registrar
            4. Salir""");
        System.out.print("Elige una opción: ");
        String s = sc.nextLine().trim();
        try { return Integer.parseInt(s); } catch (NumberFormatException e) { return -1; }
    }

    
    private static void verAsistenciaIndividual(Scanner sc) {
        int idx = leerIndiceEstudiante(sc);
        int presentes = 0, ausentes = 0;
        System.out.println("\nAsistencia de " + etiquetaEst(idx) + ":");
        for (int d = 0; d < DIAS_SEMANA; d++) {
            char v = asistencia[idx][d];
            System.out.printf("  Día %d: %s%n", (d + 1), (v == 'P' ? "Presente" : "Ausente"));
            if (v == 'P') presentes++; else ausentes++;
        }
        System.out.printf("  Total presentes: %d / %d | Ausencias: %d%n%n",
                presentes, DIAS_SEMANA, ausentes);
    }

    private static int leerIndiceEstudiante(Scanner sc) {
        while (true) {
            System.out.println("Estudiantes:");
            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                System.out.printf("  %d) %s%n", (i + 1), etiquetaEst(i));
            }
            System.out.print("Selecciona (1-" + NUM_ESTUDIANTES + "): ");
            String s = sc.nextLine().trim();
            try {
                int x = Integer.parseInt(s);
                if (1 <= x && x <= NUM_ESTUDIANTES) return x - 1;
            } catch (NumberFormatException ignored) {}
            System.out.println("Selección inválida.\n");
        }
    }

    
    private static void verResumenGeneral() {
        System.out.println("\n== Resumen general ==");

        int[] totalPorEst = new int[NUM_ESTUDIANTES];
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            for (int d = 0; d < DIAS_SEMANA; d++) {
                if (asistencia[i][d] == 'P') totalPorEst[i]++;
            }
        }
        System.out.println("Totales por estudiante:");
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            System.out.printf("  %-15s: %d de %d%n", etiquetaEst(i), totalPorEst[i], DIAS_SEMANA);
        }
        
        
        
        System.out.print("Asistieron todos los días: ");
        boolean alguno = false;
        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
            if (totalPorEst[i] == DIAS_SEMANA) {
                System.out.print(etiquetaEst(i) + "  ");
                alguno = true;
            }
        }
        if (!alguno) System.out.print("(ninguno)");
        System.out.println();

        
        int[] ausenciasPorDia = new int[DIAS_SEMANA];
        for (int d = 0; d < DIAS_SEMANA; d++) {
            int aus = 0;
            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                if (asistencia[i][d] == 'A') aus++;
            }
            ausenciasPorDia[d] = aus;
        }
        int maxAus = Arrays.stream(ausenciasPorDia).max().orElse(0);
        System.out.print("Día(s) con más ausencias (" + maxAus + "): ");
        boolean first = true;
        for (int d = 0; d < DIAS_SEMANA; d++) {
            if (ausenciasPorDia[d] == maxAus) {
                System.out.print((first ? "" : ", ") + "Día " + (d + 1));
                first = false;
            }
        }
        System.out.println("\n");
    }

    
    private static void inicializarNombres(Scanner sc) {
        System.out.println("¿Deseas capturar nombres de estudiantes? (S/N)");
        String ans = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        if (ans.equals("S")) {
            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                System.out.print("Nombre del estudiante " + (i + 1) + ": ");
                String nom = sc.nextLine().trim();
                nombres[i] = nom.isEmpty() ? ("Est_" + (i + 1)) : nom;
            }
        } else {
            for (int i = 0; i < NUM_ESTUDIANTES; i++) nombres[i] = "Est_" + (i + 1);
        }
        System.out.println();
    }

    private static String etiquetaEst(int idx) {
        return (nombres[idx] == null || nombres[idx].isEmpty()) ? ("Est_" + (idx + 1)) : nombres[idx];
    }
}
