# Gestor Campeonato

## Colaboradores:
- Jolgan Pardo
- Yisus Olarte

## 1. Principio SRP

### Violaciones
La clase `GestorCampeonato` tiene multiples responsabilidades:
- Registro de equipos y árbitros.
- Cálculo de bonificaciones.
- Generación de reportes.

Esto hace que sea dificil de mantener.

---

## 2. Principio OCP

### Violacion
El método `generarReportes` usa condicionales para definir el formato.  
Si se agrega el formato PDF, se tendria que modificar el metodo, violando el principio de Abierto/Cerrado.  
Abierto a extensiones cerrado a modificaciones.

---

Codigo antes de refactorizar con **SOLID**

public void generarReportes(String formato) {
    if (formato.equalsIgnoreCase("TEXTO")) { ... }
    else if (formato.equalsIgnoreCase("HTML")) { ... }
}

--- 

Despues de refactorizar con **SOLID**

interface Reporte {
    void generar(List<Equipo> equipos, List<Arbitro> arbitros);
}

class ReporteTexto implements Reporte {
    @Override
    public void generar(List<Equipo> equipos, List<Arbitro> arbitros) {
        System.out.println("Generando reporte en TEXTO...");
    }
}

class ReporteHTML implements Reporte {
    @Override
    public void generar(List<Equipo> equipos, List<Arbitro> arbitros) {
        System.out.println("Generando reporte en HTML...");
    }
}

---

## 3. Principio LSP
El principio LSP dice: “Si una clase B hereda de una clase A, entonces deberia poder usarse en lugar de A sin romper la aplicacion”.

Lo ideal para este caso es que las posiciones sean una subclase de Jugador y que cada una sepa calcular su propia bonificacion.  
Así cualquier "Jugador" pueda usarse de forma "Polimorfica" ya sea Portero o Delantero.

### Violaciones
- Además de estar sujeto a que se haga una comparacion de tipo if/else violando el principio OCP (Abierto/Cerrado).  
- Está violando también el principio LSP.

---

Codigo antes de refactorizar con **SOLID**

if (jugador.getPosicion().equals("Delantero")) {
    System.out.println("Bonificación alta...");
} else if (jugador.getPosicion().equals("Portero")) {
    System.out.println("Bonificación estándar...");
} else {
    System.out.println("Bonificación base...");
}

--- 

Despues de refactorizar con **SOLID**

abstract class Jugador {
    private String nombre;
    public Jugador(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }
    public abstract void calcularBonificacion();
}

class Delantero extends Jugador {
    public Delantero(String nombre) { super(nombre); }
    @Override
    public void calcularBonificacion() {
        System.out.println("Bonificación alta para Delantero: " + getNombre());
    }
}

class Portero extends Jugador {
    public Portero(String nombre) { super(nombre); }
    @Override
    public void calcularBonificacion() {
        System.out.println("Bonificación estándar para Portero: " + getNombre());
    }
}

---

## 4. Principio ISP
El principio ISP nos dice que ninguna clase debe estar obligada a depender de metodos QUE NO USA

En el codigo no se ven interfaces pero en el mismo archivo se esta haciendo todo como lo cual viola el
Principio de SRP:
- Registrar jugador
- Calcular las bonificaciones
- Generar los reportes

Despues de refactorizar con **SOLID**

Para este caso lo que se puede hacer es crear interfaces pequeñas y especificas, algo asi:

interface Registrable {
    void registrar();
}

interface Bonificable {
    void calcularBonificacion();
}

interface Reportable {
    void generarReporte();
}

