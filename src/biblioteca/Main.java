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

        reserva.cambiarEstado(estado("Confirmada"));
        System.out.println(reserva);

        reserva.cambiarEstado(estado("Cancelada"));
        System.out.println(reserva);
    }

    private static EstadoReserva estado(String nombre) {
        return new EstadoReserva() {
            public String nombre() { return nombre; }
            public void confirmar(Reserva reserva) { }
            public void cancelar(Reserva reserva) { }
            public void finalizar(Reserva reserva) { }
        };
    }
}