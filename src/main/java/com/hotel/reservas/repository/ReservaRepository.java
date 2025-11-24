package com.hotel.reservas.repository;

import com.hotel.reservas.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    List<Reserva> findByHotelIdAndHabitacionNumeroAndFechaSalidaAfterAndFechaEntradaBefore(
        String hotelId, String habitacionNumero, java.time.LocalDate fechaEntrada, java.time.LocalDate fechaSalida);
    List<Reserva> findByClienteId(String clienteId);
}
