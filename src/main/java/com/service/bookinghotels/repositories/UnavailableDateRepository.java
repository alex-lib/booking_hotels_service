package com.service.bookinghotels.repositories;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.entities.UnavailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface UnavailableDateRepository extends JpaRepository<UnavailableDate, Long> {

    @Modifying
    @Query("DELETE FROM UnavailableDate u WHERE u.room = :room AND u.date = :date")
    void deleteByRoomAndDate (Room room, LocalDate date);
}
