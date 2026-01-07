/**
 * Clase Habitacion - Representa cada habitación de la mazmorra.
 * 
 * TEMAS DEL SÍLABO APLICADOS:
 * - Programación Orientada a Objetos (POO)
 * - Encapsulamiento con atributos private
 * - Métodos getter y setter
 * - Constructor con uso de 'this'
 * - Uso de Math.random() para eventos aleatorios
 * - Validación con métodos de String
 */
public class Habitacion {

    // ==========================================
    // ENCAPSULAMIENTO: Atributos privados
    // ==========================================
    private int numero;
    private String descripcion;
    private String tipoEvento; // "enemigo", "tesoro", "pocion", "trampa", "vacia", "jefe"
    private boolean explorada;
    private Enemigo enemigo;

    // ==========================================
    // CONSTRUCTOR: Inicialización con 'this'
    // ==========================================
    public Habitacion(int numero) {
        this.numero = numero;
        this.explorada = false;
        this.enemigo = null;
        generarEventoAleatorio();
    }

    // Constructor especial para habitación del jefe
    public Habitacion(int numero, boolean esJefeFinal) {
        this.numero = numero;
        this.explorada = false;
        if (esJefeFinal) {
            this.tipoEvento = "jefe";
            this.descripcion = "Una gran cámara oscura. Sientes una presencia poderosa...";
            this.enemigo = Enemigo.crearJefeFinal();
        } else {
            generarEventoAleatorio();
        }
    }

    // ==========================================
    // MÉTODOS GETTER
    // ==========================================
    public int getNumero() {
        return this.numero;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getTipoEvento() {
        return this.tipoEvento;
    }

    public boolean isExplorada() {
        return this.explorada;
    }

    public Enemigo getEnemigo() {
        return this.enemigo;
    }

    // ==========================================
    // MÉTODOS SETTER
    // ==========================================
    public void setExplorada(boolean explorada) {
        this.explorada = explorada;
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
    }

    // ==========================================
    // MÉTODOS DE LÓGICA DE HABITACIÓN
    // ==========================================

    /**
     * Generar evento aleatorio para la habitación
     * Demuestra: uso de Math.random() y switch-case
     */
    private void generarEventoAleatorio() {
        int evento = (int) (Math.random() * 100);

        // SWITCH-CASE para determinar el tipo de evento
        // Usando rangos de probabilidad
        if (evento < 40) { // 40% probabilidad de enemigo
            this.tipoEvento = "enemigo";
            this.descripcion = "¡Un enemigo te bloquea el paso!";
            this.enemigo = Enemigo.crearEnemigoAleatorio(1);
        } else if (evento < 60) { // 20% probabilidad de tesoro
            this.tipoEvento = "tesoro";
            this.descripcion = "Encuentras un cofre brillante en la esquina.";
        } else if (evento < 75) { // 15% probabilidad de poción
            this.tipoEvento = "pocion";
            this.descripcion = "Hay una poción abandonada en el suelo.";
        } else if (evento < 85) { // 10% probabilidad de trampa
            this.tipoEvento = "trampa";
            this.descripcion = "¡Cuidado! El suelo parece inestable...";
        } else { // 15% probabilidad de vacía
            this.tipoEvento = "vacia";
            this.descripcion = "Una habitación vacía. Puedes descansar un momento.";
        }
    }

    /**
     * Procesar el evento de la habitación
     * Demuestra: SWITCH-CASE para diferentes acciones
     * 
     * @return String con item encontrado, o null si no hay item
     */
    public String procesarEvento(Jugador jugador, int nivelDificultad) {
        String itemEncontrado = null;

        // SWITCH-CASE para procesar diferentes tipos de eventos
        switch (this.tipoEvento) {
            case "tesoro":
                int oroEncontrado = (int) (Math.random() * 20) + 10 + (nivelDificultad * 5);
                jugador.recolectarOro(oroEncontrado);

                // 50% de probabilidad de encontrar un item
                if (Math.random() < 0.5) {
                    String[] itemsPosibles = { "Espada Oxidada", "Escudo de Madera", "Anillo Místico",
                            "Amuleto Antiguo", "Capa de Sombras" };
                    itemEncontrado = itemsPosibles[(int) (Math.random() * itemsPosibles.length)];
                }
                break;

            case "pocion":
                jugador.setPociones(jugador.getPociones() + 1);
                System.out.println("¡Encontraste una poción! Ahora tienes " + jugador.getPociones() + " pociones.");
                break;

            case "trampa":
                int danioTrampa = (int) (Math.random() * 10) + 5 + nivelDificultad;
                System.out.println("¡TRAMPA! Una flecha sale de la pared.");
                jugador.recibirDanio(danioTrampa);
                break;

            case "vacia":
                // Pequeña recuperación de vida en habitación vacía
                if (jugador.getVida() < 100) {
                    int recuperacion = 5;
                    jugador.setVida(Math.min(100, jugador.getVida() + recuperacion));
                    System.out.println("Descansas un momento y recuperas " + recuperacion + " de vida.");
                }
                break;

            case "enemigo":
            case "jefe":
                // El combate se maneja en la clase principal
                System.out.println("¡Prepárate para el combate!");
                break;

            default:
                System.out.println("No pasa nada especial...");
                break;
        }

        this.explorada = true;
        return itemEncontrado;
    }

    /**
     * Verificar si la habitación tiene un enemigo usando métodos de String
     * Demuestra: uso de .equalsIgnoreCase()
     */
    public boolean tieneEnemigo() {
        // Uso de equalsIgnoreCase para comparación de cadenas sin distinción de
        // mayúsculas
        return this.tipoEvento.equalsIgnoreCase("enemigo") ||
                this.tipoEvento.equalsIgnoreCase("jefe");
    }

    /**
     * Mostrar descripción de la habitación
     */
    public void mostrarDescripcion() {
        System.out.println("\n+========================================+");
        System.out.println("|     HABITACION #" + this.numero + "                      |");
        System.out.println("+========================================+");
        System.out.println("  " + this.descripcion);
        System.out.println("+========================================+");
    }
}
