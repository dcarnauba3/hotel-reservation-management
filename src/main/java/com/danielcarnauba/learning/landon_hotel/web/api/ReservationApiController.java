package com.danielcarnauba.learning.landon_hotel.web.api;

import com.danielcarnauba.learning.landon_hotel.data.entity.Reservation;
import com.danielcarnauba.learning.landon_hotel.data.repository.ReservationRepository;
import com.danielcarnauba.learning.landon_hotel.web.exception.BadRequestException;
import com.danielcarnauba.learning.landon_hotel.web.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {

    private final ReservationRepository reservationRepository;

    public ReservationApiController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> getAllReservations(@RequestParam(value="date", required = false)String dateString){
        if(StringUtils.isNotBlank(dateString)){
            Date date = new Date(new java.util.Date().getTime());
            return this.reservationRepository.findAllByReservationDate(date);
        }
        return this.reservationRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation addReservation(@RequestBody Reservation reservation){
        return this.reservationRepository.save(reservation);
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id")long id){
        Optional<Reservation> reservation = this.reservationRepository.findById(id);
        if (reservation.isEmpty()){
            throw new NotFoundException("reservation not found with id: " + id);
        }
        return reservation.get();
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id")long id, @RequestBody Reservation reservation){
        if (id != reservation.getReservationId()){
            throw new BadRequestException("id on path must match body");
        }
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteReservation(@PathVariable("id")long id){
        this.reservationRepository.deleteById(id);
    }
}
