package com.hotel.reservas.controller;

import com.hotel.reservas.model.Hotel;
import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping("/disponibilidad/{hotelId}")
    public List<Hotel.Habitacion> buscarDisponibilidad(
            @PathVariable String hotelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate salida) {
        return reservaService.buscarDisponibilidad(hotelId, entrada, salida);
    }

    public static class ReservaRequest {
        public String hotelId;
        public String habitacionNumero;
        public String clienteId;
        public LocalDate entrada;
        public LocalDate salida;
    }

    @PostMapping("/hacer")
    public Reserva hacerReserva(@RequestBody ReservaRequest request) {
        return reservaService.hacerReserva(
            request.hotelId,
            request.habitacionNumero,
            request.clienteId,
            request.entrada,
            request.salida
        );
    }

    @PostMapping("/checkin/{reservaId}")
    public String checkIn(@PathVariable String reservaId) {
        Reserva reserva = reservaService.checkIn(reservaId);
        if (reserva == null) {
            return "Reserva no encontrada";
        }
        return "Check-in realizado correctamente para la reserva " + reservaId;
    }

    @PostMapping("/checkout/{reservaId}")
    public String checkOut(@PathVariable String reservaId) {
        Reserva reserva = reservaService.checkOut(reservaId);
        if (reserva == null) {
            return "Reserva no encontrada";
        }
        return "Check-out realizado correctamente para la reserva " + reservaId;
    }
}
