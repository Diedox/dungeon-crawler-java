/**
 * Clase Jugador - Representa al jugador del juego de mazmorras.
 * 
 * TEMAS DEL SÍLABO APLICADOS:
 * - Programación Orientada a Objetos (POO)
 * - Encapsulamiento con atributos private
 * - Métodos getter y setter
 * - Constructor con uso de 'this'
 * - Arreglo unidimensional para inventario
 * - Variables primitivas (vida, oro, pociones)
 */
public class Jugador {

    // ==========================================
    // ENCAPSULAMIENTO: Atributos privados (private)
    // Solo accesibles mediante getters/setters
    // ==========================================
    private String nombre;
    private int vida; // Variable primitiva para vida
    private int oro; // Variable primitiva como ACUMULADOR
    private int pociones; // Variable primitiva como CONTADOR
    private String[] inventario; // ARREGLO UNIDIMENSIONAL de 5 espacios
    private int itemsEnInventario;

    // ==========================================
    // CONSTRUCTOR: Inicializa el objeto con 'this'
    // ==========================================
    public Jugador(String nombre) {
        this.nombre = nombre; // 'this' diferencia atributo de parámetro
        this.vida = 100; // Vida inicial
        this.oro = 0; // Oro inicial (acumulador empieza en 0)
        this.pociones = 2; // Pociones iniciales (contador)
        this.inventario = new String[5]; // Arreglo de 5 espacios
        this.itemsEnInventario = 0;

        // Inicializar inventario vacío
        for (int i = 0; i < inventario.length; i++) {
            inventario[i] = "[Vacio]";
        }
    }

    // ==========================================
    // MÉTODOS GETTER: Obtener valores de atributos
    // ==========================================
    public String getNombre() {
        return this.nombre;
    }

    public int getVida() {
        return this.vida;
    }

    public int getOro() {
        return this.oro;
    }

    public int getPociones() {
        return this.pociones;
    }

    public String[] getInventario() {
        return this.inventario;
    }

    // ==========================================
    // MÉTODOS SETTER: Modificar valores de atributos
    // ==========================================
    public void setVida(int vida) {
        this.vida = vida;
        if (this.vida < 0) {
            this.vida = 0; // La vida no puede ser negativa
        }
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public void setPociones(int pociones) {
        this.pociones = pociones;
    }

    // ==========================================
    // MÉTODOS DE LÓGICA DEL JUGADOR
    // ==========================================

    /**
     * Recibir daño del enemigo
     * Demuestra: uso de setter y operaciones con primitivas
     */
    public void recibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida < 0) {
            this.vida = 0;
        }
        System.out.println("¡" + this.nombre + " recibe " + danio + " de daño!");
    }

    /**
     * Usar una poción para recuperar vida
     * Demuestra: CONTADOR (pociones --)
     */
    public boolean usarPocion() {
        if (this.pociones > 0) {
            this.pociones--; // Contador decrementando
            int vidaRecuperada = 30;
            this.vida += vidaRecuperada;
            if (this.vida > 100) {
                this.vida = 100; // Vida máxima
            }
            System.out.println("¡Usaste una poción! Recuperaste " + vidaRecuperada + " de vida.");
            return true;
        } else {
            System.out.println("¡No tienes pociones disponibles!");
            return false;
        }
    }

    /**
     * Recolectar oro
     * Demuestra: ACUMULADOR (oro +=)
     */
    public void recolectarOro(int cantidad) {
        this.oro += cantidad; // Acumulador sumando
        System.out.println("¡Encontraste " + cantidad + " de oro! Total: " + this.oro);
    }

    /**
     * Agregar item al inventario
     * Demuestra: Uso de ARREGLO y búsqueda de espacio disponible
     */
    public boolean agregarItemInventario(String item) {
        // Buscar espacio vacío en el arreglo
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i].equals("[Vacio]")) {
                inventario[i] = item;
                itemsEnInventario++;
                System.out.println("¡" + item + " agregado al inventario!");
                return true;
            }
        }
        System.out.println("¡Inventario lleno! No puedes llevar más items.");
        return false;
    }

    /**
     * Mostrar el inventario completo
     * Demuestra: Recorrer ARREGLO con for
     */
    public void mostrarInventario() {
        System.out.println("\n==================================================");
        System.out.println("       [INVENTARIO DE " + this.nombre.toUpperCase() + "]");
        System.out.println("==================================================");
        for (int i = 0; i < inventario.length; i++) {
            System.out.println("  [" + (i + 1) + "] " + inventario[i]);
        }
        System.out.println("--------------------------------------------------");
        System.out.println("  Oro: " + this.oro);
        System.out.println("  Pociones: " + this.pociones);
        System.out.println("  Vida: " + this.vida + "/100");
        System.out.println("=================================================\n");
    }

    /**
     * Verificar si el jugador está vivo
     */
    public boolean estaVivo() {
        return this.vida > 0;
    }

    /**
     * Mostrar estado actual del jugador
     */
    public void mostrarEstado() {
        System.out.println("Vida: " + this.vida + " | Oro: " + this.oro + " | Pociones: " + this.pociones);
    }
}
