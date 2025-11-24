# Ejemplos de endpoints para Postman

## Buscar disponibilidad de habitaciones
GET http://localhost:8080/api/reservas/disponibilidad/{hotelId}?entrada=2025-12-01&salida=2025-12-05

## Hacer una reserva
POST http://localhost:8080/api/reservas/hacer
Body (x-www-form-urlencoded):
- hotelId: <id del hotel>
- habitacionNumero: <número de habitación>
- clienteId: <id del cliente>
- entrada: 2025-12-01
- salida: 2025-12-05

## Check-in
POST http://localhost:8080/api/reservas/checkin/{reservaId}

## Check-out
POST http://localhost:8080/api/reservas/checkout/{reservaId}

---

Recuerda crear primero los hoteles, habitaciones y clientes en la base de datos para poder probar las reservas.