package com.service.bookinghotels.repositories;
import com.service.bookinghotels.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    @Query("SELECT h FROM Hotel h WHERE h.name = :name")
    Room getRoomByName(String name);
}