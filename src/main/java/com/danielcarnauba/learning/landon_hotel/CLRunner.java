package com.danielcarnauba.learning.landon_hotel;

import com.danielcarnauba.learning.landon_hotel.data.entity.Guest;
import com.danielcarnauba.learning.landon_hotel.data.entity.Reservation;
import com.danielcarnauba.learning.landon_hotel.data.entity.Room;
import com.danielcarnauba.learning.landon_hotel.data.repository.GuestRepository;
import com.danielcarnauba.learning.landon_hotel.data.repository.ReservationRepository;
import com.danielcarnauba.learning.landon_hotel.data.repository.RoomRepository;
import com.danielcarnauba.learning.landon_hotel.service.RoomReservationService;
import com.danielcarnauba.learning.landon_hotel.service.model.RoomReservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Component
public class CLRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final RoomReservationService roomReservationService;

    public CLRunner(RoomRepository roomRepository,
                    GuestRepository guestRepository,
                    ReservationRepository reservationRepository,
                    RoomReservationService roomReservationService) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.roomReservationService = roomReservationService;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("************************** Rooms **************************");
//        List<Room> rooms = roomRepository.findAll();
//        Optional<Room> room = roomRepository.findByRoomNumberIgnoreCase("p1");
//        System.out.println(room);
//        rooms.forEach(System.out::println);
//        System.out.println("************************** Guests **************************");
//        List<Guest> guests = guestRepository.findAll();
//        guests.forEach(System.out::println);
//        System.out.println("************************** Reservations **************************");
//        List<Reservation> reservations = reservationRepository.findAll();
//        reservations.forEach(System.out::println);
//        System.out.println("************************** Filtered Reservations **************************");
//        reservations = reservationRepository.findAllByReservationDate(Date.valueOf(LocalDate.of(2023, Month.AUGUST, 28)));
//        reservations.forEach(System.out::println);

        List<RoomReservation> reservations = roomReservationService.getRoomReservationsForDate("2023-08-28");
        reservations.forEach(System.out::println);
    }
}
