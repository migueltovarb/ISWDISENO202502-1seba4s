package com.example.vehicleapi.repository;

import com.example.vehicleapi.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    Optional<Vehicle> findByPlaca(String placa);
    List<Vehicle> findByMarcaIgnoreCase(String marca);
    List<Vehicle> findByMarcaIgnoreCaseAndModeloIgnoreCase(String marca, String modelo);
    List<Vehicle> findByAñoBetween(Integer yearStart, Integer yearEnd);
    List<Vehicle> findByTipoCombustibleIgnoreCase(String tipoCombustible);
    List<Vehicle> findByColorIgnoreCase(String color);
    List<Vehicle> findByDisponible(Boolean disponible);
    List<Vehicle> findByPrecioBetween(Double precioMin, Double precioMax);
    List<Vehicle> findByKilometrajeLessThanEqual(Double maxKilometraje);
    @Query("{ $and: [" +
           "{ $or: [ { 'marca': { $regex: ?0, $options: 'i' } }, { ?0: null } ] }," +
           "{ $or: [ { 'modelo': { $regex: ?1, $options: 'i' } }, { ?1: null } ] }," +
           "{ $or: [ { 'año': ?2 }, { ?2: null } ] }," +
           "{ $or: [ { 'tipoCombustible': { $regex: ?3, $options: 'i' } }, { ?3: null } ] }" +
           "] }")
    List<Vehicle> findByMultipleCriteria(String marca, String modelo, Integer year, String tipoCombustible);
    long countByMarcaIgnoreCase(String marca);
    boolean existsByPlaca(String placa);
}