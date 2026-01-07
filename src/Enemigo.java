/**
 * Clase Enemigo - Representa a los enemigos en las mazmorras.
 * 
 * TEMAS DEL SÍLABO APLICADOS:
 * - Programación Orientada a Objetos (POO)
 * - Encapsulamiento con atributos private
 * - Métodos getter y setter
 * - Constructor con uso de 'this'
 * - Uso de Math.random() para mecánicas aleatorias
 */
public class Enemigo {

    // ==========================================
    // ENCAPSULAMIENTO: Atributos privados
    // ==========================================
    private String nombre;
    private String tipo;
    private int vida;
    private int danioMinimo;
    private int danioMaximo;
    private int oroRecompensa;

    // ==========================================
    // CONSTRUCTOR: Inicialización con 'this'
    // ==========================================
    public Enemigo(String nombre, String tipo, int vida, int danioMinimo, int danioMaximo, int oroRecompensa) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.vida = vida;
        this.danioMinimo = danioMinimo;
        this.danioMaximo = danioMaximo;
        this.oroRecompensa = oroRecompensa;
    }

    // ==========================================
    // MÉTODOS GETTER
    // ==========================================
    public String getNombre() {
        return this.nombre;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getVida() {
        return this.vida;
    }

    public int getOroRecompensa() {
        return this.oroRecompensa;
    }

    // ==========================================
    // MÉTODOS SETTER
    // ==========================================
    public void setVida(int vida) {
        this.vida = vida;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    // ==========================================
    // MÉTODOS DE LÓGICA DEL ENEMIGO
    // ==========================================

    /**
     * Calcular daño aleatorio que inflige el enemigo
     * Demuestra: uso de Math.random() para generar números aleatorios
     */
    public int atacar() {
        // Generar daño aleatorio entre danioMinimo y danioMaximo
        int danio = (int) (Math.random() * (danioMaximo - danioMinimo + 1)) + danioMinimo;
        return danio;
    }

    /**
     * Recibir daño del jugador
     */
    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    /**
     * Verificar si el enemigo está derrotado
     */
    public boolean estaDerrotado() {
        return this.vida <= 0;
    }

    /**
     * Mostrar información del enemigo
     */
    public void mostrarInfo() {
        System.out.println("===========================================");
        System.out.println("  [ENEMIGO] " + this.nombre + " (" + this.tipo + ")");
        System.out.println("  Vida: " + this.vida);
        System.out.println("  Danio: " + this.danioMinimo + "-" + this.danioMaximo);
        System.out.println("===========================================");
    }

    // ==========================================
    // MÉTODOS ESTÁTICOS: Fábrica de enemigos
    // Demuestra: diferentes tipos de enemigos
    // ==========================================

    /**
     * Crear un enemigo aleatorio basado en la dificultad
     */
    public static Enemigo crearEnemigoAleatorio(int nivelDificultad) {
        int tipoEnemigo = (int) (Math.random() * 4);

        // IF-ELSE para determinar el tipo de enemigo
        if (tipoEnemigo == 0) {
            return new Enemigo("Goblin", "Criatura", 20 + nivelDificultad * 5, 5, 10 + nivelDificultad,
                    10 + nivelDificultad * 2);
        } else if (tipoEnemigo == 1) {
            return new Enemigo("Esqueleto", "No-muerto", 25 + nivelDificultad * 5, 8, 12 + nivelDificultad,
                    15 + nivelDificultad * 2);
        } else if (tipoEnemigo == 2) {
            return new Enemigo("Lobo Oscuro", "Bestia", 30 + nivelDificultad * 5, 10, 15 + nivelDificultad,
                    20 + nivelDificultad * 2);
        } else {
            return new Enemigo("Orco", "Humanoide", 40 + nivelDificultad * 5, 12, 18 + nivelDificultad,
                    25 + nivelDificultad * 2);
        }
    }

    /**
     * Crear el jefe final de la mazmorra
     */
    public static Enemigo crearJefeFinal() {
        return new Enemigo("Dragón Ancestral", "JEFE FINAL", 150, 20, 35, 100);
    }
}
