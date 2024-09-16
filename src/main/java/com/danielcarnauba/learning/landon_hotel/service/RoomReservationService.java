package com.danielcarnauba.learning.landon_hotel.service;

import com.danielcarnauba.learning.landon_hotel.data.entity.Guest;
import com.danielcarnauba.learning.landon_hotel.data.entity.Reservation;
import com.danielcarnauba.learning.landon_hotel.data.entity.Room;
import com.danielcarnauba.learning.landon_hotel.data.repository.GuestRepository;
import com.danielcarnauba.learning.landon_hotel.data.repository.ReservationRepository;
import com.danielcarnauba.learning.landon_hotel.data.repository.RoomRepository;
import com.danielcarnauba.learning.landon_hotel.service.model.RoomReservation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;


    public RoomReservationService(
            RoomRepository roomRepository,
            GuestRepository guestRepository,
            ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(String reservationDate) {
        Date date;
        if (StringUtils.isNotEmpty(reservationDate)) {
            date = Date.valueOf(reservationDate);
        } else {
            date = Date.valueOf(LocalDate.now());
        }

        Map<Long, RoomReservation> roomReservations = new HashMap<>();

        List<Room> rooms = roomRepository.findAll();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());

            roomReservations.put(room.getId(), roomReservation);

        });

        List<Reservation> reservations = reservationRepository.findAllByReservationDate(date);

        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setReservationId(reservation.getReservationId());
            roomReservation.setReservationDate(reservation.getReservationDate().toString());
            Optional<Guest> guest = guestRepository.findById(reservation.getGuestId());
            guest.ifPresent(value -> {
                roomReservation.setGuestId(value.getId());
                roomReservation.setFirstName(value.getFirstName());
                roomReservation.setLastName(value.getLastName());
            });
        });

        return roomReservations.values().stream().toList();
    }

}
