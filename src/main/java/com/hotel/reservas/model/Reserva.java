package com.hotel.reservas.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Data
@Document(collection = "reservas")
public class Reserva {
    @Id
    private String id;
    private String habitacionNumero;
    private String hotelId;
    private String clienteId;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private boolean checkIn;
    private boolean checkOut;
}
