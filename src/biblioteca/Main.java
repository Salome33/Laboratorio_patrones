package biblioteca;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        RepositorioSalas salas = new RepositorioSalasMemoria();
        RepositorioReservasMemoria reservas = new RepositorioReservasMemoria();
        ReservaFacade fachada = new ReservaFacade(salas, reservas);
        Estudiante laura = new Estudiante("E001", "Laura");

        System.out.println("RESERVA EXITOSA");
        fachada.realizarReserva(laura, LocalDate.now().plusDays(7),
                        LocalTime.of(14, 0), 2, 4, new SeleccionPorCapacidad())
                .ifPresent(System.out::println);

        System.out.println("\nSIN SALA ADECUADA");
        fachada.realizarReserva(laura, LocalDate.now().plusDays(8),
                LocalTime.of(10, 0), 2, 10, new SeleccionPorCapacidad());

        System.out.println("\nReservas guardadas: " + reservas.obtenerTodas().size());
    }
}