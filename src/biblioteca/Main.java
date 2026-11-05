package biblioteca;

public class Main {
    public static void main(String[] args) {
        Reserva reserva = new ReservaBuilder()
                .estudiante(new Estudiante("E001", "Laura"))
                .fecha(java.time.LocalDate.now().plusDays(7))
                .horaInicio(java.time.LocalTime.of(14, 0))
                .duracionHoras(2)
                .cantidadPersonas(4)
                .sala(new Sala("B", "Sala B", 4))
                .build();

        System.out.println(reserva);
    }
}