import java.util.ArrayList;
import java.util.List;

// --- Clases de Entidad (simuladas para el ejercicio) ---
    class Equipo {

        private String nombre;
        private List<Jugador> jugadores = new ArrayList<>();

        //Constructor - Responsabilidad 1
        public Equipo(String nombre) { 
            this.nombre = nombre; 
        }

        //Getter
        public String getNombre() { return nombre; }

        //Responsabilidad 2.
        public void agregarJugador(Jugador j) { this.jugadores.add(j); }

        public List<Jugador> getJugadores() { return this.jugadores; }

    }
    
    class Jugador {

        private String nombre;
        private String posicion; // Posibles valores: "Portero", "Delantero", "Defensa"

        // Constructor - Responsabilidad 1
        public Jugador(String nombre, String posicion) { this.nombre = nombre;
            this.posicion = posicion; 
        }

        //Getters & Setters
        public String getNombre() { return nombre; }
        public String getPosicion() { return this.posicion; }
    }

    class Arbitro {
        private String nombre;

        //Constructor - Responsabilidad 1
        public Arbitro(String nombre) { 
            this.nombre = nombre; 
        }

        //Getter
        public String getNombre() { return nombre; }
    }

    /**
    * Clase principal para la gestión de un campeonato de fútbol.
    */
    public class GestorCampeonato {

        private List<Equipo> equipos = new ArrayList<>();
        private List<Arbitro> arbitros = new ArrayList<>();

        /**
        * Registra los participantes en el sistema.
        */

        //Responsabilidad 1
        public void registrarParticipantes() {
            // Registrar equipos
            Equipo equipoA = new Equipo("Los Ganadores");
            equipoA.agregarJugador(new Jugador("Juan Pérez", "Delantero"));
            equipoA.agregarJugador(new Jugador("Pedro Pan", "Portero"));
            equipos.add(equipoA);
            System.out.println("Equipo 'Los Ganadores' registrado.");
            Equipo equipoB = new Equipo("Los Retadores");
            equipoB.agregarJugador(new Jugador("Alicia Smith", "Defensa"));


            equipos.add(equipoB);
            System.out.println("Equipo 'Los Retadores' registrado.");
            // Contratar árbitros
            arbitros.add(new Arbitro("Miguel Díaz"));
            System.out.println("Árbitro 'Miguel Díaz' contratado.");
        }
    
    /**
    * Calcula las bonificaciones para los jugadores.
    */
    public void calcularBonificaciones() {
        //Responsabilidad 1
        System.out.println("\n--- Calculando Bonificaciones de Jugadores ---");
        for (Equipo equipo : equipos) {
            for (Jugador jugador : equipo.getJugadores()) {

                if (jugador.getPosicion().equals("Delantero")) {
                    System.out.println("Calculando bonificación alta para Delantero: " + jugador.getNombre());

                } else if (jugador.getPosicion().equals("Portero")) {
                    System.out.println("Calculando bonificación estándar para Portero: " + jugador.getNombre());

                } else {
                    System.out.println("Calculando bonificación base para: " + jugador.getNombre());
                }
            }
        }
    }

    /**
    * Genera y muestra en consola diferentes tipos de reportes.
    */
    public void generarReportes(String formato) {
        //Responsabilidad 1
        if (formato.equalsIgnoreCase("TEXTO")) {

            String contenidoReporte = "--- Reporte del Campeonato (TEXTO) ---\n";
            contenidoReporte += "EQUIPOS:\n";

            for (Equipo equipo : equipos) {
                contenidoReporte += "- " + equipo.getNombre() + "\n";
            }

            contenidoReporte += "ÁRBITROS:\n";

            for (Arbitro arbitro : arbitros) {
                contenidoReporte += "- " + arbitro.getNombre() + "\n";
            }

            System.out.println(contenidoReporte);

        } else if (formato.equalsIgnoreCase("HTML")) {
            String contenidoHtml = "<html><body>\n";
            contenidoHtml += " <h1>Reporte del Campeonato</h1>\n";
            contenidoHtml += " <h2>Equipos</h2>\n <ul>\n";

            for (Equipo equipo : equipos) {
                contenidoHtml += " <li>" + equipo.getNombre() + "</li>\n";
            }

            contenidoHtml += " </ul>\n <h2>Árbitros</h2>\n <ul>\n";

            for (Arbitro arbitro : arbitros) {
                contenidoHtml += " <li>" + arbitro.getNombre() + "</li>\n";
            }

            contenidoHtml += " </ul>\n</body></html>";
            System.out.println(contenidoHtml);
        }
    }

    // Método principal para simular la ejecución del módulo
    public static void main(String[] args) {
        
        //Responsabilidad 1
        GestorCampeonato gestor = new GestorCampeonato();
        gestor.registrarParticipantes();
        gestor.calcularBonificaciones();
        System.out.println("\n--- Generando Reportes ---");
        gestor.generarReportes("TEXTO");
        System.out.println("\n--- Generando más Reportes ---");
        gestor.generarReportes("HTML");
    }
}