package com.danielcarnauba.learning.landon_hotel.web.api;

import com.danielcarnauba.learning.landon_hotel.data.entity.Room;
import com.danielcarnauba.learning.landon_hotel.data.repository.RoomRepository;
import com.danielcarnauba.learning.landon_hotel.web.exception.BadRequestException;
import com.danielcarnauba.learning.landon_hotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {

    private final RoomRepository roomRepository;

    public RoomApiController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        Optional<Room> room = roomRepository.findById(id);

        if (room.isEmpty()) {
            throw new NotFoundException("Room not found with id " + id);
        }

        return room.get();
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        if (id.equals(room.getId())) {
            throw new BadRequestException("The room id pn path does not match the request body id");
        }

        return roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteRoom(@PathVariable Long id) {
        roomRepository.deleteById(id);
    }
}
