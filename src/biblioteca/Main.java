package biblioteca;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RepositorioSalas salas = new RepositorioSalasMemoria();
        RepositorioReservasMemoria reservas = new RepositorioReservasMemoria();

        List<ObservadorReserva> observadores = List.of(
                new NotificadorEstudiante(),
                new RegistroEventos()
        );

        ReservaFacade fachada = new ReservaFacade(salas, reservas, observadores);

        Reserva reserva = fachada.realizarReserva(
                new Estudiante("E001", "Laura"),
                LocalDate.now().plusDays(7),
                LocalTime.of(14, 0),
                2,
                4,
                new SeleccionPorCapacidad()
        ).orElseThrow();
        System.out.println(reserva);

        reserva.cancelar();
        System.out.println(reserva);

        System.out.println("\nIntentar confirmar una cancelada:");
        try {
            reserva.confirmar();
        } catch (IllegalStateException ex) {
            System.out.println("Operación rechazada: " + ex.getMessage());
        }

        Reserva otra = fachada.realizarReserva(
                new Estudiante("E004", "Samuel"),
                LocalDate.now().plusDays(10),
                LocalTime.of(11, 0),
                1,
                2,
                new SeleccionPorCapacidad()
        ).orElseThrow();
        otra.finalizar();
        System.out.println(otra);

        System.out.println("\nIntentar cancelar una finalizada:");
        try {
            otra.cancelar();
        } catch (IllegalStateException ex) {
            System.out.println("Operación rechazada: " + ex.getMessage());
        }
    }
}