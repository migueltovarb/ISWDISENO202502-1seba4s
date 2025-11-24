package com.hotel.reservas.controller;

import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaQueryController {
    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
}
