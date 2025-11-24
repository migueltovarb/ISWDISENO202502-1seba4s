package com.hotel.reservas.service;

import com.hotel.reservas.model.Hotel;
import com.hotel.reservas.model.Hotel;
import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.model.Cliente;
import com.hotel.reservas.repository.HotelRepository;
import com.hotel.reservas.repository.ReservaRepository;
import com.hotel.reservas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Hotel.Habitacion> buscarDisponibilidad(String hotelId, LocalDate entrada, LocalDate salida) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        if (hotelOpt.isEmpty()) return List.of();
        Hotel hotel = hotelOpt.get();
        List<Hotel.Habitacion> habitaciones = hotel.getHabitaciones();
        if (habitaciones == null) return List.of();
        // Devuelve todas las habitaciones disponibles del hotel
        return habitaciones.stream()
            .filter(Hotel.Habitacion::isDisponible)
            .collect(Collectors.toList());
    }

    public Reserva hacerReserva(String hotelId, String habitacionNumero, String clienteId, LocalDate entrada, LocalDate salida) {
        Reserva reserva = new Reserva();
        reserva.setHotelId(hotelId);
        reserva.setHabitacionNumero(habitacionNumero);
        reserva.setClienteId(clienteId);
        reserva.setFechaEntrada(entrada);
        reserva.setFechaSalida(salida);
        reserva.setCheckIn(false);
        reserva.setCheckOut(false);
        return reservaRepository.save(reserva);
    }

    public Reserva checkIn(String reservaId) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(reservaId);
        if (reservaOpt.isEmpty()) return null;
        Reserva reserva = reservaOpt.get();
        reserva.setCheckIn(true);
        return reservaRepository.save(reserva);
    }

    public Reserva checkOut(String reservaId) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(reservaId);
        if (reservaOpt.isEmpty()) return null;
        Reserva reserva = reservaOpt.get();
        reserva.setCheckOut(true);
        return reservaRepository.save(reserva);
    }
}
