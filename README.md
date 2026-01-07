# Dungeon Crawler - Juego de Mazmorras en Java

Proyecto de consola para el curso de **Taller de Programación 2026**.

## Requisitos

- Java JDK 8 o superior

## Descargar el Proyecto

```bash
git clone https://github.com/TU_USUARIO/dungeon-crawler-java.git
cd dungeon-crawler-java
```

## Compilar

```bash
javac -encoding UTF-8 src/*.java -d bin
```

## Ejecutar

```bash
cd bin
java DungeonCrawler
```

## Estructura del Proyecto

```
├── src/
│   ├── DungeonCrawler.java   # Clase principal
│   ├── Jugador.java          # Clase del jugador
│   ├── Enemigo.java          # Clase de enemigos
│   └── Habitacion.java       # Clase de habitaciones
└── bin/                      # Archivos compilados
```

## Temas del Silabo Aplicados

- Scanner y System.out.printf
- Bucle do-while
- if-else y switch-case
- Variables primitivas (vida, oro, pociones)
- Arreglo unidimensional (inventario)
- POO con 3 clases
- Encapsulamiento (private, getters/setters, this)
- Metodos de String (.equalsIgnoreCase())
- try-catch para manejo de errores

## Como Jugar

1. Ingresa tu nombre de heroe
2. Explora 10 habitaciones
3. Combate enemigos, recolecta oro y pociones
4. Derrota al Dragon Ancestral en la habitacion final

## Comandos del Menu

- `1` - Explorar siguiente habitacion
- `2` - Ver inventario
- `3` - Usar pocion (recupera 30 de vida)
- `4` - Salir del juego
