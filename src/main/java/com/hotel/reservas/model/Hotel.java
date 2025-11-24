package com.hotel.reservas.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "hoteles")
public class Hotel {
    @Id
    private String id;
    private String nombre;
    private String direccion;
    private List<Habitacion> habitaciones;

    @Data
    public static class Habitacion {
        private String numero;
        private String tipo; // simple, doble, suite
        private boolean disponible;
        private double precioPorNoche;
        private List<String> reservasIds;
    }
}
