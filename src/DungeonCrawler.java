import java.util.Scanner;

/**
 * Clase Principal DungeonCrawler - Juego de Mazmorras para Taller de
 * Programación
 * 
 * TEMAS DEL SÍLABO APLICADOS EN ESTA CLASE:
 * - Clase Scanner para entrada de datos
 * - System.out.printf para formato de salida
 * - Bucle do-while principal del juego
 * - Estructuras if-else y switch-case
 * - Try-catch para manejo de excepciones
 * - Validación con métodos de String
 * 
 * @author Generado para curso de Taller de Programación
 */
public class DungeonCrawler {

    // ==========================================
    // VARIABLES GLOBALES DEL JUEGO
    // ==========================================
    private static Scanner scanner;
    private static Jugador jugador;
    private static int habitacionActual;
    private static int totalHabitaciones;
    private static boolean juegoActivo;
    private static boolean juegoGanado;

    /**
     * Método principal - Punto de entrada del programa
     */
    public static void main(String[] args) {
        // Inicializar Scanner para entrada de usuario
        scanner = new Scanner(System.in);
        totalHabitaciones = 10;
        habitacionActual = 0;
        juegoActivo = true;
        juegoGanado = false;

        // Mostrar pantalla de bienvenida
        mostrarBienvenida();

        // ==========================================
        // USO DE SCANNER: Pedir nombre del jugador
        // ==========================================
        System.out.print("Ingresa el nombre de tu heroe: ");
        String nombreJugador = scanner.nextLine();

        // Validar que el nombre no esté vacío usando métodos de String
        while (nombreJugador.trim().isEmpty()) {
            System.out.print("[!] El nombre no puede estar vacio. Ingresa tu nombre: ");
            nombreJugador = scanner.nextLine();
        }

        // Crear jugador con el nombre ingresado
        jugador = new Jugador(nombreJugador);

        // ==========================================
        // USO DE System.out.printf: Dar bienvenida con formato
        // ==========================================
        System.out.println("\n==========================================================");
        System.out.printf("  Bienvenido, valiente %s!%n", jugador.getNombre());
        System.out.printf("  Tu mision: Explorar %d habitaciones y derrotar al jefe final.%n", totalHabitaciones);
        System.out.printf("  Vida inicial: %d | Pociones: %d | Oro: %d%n",
                jugador.getVida(), jugador.getPociones(), jugador.getOro());
        System.out.println("==========================================================\n");

        pausar();

        // ==========================================
        // BUCLE DO-WHILE: Mantiene el juego activo
        // El juego continúa hasta que:
        // - La vida llegue a 0 (derrota)
        // - El jugador gane (derrote al jefe)
        // - El jugador elija salir
        // ==========================================
        do {
            // Mostrar menú principal
            mostrarMenuPrincipal();

            // Obtener opción del usuario con manejo de errores
            int opcion = obtenerOpcionUsuario();

            // ==========================================
            // SWITCH-CASE: Procesar comandos del menú
            // ==========================================
            switch (opcion) {
                case 1:
                    // Explorar nueva habitación
                    explorarHabitacion();
                    break;

                case 2:
                    // Ver inventario
                    jugador.mostrarInventario();
                    break;

                case 3:
                    // Usar poción
                    if (jugador.getVida() < 100) {
                        jugador.usarPocion();
                    } else {
                        System.out.println("¡Ya tienes la vida al máximo!");
                    }
                    break;

                case 4:
                    // Confirmar salida
                    juegoActivo = confirmarSalida();
                    break;

                default:
                    System.out.println("[!] Opcion no valida. Intenta de nuevo.");
                    break;
            }

            // Verificar condiciones de fin del juego
            if (!jugador.estaVivo()) {
                juegoActivo = false;
                mostrarGameOver();
            }

            if (juegoGanado) {
                juegoActivo = false;
                mostrarVictoria();
            }

        } while (juegoActivo); // El bucle continúa mientras juegoActivo sea true

        // Cerrar Scanner
        scanner.close();

        System.out.println("\n¡Gracias por jugar Dungeon Crawler!");
        System.out.println("Desarrollado para: Taller de Programación");
    }

    /**
     * Mostrar pantalla de bienvenida del juego
     */
    private static void mostrarBienvenida() {
        System.out.println("\n");
        System.out.println("+----------------------------------------------------------+");
        System.out.println("|                                                          |");
        System.out.println("|     DDDD   U   U  N   N   GGGG   EEEEE   OOO   N   N     |");
        System.out.println("|     D   D  U   U  NN  N  G       E      O   O  NN  N     |");
        System.out.println("|     D   D  U   U  N N N  G  GGG  EEEE   O   O  N N N     |");
        System.out.println("|     D   D  U   U  N  NN  G    G  E      O   O  N  NN     |");
        System.out.println("|     DDDD    UUU   N   N   GGGG   EEEEE   OOO   N   N     |");
        System.out.println("|                                                          |");
        System.out.println("|           CCCC   RRRR    AAA   W   W  L                  |");
        System.out.println("|          C       R   R  A   A  W   W  L                  |");
        System.out.println("|          C       RRRR   AAAAA  W W W  L                  |");
        System.out.println("|          C       R  R   A   A  WW WW  L                  |");
        System.out.println("|           CCCC   R   R  A   A  W   W  LLLLL              |");
        System.out.println("|                                                          |");
        System.out.println("|             Taller de Programacion - 2026                |");
        System.out.println("|                                                          |");
        System.out.println("+----------------------------------------------------------+");
        System.out.println("\n");
    }

    /**
     * Mostrar menú principal del juego
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n--------------------------------------");
        jugador.mostrarEstado();
        System.out.printf("Habitacion actual: %d/%d%n", habitacionActual, totalHabitaciones);
        System.out.println("--------------------------------------");
        System.out.println("  [1] Explorar siguiente habitacion");
        System.out.println("  [2] Ver inventario");
        System.out.println("  [3] Usar pocion");
        System.out.println("  [4] Salir del juego");
        System.out.println("--------------------------------------");
        System.out.print("Elige una opcion: ");
    }

    /**
     * Obtener opción del usuario con manejo de errores
     * 
     * TEMA DEL SÍLABO: TRY-CATCH para evitar errores
     * si el usuario ingresa una letra en lugar de un número
     */
    private static int obtenerOpcionUsuario() {
        int opcion = 0;

        // ==========================================
        // TRY-CATCH: Manejo de excepciones
        // Previene errores si el usuario ingresa texto
        // en lugar de un número
        // ==========================================
        try {
            String entrada = scanner.nextLine();
            opcion = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            // Si el usuario ingresa una letra, retornamos 0 (opción inválida)
            System.out.println("[!] Error: Debes ingresar un numero.");
            opcion = 0;
        }

        return opcion;
    }

    /**
     * Explorar una nueva habitación de la mazmorra
     */
    private static void explorarHabitacion() {
        habitacionActual++;

        // Crear nueva habitación
        Habitacion habitacion;

        // IF-ELSE: Verificar si es la habitación del jefe final
        if (habitacionActual >= totalHabitaciones) {
            habitacion = new Habitacion(habitacionActual, true); // Habitación del jefe
        } else {
            habitacion = new Habitacion(habitacionActual);
        }

        // Mostrar descripción de la habitación
        habitacion.mostrarDescripcion();
        pausar();

        // IF-ELSE: Procesar según el tipo de evento
        if (habitacion.tieneEnemigo()) {
            // Hay un enemigo en la habitación
            iniciarCombate(habitacion.getEnemigo());

            // Verificar si derrotamos al jefe
            if (habitacion.getTipoEvento().equalsIgnoreCase("jefe") &&
                    habitacion.getEnemigo().estaDerrotado()) {
                juegoGanado = true;
            }
        } else {
            // Procesar evento no-combate
            String item = habitacion.procesarEvento(jugador, habitacionActual / 3 + 1);

            // Si encontramos un item, agregarlo al inventario
            if (item != null) {
                jugador.agregarItemInventario(item);
            }
        }
    }

    /**
     * Sistema de combate por turnos
     */
    private static void iniciarCombate(Enemigo enemigo) {
        System.out.println("\n*** COMBATE INICIADO! ***");
        enemigo.mostrarInfo();

        // Calcular daño del jugador (aleatorio entre 15-25)
        int danioJugador = 15 + (int) (Math.random() * 11);

        // ==========================================
        // BUCLE DO-WHILE: Combate por turnos
        // Continúa hasta que el jugador o el enemigo sean derrotados
        // ==========================================
        boolean combateActivo = true;

        do {
            System.out.println("\n--------------------------------------");
            System.out.println("  [ENEMIGO] " + enemigo.getNombre() + " - Vida: " + enemigo.getVida());
            jugador.mostrarEstado();
            System.out.println("--------------------------------------");
            System.out.println("  [1] Atacar");
            System.out.println("  [2] Usar pocion");
            System.out.println("  [3] Intentar huir");
            System.out.println("--------------------------------------");
            System.out.print("Que haras? ");

            int accion = obtenerOpcionUsuario();

            // SWITCH-CASE: Procesar acción de combate
            switch (accion) {
                case 1: // Atacar
                    System.out.println("\n¡Atacas al " + enemigo.getNombre() + "!");
                    enemigo.recibirDanio(danioJugador);
                    System.out.println("Infliges " + danioJugador + " de daño.");

                    // IF-ELSE: Verificar si el enemigo fue derrotado
                    if (enemigo.estaDerrotado()) {
                        System.out.println("\n*** Derrotaste a " + enemigo.getNombre() + "! ***");
                        jugador.recolectarOro(enemigo.getOroRecompensa());
                        combateActivo = false;
                    } else {
                        // Turno del enemigo
                        int danioEnemigo = enemigo.atacar();
                        jugador.recibirDanio(danioEnemigo);

                        if (!jugador.estaVivo()) {
                            combateActivo = false;
                        }
                    }
                    break;

                case 2: // Usar poción
                    jugador.usarPocion();
                    // El enemigo también ataca
                    int danioEnemigo = enemigo.atacar();
                    jugador.recibirDanio(danioEnemigo);

                    if (!jugador.estaVivo()) {
                        combateActivo = false;
                    }
                    break;

                case 3: // Huir
                    // 40% de probabilidad de huir exitosamente
                    if (Math.random() < 0.4) {
                        System.out.println("Escapaste con exito!");
                        habitacionActual--; // Volver a la habitación anterior
                        combateActivo = false;
                    } else {
                        System.out.println("[X] No pudiste escapar!");
                        int danioHuida = enemigo.atacar();
                        jugador.recibirDanio(danioHuida);

                        if (!jugador.estaVivo()) {
                            combateActivo = false;
                        }
                    }
                    break;

                default:
                    System.out.println("[!] Accion no valida. Pierdes tu turno.");
                    int danioPerdido = enemigo.atacar();
                    jugador.recibirDanio(danioPerdido);

                    if (!jugador.estaVivo()) {
                        combateActivo = false;
                    }
                    break;
            }

        } while (combateActivo);
    }

    /**
     * Confirmar salida del juego con validación de String
     * 
     * TEMA DEL SÍLABO: Uso de .equalsIgnoreCase() para validar entrada
     */
    private static boolean confirmarSalida() {
        System.out.print("¿Estás seguro de que quieres salir? (si/no): ");
        String respuesta = scanner.nextLine();

        // ==========================================
        // USO DE .equalsIgnoreCase(): Comparar cadenas
        // sin importar mayúsculas/minúsculas
        // ==========================================
        if (respuesta.equalsIgnoreCase("si") ||
                respuesta.equalsIgnoreCase("sí") ||
                respuesta.equalsIgnoreCase("s")) {
            return false; // El juego ya no está activo
        } else {
            System.out.println("¡Bien! Continuemos la aventura.");
            return true; // El juego sigue activo
        }
    }

    /**
     * Mostrar pantalla de Game Over
     */
    private static void mostrarGameOver() {
        System.out.println("\n");
        System.out.println("+----------------------------------------------------------+");
        System.out.println("|                                                          |");
        System.out.println("|      GGGG    AAA   M   M  EEEEE                          |");
        System.out.println("|     G       A   A  MM MM  E                              |");
        System.out.println("|     G  GGG  AAAAA  M M M  EEEE                           |");
        System.out.println("|     G    G  A   A  M   M  E                              |");
        System.out.println("|      GGGG   A   A  M   M  EEEEE                          |");
        System.out.println("|                                                          |");
        System.out.println("|      OOO   V   V  EEEEE  RRRR                            |");
        System.out.println("|     O   O  V   V  E      R   R                           |");
        System.out.println("|     O   O  V   V  EEEE   RRRR                            |");
        System.out.println("|     O   O   V V   E      R  R                            |");
        System.out.println("|      OOO     V    EEEEE  R   R                           |");
        System.out.println("|                                                          |");
        System.out.println("+----------------------------------------------------------+");
        System.out.printf("|  [X] %s ha caido en la mazmorra...                   |%n",
                ajustarTexto(jugador.getNombre(), 15));
        System.out.printf("|  Llegaste hasta la habitacion %d                          |%n", habitacionActual);
        System.out.printf("|  Oro recolectado: %d                                     |%n", jugador.getOro());
        System.out.println("+----------------------------------------------------------+");
    }

    /**
     * Mostrar pantalla de victoria
     */
    private static void mostrarVictoria() {
        System.out.println("\n");
        System.out.println("+----------------------------------------------------------+");
        System.out.println("|                     *** VICTORIA! ***                    |");
        System.out.println("+----------------------------------------------------------+");
        System.out.println("|                                                          |");
        System.out.println("|     V   V  III   CCCC  TTTTT   OOO   RRRR   III    A     |");
        System.out.println("|     V   V   I   C        T    O   O  R   R   I    A A    |");
        System.out.println("|     V   V   I   C        T    O   O  RRRR    I   AAAAA   |");
        System.out.println("|      V V    I   C        T    O   O  R  R    I   A   A   |");
        System.out.println("|       V    III   CCCC    T     OOO   R   R  III  A   A   |");
        System.out.println("|                                                          |");
        System.out.println("+----------------------------------------------------------+");
        System.out.printf("|  Felicidades, %s!                                    |%n",
                ajustarTexto(jugador.getNombre(), 15));
        System.out.println("|  Has derrotado al Dragon Ancestral                       |");
        System.out.printf("|  Vida restante: %d                                       |%n", jugador.getVida());
        System.out.printf("|  Oro total: %d                                           |%n", jugador.getOro());
        System.out.println("|                                                          |");
        System.out.println("|           Eres el campeon de la mazmorra!                |");
        System.out.println("+----------------------------------------------------------+");
    }

    /**
     * Método auxiliar para ajustar texto a un ancho fijo
     * Demuestra: métodos de la clase String
     */
    private static String ajustarTexto(String texto, int ancho) {
        if (texto.length() > ancho) {
            return texto.substring(0, ancho);
        }
        // Rellenar con espacios
        StringBuilder sb = new StringBuilder(texto);
        while (sb.length() < ancho) {
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Pausar el juego esperando que el usuario presione Enter
     */
    private static void pausar() {
        System.out.println("\nPresiona ENTER para continuar...");
        scanner.nextLine();
    }
}
