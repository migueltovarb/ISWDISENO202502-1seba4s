package com.example.vehicleapi.service;

import com.example.vehicleapi.model.Vehicle;
import com.example.vehicleapi.repository.VehicleRepository;
import com.example.vehicleapi.exception.VehicleNotFoundException;
import com.example.vehicleapi.exception.DuplicatePlacaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(String id) {
        @SuppressWarnings("null")
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("No se encontró vehículo con ID: " + id));
        return vehicle;
    }

    public Vehicle getVehicleByPlaca(String placa) {
        return vehicleRepository.findByPlaca(placa.toUpperCase())
                .orElseThrow(() -> new VehicleNotFoundException("No se encontró vehículo con placa: " + placa));
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicle.getPlaca() != null && vehicleRepository.existsByPlaca(vehicle.getPlaca().toUpperCase())) {
            throw new DuplicatePlacaException("Placa en uso: " + vehicle.getPlaca());
        }

        if (vehicle.getPlaca() != null) vehicle.setPlaca(vehicle.getPlaca().toUpperCase());
        if (vehicle.getMarca() != null) vehicle.setMarca(vehicle.getMarca().toUpperCase());
        if (vehicle.getModelo() != null) vehicle.setModelo(vehicle.getModelo().toUpperCase());
        if (vehicle.getColor() != null) vehicle.setColor(vehicle.getColor().toUpperCase());
        if (vehicle.getTipoCombustible() != null) vehicle.setTipoCombustible(vehicle.getTipoCombustible().toUpperCase());

        vehicle.setFechaCreacion(LocalDateTime.now());
        vehicle.setFechaActualizacion(LocalDateTime.now());

        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(String id, Vehicle vehicleDetails) {
        Vehicle vehicle = getVehicleById(id);

        if (vehicleDetails.getPlaca() != null && !vehicleDetails.getPlaca().equalsIgnoreCase(vehicle.getPlaca())) {
            if (vehicleRepository.existsByPlaca(vehicleDetails.getPlaca().toUpperCase())) {
                throw new DuplicatePlacaException("Placa en uso: " + vehicleDetails.getPlaca());
            }
        }

        if (vehicleDetails.getMarca() != null) {
            vehicle.setMarca(vehicleDetails.getMarca().toUpperCase());
        }
        if (vehicleDetails.getModelo() != null) {
            vehicle.setModelo(vehicleDetails.getModelo().toUpperCase());
        }
        if (vehicleDetails.getAño() != null) {
            vehicle.setAño(vehicleDetails.getAño());
        }
        if (vehicleDetails.getColor() != null) {
            vehicle.setColor(vehicleDetails.getColor().toUpperCase());
        }
        if (vehicleDetails.getPlaca() != null) {
            vehicle.setPlaca(vehicleDetails.getPlaca().toUpperCase());
        }
        if (vehicleDetails.getTipoCombustible() != null) {
            vehicle.setTipoCombustible(vehicleDetails.getTipoCombustible().toUpperCase());
        }
        if (vehicleDetails.getKilometraje() != null) {
            vehicle.setKilometraje(vehicleDetails.getKilometraje());
        }
        if (vehicleDetails.getPrecio() != null) {
            vehicle.setPrecio(vehicleDetails.getPrecio());
        }
        if (vehicleDetails.getDescripcion() != null) {
            vehicle.setDescripcion(vehicleDetails.getDescripcion());
        }
        if (vehicleDetails.getDisponible() != null) {
            vehicle.setDisponible(vehicleDetails.getDisponible());
        }

        vehicle.setFechaActualizacion(LocalDateTime.now());
        return vehicleRepository.save(vehicle);
    }

    @SuppressWarnings("null")
    public void deleteVehicle(String id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }

    public List<Vehicle> getVehiclesByMarca(String marca) {
        return vehicleRepository.findByMarcaIgnoreCase(marca);
    }

    public List<Vehicle> getVehiclesByMarcaAndModelo(String marca, String modelo) {
        return vehicleRepository.findByMarcaIgnoreCaseAndModeloIgnoreCase(marca, modelo);
    }

    public List<Vehicle> getVehiclesByYearRange(Integer startYear, Integer endYear) {
        return vehicleRepository.findByAñoBetween(startYear, endYear);
    }

    public List<Vehicle> getVehiclesByTipoCombustible(String tipoCombustible) {
        return vehicleRepository.findByTipoCombustibleIgnoreCase(tipoCombustible);
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return vehicleRepository.findByColorIgnoreCase(color);
    }

    public List<Vehicle> getVehiclesByDisponibilidad(Boolean disponible) {
        return vehicleRepository.findByDisponible(disponible);
    }

    public List<Vehicle> getVehiclesByPriceRange(Double precioMin, Double precioMax) {
        return vehicleRepository.findByPrecioBetween(precioMin, precioMax);
    }

    public List<Vehicle> getVehiclesByMaxKilometraje(Double maxKilometraje) {
        return vehicleRepository.findByKilometrajeLessThanEqual(maxKilometraje);
    }

    public List<Vehicle> searchVehicles(String marca, String modelo, Integer año, String tipoCombustible) {
        return vehicleRepository.findByMultipleCriteria(marca, modelo, año, tipoCombustible);
    }

    public long countVehiclesByMarca(String marca) {
        return vehicleRepository.countByMarcaIgnoreCase(marca);
    }

    public boolean existsByPlaca(String placa) {
        return vehicleRepository.existsByPlaca(placa.toUpperCase());
    }
}