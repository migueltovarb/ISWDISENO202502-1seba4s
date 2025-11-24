package com.hotel.reservas.controller;

import com.hotel.reservas.model.Hotel;
import com.hotel.reservas.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hoteles")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;


    @GetMapping
    public List<Hotel> listarHoteles() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel obtenerHotel(@PathVariable String id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Hotel crearHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}
