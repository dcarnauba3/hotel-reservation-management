package com.danielcarnauba.learning.landon_hotel.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "reservations")
@Data
@ToString
public class Reservation {
    /*
    RESERVATION_ID BIGINT PRIMARY KEY auto_increment,
  ROOM_ID BIGINT NOT NULL,
  GUEST_ID BIGINT NOT NULL,
  RES_DATE DATE
     */

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationId;

    @Column(name = "room_id")
    private long roomId;

    @Column(name = "guest_id")
    private long guestId;

    @Column(name = "res_date")
    private Date reservationDate;
}
